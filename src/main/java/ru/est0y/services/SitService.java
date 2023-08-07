package ru.est0y.services;

import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.est0y.domain.Player;
import ru.est0y.domain.Seat;
import ru.est0y.domain.messages.RoomRole;
import ru.est0y.domain.messages.SitAction;
import ru.est0y.exceptions.SeatIsNotExistsException;
import ru.est0y.repositories.GameRepository;
import ru.est0y.repositories.PlayerRepository;
import ru.est0y.repositories.SeatRepository;
import ru.est0y.services.gameManagement.GameStateService;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class SitService {
    private final GameRepository gameRepository;
    private final SeatRepository seatRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final PlayerRepository playerRepository;
    private final GameStateService gameStateService;

    @Transactional
    public Map<String, RoomRole> takeSeatIfFree(String gameId, String playerId, SitAction sitAction) {
        Map<String, RoomRole> roomRoles = new HashMap<>();
        var player = playerRepository.findById(playerId).orElse(new Player(playerId, sitAction.name()));
        var seat = seatRepository.findByGameIdAndNumberAndFree(gameId, sitAction.seatNumber())
                .orElseThrow(() -> new SeatIsNotExistsException(gameId, sitAction.seatNumber()));
        leaveOldSeatIfExists(player, roomRoles);
        sitOnSeat(gameId,seat,player,roomRoles);

        return roomRoles;
    }
    private void sitOnSeat(String targetGameId,Seat targetSeat,Player player,Map<String, RoomRole> roomRoles){
        roomRoles.put(targetGameId, RoomRole.PLAYER);
        targetSeat.setFree(false);
        targetSeat.setPlayer(player);
    }

    private void leaveOldSeatIfExists(Player player, Map<String, RoomRole> roomRoles) {
        gameRepository.findBySeats_Player_Id(player.getId()).ifPresent(oldGame -> {
            var oldSeat = oldGame.getSeats().stream()
                    .filter(s -> player.equals(s.getPlayer()))
                    .findFirst().orElseThrow();
            roomRoles.put(oldGame.getId(), RoomRole.SPECTATOR);
            oldSeat.setFree(true);
            oldSeat.setPlayer(null);
        });
    }
}
