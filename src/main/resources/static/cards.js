var map = new Map([
  [1, '2C'],[2, '2D'],[3, '2H'],[4, '2S'],
  [5, '3C'],[6, '3D'],[7, '3H'],[8, '3S'],
  [9, '4C'],[10, '4D'],[11, '4H'],[12, '4S'],
  [13, '5C'],[14, '5D'],[15, '5H'],[16, '5S'],
  [17, '6C'],[18, '6D'],[19, '6H'],[20, '6S'],
  [21, '7C'],[22, '7D'],[23, '7H'],[24, '7S'],
  [25, '8C'],[26, '8D'],[27, '8H'],[28, '8S'],
  [29, '9C'],[30, '9D'],[31, '9H'],[32, '9S'],
  [33, 'TC'],[34, 'TD'],[35, 'TH'],[36, 'TS'],
  [37, 'JC'],[38, 'JD'],[39, 'JH'],[40, 'JS'],
  [41, 'QC'],[42, 'QD'],[43, 'QH'],[44, 'QS'],
  [45, 'KC'],[46, 'KD'],[47, 'KH'],[48, 'KS'],
  [49, 'AC'],[50, 'AD'],[51, 'AH'],[52, 'AS']
]);
function getCardIdByNumber(number){
    return map.get(number);
}
function addCardBacksInHand(hand,count){
    for (let i = 0; i < count; i++) {
     let imgElement=$("<img></img>");
              imgElement.attr( "src", "/cards/Blue_Back.svg" );
              imgElement.attr( "class", "card" );
              imgElement.attr( "style", "width: "+CARD_WIDTH+"px; margin-left: -56px; margin-top: 0px;" );
          hand.append(imgElement);
    }
}
function putOnStack(stack,card){
    stack.append(card);
}
function createNewStack(playingTable){
   let count = playingTable.children().length;
   console.log(count);
   let stack=$("<div></div");
   stack.attr( "id", "stack"+(count+1) );
   stack.attr("number",count+1);
   stack.attr( "class","stack hand vhand-compact" );
   stack.attr( "data-hand", "flow: vertical; width: "+CARD_WIDTH+";" );
   playingTable.append(stack);
return stack;
}


function getCardById(stringId,number){
    let card=$("<img></img");
    card.attr( "src", "/cards/"+stringId+".svg" );
    card.attr( "class", "card" );
    card.attr( "style", "width: "+CARD_WIDTH+"px;" );
    card.attr("cardId",number);
    return card;
}

function getCardByNumber(number){
    return getCardById(getCardIdByNumber(number),number);
}
function getCard(numberOrId){
    const isNumeric = n => !!Number(n);
    if(isNumeric(numberOrId)){
        return getCardByNumber(numberOrId);
    }else{
        return getCardById(numberOrId);
}
}
function getCards(arrayId){
    console.log(arrayId);
    let cards=$();
    arrayId.forEach(id=>cards=cards.add(getCard(id)));
    return cards;
}
var action={

}
