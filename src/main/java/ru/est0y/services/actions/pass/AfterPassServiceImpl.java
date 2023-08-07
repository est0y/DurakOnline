package ru.est0y.services.actions.pass;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.playerActions.PassAction;
import ru.est0y.services.actions.AfterSimpleActionService;
import ru.est0y.services.actions.turnManager.TurnManager;
@Service
@AllArgsConstructor
public class AfterPassServiceImpl implements AfterSimpleActionService<PassAction> {
    private final TurnManager turnManager;

    @Override
    public void act(Game game) {
        if (game.getPassCount() == game.getSeats().size()-1) {
            turnManager.endTurn(game);
            turnManager.checkWinConditional(game);
        } else {
            game.setAttackerSeat(turnManager.nextAttacker(game));
           // game.setPassCount(0);
            //if (game.getGameStatus()!= GameStatus.DEFENDER_TAKES) game.setPassCount(0);
        }
    }
}
