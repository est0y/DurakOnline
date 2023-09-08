package ru.est0y.services.actions.attack;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.est0y.domain.CardStack;
import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;
import ru.est0y.domain.playerActions.AttackAction;
import ru.est0y.services.actions.ActionService;

import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class AttackActionService implements ActionService<AttackAction> {
    @Override
    public void act(Seat seat, Game game, AttackAction action) {
        seat.getCardsId().remove((Integer) action.attackCardId());
        var table = game.getPlayingTable();
        var stacks = table.getCardStacks();
        stacks.add(new CardStack(stacks.size() + 1, List.of(action.attackCardId())));
        game.setPassCount(0);
        log.info("seat {} attacks",seat.getNumber());
    }
}
