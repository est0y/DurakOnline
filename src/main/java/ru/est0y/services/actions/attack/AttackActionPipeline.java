package ru.est0y.services.actions.attack;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;
import ru.est0y.domain.playerActions.AttackAction;
import ru.est0y.services.actions.ActionService;
import ru.est0y.services.actions.AfterSimpleActionService;
import ru.est0y.services.actions.handle.ActionFilterService;

import java.util.List;
@Service
@AllArgsConstructor
public class AttackActionPipeline {
    private final List<ActionFilterService<AttackAction>> filters;
    private final ActionService<AttackAction> actionService;
    private final AfterSimpleActionService<AttackAction> afterAttack;

    public void run(Seat seat, Game game, AttackAction action) {
        filters.forEach(f -> f.doFilter(seat, game, action));
        actionService.act(seat, game, action);
        afterAttack.act(game);
    }
}
