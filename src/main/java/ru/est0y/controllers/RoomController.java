package ru.est0y.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import ru.est0y.domain.messages.RoomRole;
import ru.est0y.domain.messages.SitAction;
import ru.est0y.domain.state.GameState;
import ru.est0y.exceptions.GameIsNotExistsException;
import ru.est0y.repositories.GameRepository;
import ru.est0y.repositories.SeatRepository;
import ru.est0y.services.SitService;
import ru.est0y.services.gameManagement.GameStateService;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@Controller
public class RoomController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    private final GameRepository gameRepository;
    private final SeatRepository seatRepository;
    private final SitService sitService;
    private final GameStateService gameStateService;


    @MessageMapping("/game/id.{gameId}/sit")
    public void sit(Principal principal, @DestinationVariable String gameId, SitAction sitAction) {
        var roomRoles = sitService.takeSeatIfFree(gameId, principal.getName(), sitAction);
        roomRoles.keySet().forEach(roomId -> {
            simpMessagingTemplate.convertAndSendToUser(principal.getName(),
                    "/queue/role/" + roomId, roomRoles.get(roomId));
        });
    }

    @MessageMapping("/game/id.{gameId}/gameState")
    @Transactional
    public void gameState(Principal principal, @DestinationVariable String gameId) {
        GameState gameState;
        var optionalGameByPlayer = gameRepository.findByIdAndSeats_Player_Id(gameId, principal.getName());
        if (optionalGameByPlayer.isPresent()) {
            var game = optionalGameByPlayer.get();
            gameState = gameStateService.getGameStateForPlayer(game, seatRepository.findByPlayerId(principal.getName()).get().getNumber());
            log.info("game state for player");
        } else {
            var game = gameRepository.findById(gameId).orElseThrow(() -> new GameIsNotExistsException(gameId));
            gameState = gameStateService.getGameStateForSpectator(game);
            log.info("game state for spectator");
        }
        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/queue/gameState/" + gameId, gameState);

    }

    @MessageMapping("/game/id.{gameId}/gameState/spectator")
    @Transactional
    public void spectatorGameState(@DestinationVariable String gameId) {
        var game = gameRepository.findById(gameId).orElseThrow();
        var gameState = gameStateService.getGameStateForSpectator(game);
        simpMessagingTemplate.convertAndSend("/gameState/spectator/" + gameId, gameState);

    }

    @MessageMapping("/connect/{roomId}")
    @Transactional
    public void getRoomRole(@DestinationVariable String roomId, Principal principal) {
        log.info("Get role " + roomId);
        boolean isPlayer = gameRepository.existsByIdAndSeats_Player_Id(roomId, principal.getName());
        simpMessagingTemplate.convertAndSendToUser(principal.getName(),
                "/queue/role/" + roomId,
                isPlayer ? RoomRole.PLAYER : RoomRole.SPECTATOR);
    }
}
