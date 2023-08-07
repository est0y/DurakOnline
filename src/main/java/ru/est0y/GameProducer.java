package ru.est0y;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.est0y.configs.props.GameProps;
import ru.est0y.services.gameManagement.GameManagerService;

@Component
@RequiredArgsConstructor
public class GameProducer {
    private final GameProps gameProps;
    private final GameManagerService gameManagerService;

    @Transactional
    public void createGames() {
        for (var gameId = 1; gameId <= gameProps.getGamesCount(); gameId++) {
            gameManagerService.createAndStartGame(String.valueOf(gameId));
        }
    }
}
