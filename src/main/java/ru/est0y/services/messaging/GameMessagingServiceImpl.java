package ru.est0y.services.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.services.gameManagement.GameStateService;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameMessagingServiceImpl implements GameMessagingService {
    private final GameStateService gameStateService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void sendToAll(Game game) {
        sendToPlayers(game);
        sendToSpectators(game);
    }

    private void sendToPlayers(Game game) {
        game.getSeats().forEach(s -> {
            if (s.getPlayer() == null) {
                return;
            }
            var gameState = gameStateService.getGameStateForPlayer(game, s);
            simpMessagingTemplate.convertAndSendToUser(
                    s.getPlayer().getId(),
                    "/queue/gameState/" + game.getId(),
                    gameState
            );
        });
    }

    private void sendToSpectators(Game game) {
        simpMessagingTemplate.convertAndSend(
                "/gameState/spectator/" + game.getId(),
                gameStateService.getGameStateForSpectator(game)
        );
    }
}
