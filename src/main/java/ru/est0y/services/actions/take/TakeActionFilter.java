package ru.est0y.services.actions.take;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;
import ru.est0y.domain.playerActions.TakeAction;
import ru.est0y.exceptions.take.AllCardStacksClosedException;
import ru.est0y.services.actions.SimpleActionFilterService;
import ru.est0y.services.utils.stacks.CardStacksUtils;

@Service
@AllArgsConstructor
public class TakeActionFilter implements SimpleActionFilterService<TakeAction> {
    private final CardStacksUtils cardStacksUtils;

    @Override
    public void doFilter(Seat seat, Game game) {
        var stacks = game.getPlayingTable().getCardStacks();
        boolean isExistsOpenStacks = cardStacksUtils.isExistOpenStacks(stacks);
        if (!isExistsOpenStacks) {
            throw new AllCardStacksClosedException(stacks);
        }
    }
}
