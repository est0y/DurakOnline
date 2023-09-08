package ru.est0y.services.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.Player;
import ru.est0y.domain.state.GameState;
import ru.est0y.services.gameManagement.GameStateService;
import ru.est0y.services.utils.games.GameUtils;

import javax.swing.text.PlainDocument;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameMessagingServiceImpl implements GameMessagingService {
    private final GameStateService gameStateService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final GameUtils gameUtils;

    @Override
    public void sendToAll(Game game) {
        sendToPlayers(game);
        sendToSpectators(game);
    }

    @Override
    public void sendToUser(Game game, String userId) {
        //todo вынести
        log.info(game.getSeats().toString());
        var optionalPlayerSeat = game.getSeats().stream().filter(s -> s.getPlayer() != null).filter(s -> s.getPlayer().getId().equals(userId)).findFirst();
        if (optionalPlayerSeat.isPresent()) {
            var playerSeat = optionalPlayerSeat.get();
            sendGameStateToUser(game.getId(),
                    gameStateService.getGameStateForPlayer(game, playerSeat),
                    userId);
        } else {
            sendGameStateToUser(game.getId(), gameStateService.getGameStateForSpectator(game), userId);
        }
    }

    private void sendToPlayers(Game game) {
        game.getSeats().forEach(s -> {
            if (s.getPlayer() == null) {
                return;
            }
            var gameState = gameStateService.getGameStateForPlayer(game, s);
            sendGameStateToUser(game.getId(),
                    gameState,
                    s.getPlayer().getId());
        });
    }

    private void sendToSpectators(Game game) {
        simpMessagingTemplate.convertAndSend(
                "/gameState/spectator/" + game.getId(),
                gameStateService.getGameStateForSpectator(game)
        );
    }

    private void sendGameStateToUser(String gameId, GameState gameState, String userId) {
        simpMessagingTemplate.convertAndSendToUser(
                userId,
                "/queue/gameState/" + gameId,
                gameState
        );
    }
}
