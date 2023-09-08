package ru.est0y.services.actions.attack.after;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.GameStatus;
import ru.est0y.domain.playerActions.AttackAction;
import ru.est0y.services.actions.AfterSimpleActionService;
import ru.est0y.services.actions.turnManager.TurnManager;
import ru.est0y.services.utils.games.GameUtils;


@AllArgsConstructor
@Service
public class AfterAttackActionService implements AfterSimpleActionService<AttackAction> {
    private final TurnManager turnManager;
    private final GameUtils gameUtils;

    @Override
    public void act(Game game) {
        var countAttacksLeft = gameUtils.getAvailableAttacksCount(game);
        if (game.getGameStatus() == GameStatus.DEFENDER_TAKES && countAttacksLeft == 0) {
            turnManager.endTurn(game);
        }
    }
}
