package ru.est0y.services.actions.take;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.playerActions.TakeAction;
import ru.est0y.services.actions.AfterSimpleActionService;
import ru.est0y.services.actions.turnManager.TurnManager;
import ru.est0y.services.utils.games.GameUtils;

@AllArgsConstructor
@Service
public class AfterTakeAction implements AfterSimpleActionService<TakeAction> {
    private final GameUtils gameUtils;
    private final TurnManager turnManager;

    @Override
    public void act(Game game) {
        var attacksCount = gameUtils.getAvailableAttacksCount(game);
        if (attacksCount == 0) turnManager.endTurn(game);
    }
}
