package ru.est0y.services.actions.pass;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;
import ru.est0y.domain.playerActions.PassAction;
import ru.est0y.services.actions.SimpleActionService;
import ru.est0y.services.actions.turnManager.TurnManager;


@AllArgsConstructor
@Service
public class PassActionService implements SimpleActionService<PassAction> {
    private final TurnManager turnManager;

    @Override
    public void act(Seat seat, Game game) {
        game.incrementPassCount();



        /*var nextAttackerSeat = turnManager.nextAttacker(game);
        game.setAttackerSeat(nextAttackerSeat);
        var nextDefenderSeatNumber = nextAttackerSeat.getNumber() == game.getSeats().size() ? 1 : nextAttackerSeat.getNumber() + 1;
        var nextDefenderSeat = game.getSeats().stream().filter(s -> s.getNumber() == nextDefenderSeatNumber).findFirst().orElseThrow();
        game.setDefenderSeat(nextDefenderSeat);*/
    }
}
