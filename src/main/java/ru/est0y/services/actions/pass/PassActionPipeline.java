package ru.est0y.services.actions.pass;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.playerActions.PassAction;
import ru.est0y.services.actions.AfterSimpleActionService;
import ru.est0y.services.actions.SimpleActionFilterService;
import ru.est0y.services.actions.SimpleActionService;

import java.util.List;

@AllArgsConstructor
@Service
public class PassActionPipeline {
    private final List<SimpleActionFilterService<PassAction>> passActionFilterServices;
    private final SimpleActionService<PassAction> passActionService;
    private final AfterSimpleActionService<PassAction> afterPassService;
    public void run(Game game){
        passActionFilterServices.forEach(s->s.doFilter(game.getAttackerSeat(),game));
        passActionService.act(game.getAttackerSeat(),game);
        afterPassService.act(game);
    }
}
