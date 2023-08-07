package ru.est0y.services.actions.defence.filters;

import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.GameStatus;
import ru.est0y.domain.Seat;
import ru.est0y.domain.playerActions.DefenceAction;
import ru.est0y.exceptions.defence.WrongPhaseException;
import ru.est0y.services.actions.handle.ActionFilterService;

@Service
public class DefenceFilterService implements ActionFilterService<DefenceAction> {
    @Override
    public void doFilter(Seat seat, Game game, DefenceAction action) {
        if (game.getGameStatus() != GameStatus.DEFENDING) {
            throw new WrongPhaseException(game.getGameStatus(),GameStatus.DEFENDING);
        }
    }
}
