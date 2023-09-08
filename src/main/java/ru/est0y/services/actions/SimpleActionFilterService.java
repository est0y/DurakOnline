package ru.est0y.services.actions;

import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;

public interface SimpleActionFilterService<T> {
    void doFilter(Seat seat, Game game);
}
