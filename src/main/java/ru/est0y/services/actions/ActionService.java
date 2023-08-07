package ru.est0y.services.actions;

import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;

public interface ActionService<T> {
    void act(Seat seat, Game game,T action);
}
