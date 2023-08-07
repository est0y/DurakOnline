package ru.est0y.services.actions.turnManager;

import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;

public interface TurnManager {
    Seat nextAttacker(Game game);
    void endTurn(Game game);
    void checkWinConditional(Game game);
}
