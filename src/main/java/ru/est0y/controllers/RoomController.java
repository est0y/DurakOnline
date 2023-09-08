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
import ru.est0y.repositories.GameRepository;
import ru.est0y.repositories.SeatRepository;
import ru.est0y.services.SitService;
import ru.est0y.services.gameManagement.GameStateService;
import ru.est0y.services.messaging.GameMessagingService;

import java.security.Principal;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class RoomController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    private final GameRepository gameRepository;
    private final SitService sitService;
    private final GameStateService gameStateService;
    private final GameMessagingService gameMessagingService;


    @MessageMapping("/game/id.{gameId}/sit")
    public void sit(Principal principal, @DestinationVariable String gameId, SitAction sitAction) {
        Map<String, RoomRole> roomRoles = allocateSeatIfAvailable(gameId, principal.getName(), sitAction);
        sendRoomRolesToUser(principal.getName(), roomRoles);
    }

    @MessageMapping("/game/id.{gameId}/gameState")
    @Transactional
    public void sendGameState(Principal principal, @DestinationVariable String gameId) {
        //todo send to user
        var game = gameRepository.findById(gameId).orElseThrow();
        gameMessagingService.sendToUser(game,principal.getName());
    }

    @MessageMapping("/game/id.{gameId}/gameState/spectator")
    @Transactional
    public void sendSpectatorGameState(@DestinationVariable String gameId) {
        var game = gameRepository.findById(gameId).orElseThrow();
        var gameState = gameStateService.getGameStateForSpectator(game);
        simpMessagingTemplate.convertAndSend("/gameState/spectator/" + gameId, gameState);

    }

    @MessageMapping("/connect/{roomId}")
    @Transactional
    public void getRoomRole(@DestinationVariable String roomId, Principal principal) {
        log.info("Get role {}", roomId);
        boolean isPlayer = gameRepository.existsByIdAndSeats_Player_Id(roomId, principal.getName());
        simpMessagingTemplate.convertAndSendToUser(principal.getName(),
                "/queue/role/" + roomId,
                isPlayer ? RoomRole.PLAYER : RoomRole.SPECTATOR);
    }
    private Map<String, RoomRole> allocateSeatIfAvailable(String gameId, String playerName, SitAction sitAction) {
        return sitService.allocateSeatIfAvailable(gameId, playerName, sitAction);
    }

    private void sendRoomRolesToUser(String username, Map<String, RoomRole> roomRoles) {
        roomRoles.forEach((roomId, roomRole) -> {
            String destination = "/queue/role/" + roomId;
            simpMessagingTemplate.convertAndSendToUser(username, destination, roomRole);
        });
    }

}
