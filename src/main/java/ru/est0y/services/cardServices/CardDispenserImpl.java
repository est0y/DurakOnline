package ru.est0y.services.cardServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.configs.props.GameProps;
import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;
import ru.est0y.services.utils.seats.SeatsSorting;


import java.util.List;

@AllArgsConstructor
@Service
public class CardDispenserImpl implements CardDispenser {
    GameProps gameProps;
    private final CardsIdService cardsIdService;
    private final SeatsSorting seatsSorting;

    @Override
    public void dealCardsIdForSeatsToLimit(Game game) {
        var deck = game.getPlayingTable().getDeck();
        var sortedSeats = seatsSorting.sortToDealCards(
                game.getSeats(),
                game.getAttackerSeat(),
                game.getDefenderSeat()
        );
        System.out.println("ss"+sortedSeats);
        sortedSeats.forEach(seat -> {
            System.out.println(gameProps.getMinHandOnNewTurn());
            var needCardCount = gameProps.getMinHandOnNewTurn() - seat.getCardsId().size();
            dealCards(seat,needCardCount,deck);
        });
    }
    private void dealCards(Seat seat, int needCardCount, List<Integer> deck){
        if (needCardCount <= 0){
            return;
        }
        var needCards = cardsIdService.takeTopCardsId(needCardCount, deck);
        seat.getCardsId().addAll(needCards);
    }
}
