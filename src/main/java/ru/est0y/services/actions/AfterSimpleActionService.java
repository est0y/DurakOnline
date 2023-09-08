package ru.est0y.services.actions;

import ru.est0y.domain.Game;

public interface AfterSimpleActionService<T> {
    void act(Game game);
}
