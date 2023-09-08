package ru.est0y.services.actions.take;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.GameStatus;
import ru.est0y.domain.Seat;
import ru.est0y.domain.playerActions.TakeAction;
import ru.est0y.services.actions.SimpleActionService;

@AllArgsConstructor
@Service
public class TakeActionService implements SimpleActionService<TakeAction> {

    @Override
    public void act(Seat seat, Game game) {
        game.setGameStatus(GameStatus.DEFENDER_TAKES);
    }
}
