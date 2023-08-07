package ru.est0y.services.gameManagement.startGame;

import ru.est0y.domain.Game;

public interface GameStartProcessor {
    void handle(Game game);
}
