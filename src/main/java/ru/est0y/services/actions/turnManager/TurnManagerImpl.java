package ru.est0y.services.actions.turnManager;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.GameStatus;
import ru.est0y.domain.Seat;
import ru.est0y.services.cardServices.CardDispenser;
import ru.est0y.services.utils.seats.SeatSequence;
import ru.est0y.services.utils.stacks.CardStacksUtils;


@AllArgsConstructor
@Service
public class TurnManagerImpl implements TurnManager{
    private final CardDispenser cardDispenser;
    private final CardStacksUtils cardStacksUtils;
    private final SeatSequence seatSequence;

    public Seat nextAttacker(Game game) {
        var nextSeat = seatSequence.getNextSeat(game.getAttackerSeat(), game.getSeats());
        while (nextSeat.equals(game.getDefenderSeat())) {
            nextSeat = seatSequence.getNextSeat(nextSeat, game.getSeats());
        }
        return nextSeat;

    }

    public void endTurn(Game game) {
        game.setPassCount(0);
        if (game.getGameStatus() == GameStatus.DEFENDER_TAKES) {
            defenderTakes(game);
        } else {
            var nextAttackerSeat = game.getDefenderSeat();
            game.setAttackerSeat(nextAttackerSeat);
            var nextDefender = seatSequence.getNextSeat(nextAttackerSeat, game.getSeats());
            game.setDefenderSeat(nextDefender);
        }
        game.getPlayingTable().getCardStacks().clear();
        dealCards(game);
    }

    private void defenderTakes(Game game) {
        var stacks = game.getPlayingTable().getCardStacks();
        var defenderSeat = game.getDefenderSeat();
        defenderSeat.getCardsId().addAll(cardStacksUtils.takeAllCardsId(stacks));
        game.setGameStatus(GameStatus.DEFENDING);
        if (game.getSeats().size() != 2) {
            var nextAttackerSeat = seatSequence.getNextSeat(defenderSeat, game.getSeats());
            var nextDefenderSeat = seatSequence.getNextSeat(nextAttackerSeat, game.getSeats());
            game.setAttackerSeat(nextAttackerSeat);
            game.setDefenderSeat(nextDefenderSeat);
        }
    }

    public void checkWinConditional(Game game) {
        var seatsHaveMoreOneCard = game.getSeats().stream().filter(seat -> seat.getCardsId().size() > 0).toList();
        if (seatsHaveMoreOneCard.size() == 1) game.setGameStatus(GameStatus.GAME_OVER);
    }

    public void dealCards(Game game) {
        cardDispenser.dealCardsIdForSeatsToLimit(game);
    }
}