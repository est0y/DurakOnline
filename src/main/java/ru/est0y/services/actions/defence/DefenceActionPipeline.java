package ru.est0y.services.actions.defence;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;
import ru.est0y.domain.playerActions.DefenceAction;
import ru.est0y.services.actions.ActionService;
import ru.est0y.services.actions.handle.ActionFilterService;

import java.util.List;

@Service
@AllArgsConstructor
public class DefenceActionPipeline {
    private final List<ActionFilterService<DefenceAction>> filters;
    private final ActionService<DefenceAction> actionService;
    //private final AfterSimpleActionService<DefenceAction> afterDefenceServices;

    public void run(Seat seat, Game game, DefenceAction action) {
        filters.forEach(f -> f.doFilter(seat, game, action));
        actionService.act(seat, game, action);
        //todo проверка на то незакончились ли карты в руке

    }
}
