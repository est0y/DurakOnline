package ru.est0y.services.gameManagement.startGame;

import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.est0y.domain.Game;
import ru.est0y.domain.GameStatus;
import ru.est0y.domain.Seat;
import ru.est0y.domain.cards.CardValue;
import ru.est0y.repositories.CardRepository;
import ru.est0y.services.cardServices.CardService;

import java.util.Comparator;

@Component
@Order(2)
@AllArgsConstructor
public class MovesStart implements GameStartProcessor {
    private final CardService<Integer> cardIdService;


    @Override
    public void handle(Game game) {
        var trump = game.getPlayingTable().getTrump();
        Seat attackerSeat = game.getSeats().stream().min(Comparator.comparing(seat -> {
            var trumpCard = cardIdService.getMinTrumpCard(seat.getCardsId(), trump);
            return trumpCard.map(card -> card.getValue().ordinal()).orElseGet(() -> CardValue.values().length+1);
        })).orElseThrow(()->new IllegalStateException("No seats"));
        game.setAttackerSeat(attackerSeat);
        var defenderSeatNumber = attackerSeat.getNumber() + 1 > game.getSeats().size() ? 1 : attackerSeat.getNumber() + 1;
        game.setDefenderSeat(game.getSeats().stream().filter(s->s.getNumber()==defenderSeatNumber).findFirst().orElseThrow());
        game.setGameStatus(GameStatus.DEFENDING);
    }
}
