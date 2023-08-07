package ru.est0y.services.actions.handle;

import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;

public interface ActionFilterService<T> {
    void doFilter(Seat seat, Game game, T action);
}
