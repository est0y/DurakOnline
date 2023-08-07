package ru.est0y.services.actions.pass;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.GameStatus;
import ru.est0y.domain.Seat;
import ru.est0y.domain.playerActions.PassAction;
import ru.est0y.exceptions.pass.NoAttacksException;
import ru.est0y.exceptions.pass.NotAllCardStacksClosedException;
import ru.est0y.services.actions.SimpleActionFilterService;
import ru.est0y.services.utils.stacks.CardStacksUtils;

@Service
@AllArgsConstructor
public class PassActionFilterService implements SimpleActionFilterService<PassAction> {
    private final CardStacksUtils cardStacksUtils;

    @Override
    public void doFilter(Seat seat, Game game) {
        var stacks = game.getPlayingTable().getCardStacks();
        boolean isExistOpenStacks = cardStacksUtils.isExistOpenStacks(stacks);
        boolean isDefenderTakes = game.getGameStatus() == GameStatus.DEFENDER_TAKES;
        if (isExistOpenStacks && !isDefenderTakes) {
            throw new NotAllCardStacksClosedException(stacks);
        }
        if (stacks.size() == 0) {
            throw new NoAttacksException();
        }
    }
}
