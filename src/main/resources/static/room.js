let roomId  =  document.currentScript.getAttribute('roomId');

let stompClient = null;

const chatLineElementId = "chatLine";
const roomIdElementId = "roomId";
const messageElementId = "message";
let roomRole=null



    stompClient = Stomp.over(new SockJS('/any'));
    stompClient.connect({}, (frame) => {
        console.log(`Connected to roomId: ${roomId} frame:${frame}`);
        stompClient.subscribe('/user/queue/role/'+roomId, (message) => {
        const role=JSON.parse(message.body);
        if(role=="SPECTATOR"){
           stompClient.unsubscribe('playerTopic');
           stompClient.subscribe('/gameState/spectator/'+roomId, (message) => setGameState(JSON.parse(message.body)),{ id: "spectatorTopic"});
           stompClient.send("/app/game/id."+roomId+"/gameState/spectator", {}, {});
        }else if(role=="PLAYER"){
           stompClient.unsubscribe('spectatorTopic');
           stompClient.subscribe('/user/queue/gameState/'+roomId, (message) => setGameState(JSON.parse(message.body)),{id: "playerTopic"});
          stompClient.send("/app/game/id."+roomId+"/gameState", {}, {});
        }
   });
        stompClient.send('/app/connect/'+roomId, {}, '');

    });


const disconnect = () => {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}


const takeSeat = (seatNumber) => {
    const message = document.getElementById(messageElementId).value;
    stompClient.send("/app/game/id."+roomId+"/sit", {}, JSON.stringify({'name': message,'seatNumber':seatNumber}))
}


$("#seat1").on("click",  function () {
    stompClient.send("/app/game/id."+roomId+"/sit", {}, JSON.stringify({'name': 'name 1','seatNumber':1}))
 });
 $("#seat2").on("click",  function () {
     stompClient.send("/app/game/id."+roomId+"/sit", {}, JSON.stringify({'name': 'name 2','seatNumber':2}))
  });
$("#seat3").on("click",  function () {
     stompClient.send("/app/game/id."+roomId+"/sit", {}, JSON.stringify({'name': 'name 3','seatNumber':3}))
  });

function setGameState(gameState){
    $(".hand").empty();
    $(".pass_btn").hide();
    $(".take_btn").hide();
    $("#gameStatus").empty();
    if(gameState.status=="DEFENDER_TAKES")$("#gameStatus").append("Defending player takes")
    gameState.seats.forEach(seat=>{
        let seatElement=$("#hand"+seat.number);
        seatElement.empty();
        if(seat.cardsId==null || seat.cardsId.length==0){
           addCardBacksInHand(seatElement,seat.cardsCount);
        }else{
        console.log("seat cards:"+seat.cardsId)
            seatElement.append(getCards(seat.cardsId));
        }
        if(gameState.yourSeatNumber==seat.number){
            PLAYER.role=seat.role
            if(seat.role=="DEFENCE"){
                $("#take"+seat.number).show();
            }else if(seat.role=="ATTACK"){
                 $("#pass"+seat.number).show();
            }
        }
        if(seat.free){
            $("#seat"+seat.number).prop('disabled', false);
        }else{
           $("#seat"+seat.number).prop('disabled', true);
        }
    });
    $("#playingTable").empty();
    $("#trump").empty();
    $("#trump").append("trump:"+gameState.playingTable.trump);
    let cardsCountInDeck=gameState.playingTable.cardsCountInDeck;
    $("#deckCardsCount").empty();
    $("#deckCardsCount").append("cards left in the deck:"+cardsCountInDeck);
    $("#bottomCard").empty();
    $("#bottomCard").append("bottomCard:"+getCardIdByNumber(gameState.playingTable.bottomCard));
    gameState.playingTable.cardStacks.forEach(stack=>{
        let stackElement=createNewStack($("#playingTable"));
        stackElement.append(getCards(stack.cardsId));
    });
   $(".stack").on("click", "img.card", function () {
        stompClient.send("/app/game/id."+roomId+"/defence", {},
        JSON.stringify({'defenceCardId': PLAYER.selectedCard,'stackNumber':$(this).parent().attr("number")}))
   });
}

