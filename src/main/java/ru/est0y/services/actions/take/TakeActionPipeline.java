package ru.est0y.services.actions.take;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.playerActions.TakeAction;
import ru.est0y.services.actions.AfterSimpleActionService;
import ru.est0y.services.actions.SimpleActionFilterService;
import ru.est0y.services.actions.SimpleActionService;

import java.util.List;
@Service
@AllArgsConstructor
public class TakeActionPipeline {
   private final List<SimpleActionFilterService<TakeAction>> filters;
    private final SimpleActionService<TakeAction> takeActionService;
    private final List<AfterSimpleActionService<TakeAction>> after;
    public void run(Game game){
        filters.forEach(f->f.doFilter(game.getDefenderSeat(),game));
        takeActionService.act(game.getDefenderSeat(),game);
        after.forEach(f->f.act(game));

    }
}
