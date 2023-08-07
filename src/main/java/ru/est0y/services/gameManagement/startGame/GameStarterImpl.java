package ru.est0y.services.gameManagement.startGame;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;

import java.util.List;
@Service
@AllArgsConstructor
public class GameStarterImpl implements GameStarter {
    private final List<GameStartProcessor> processors;

    @Override
    public void start(Game game) {
        processors.forEach(p -> p.handle(game));
    }
}
