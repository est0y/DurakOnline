package ru.est0y.services.gameManagement;

import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.Role;
import ru.est0y.domain.Seat;
import ru.est0y.domain.state.GameState;
import ru.est0y.domain.state.PlayingTableState;
import ru.est0y.domain.state.SeatState;

import java.util.List;

@Service
public class GameStateService {
    public GameState getGameStateForSpectator(Game game) {
        var gameStateBuilder = getGameStateBuilder(game).yourSeatNumber(0);
        game.getSeats().forEach(seat ->
                gameStateBuilder.seat(getSeatStateBuilder(game, seat).build())
        );
        return gameStateBuilder.build();
    }

    public GameState getGameStateForPlayer(Game game, int seatNumber) {
        Seat seat = game.getSeats().stream().filter(s -> s.getNumber() == seatNumber).findFirst().orElseThrow();
        return getGameStateForPlayer(game, seat);
    }

    public GameState getGameStateForPlayer(Game game, Seat seat) {
        var gameStateBuilder = getGameStateBuilder(game).yourSeatNumber(seat.getNumber());
        game.getSeats().forEach(s -> gameStateBuilder.
                seat(getSeatStateBuilder(game, s)
                        .cardsId(s.getNumber() == seat.getNumber() ? seat.getCardsId() : List.of())
                        .build()));
        return gameStateBuilder.build();
    }

    private GameState.GameStateBuilder getGameStateBuilder(Game game) {
        var table = game.getPlayingTable();
        var bottomCardId = table.getDeck().size() < 1 ? 0 : table.getDeck().get(table.getDeck().size() - 1);
        var cardsCountInDeck=table.getDeck().size();
        return GameState.builder()
                .status(game.getGameStatus())
                .playingTable(
                        PlayingTableState.builder()
                                .trump(table.getTrump())
                                .cardsCountInDeck(cardsCountInDeck)
                                .bottomCard(bottomCardId)
                                .cardStacks(table.getCardStacks())
                                .build()
                );
    }

    private SeatState.SeatStateBuilder getSeatStateBuilder(Game game, Seat seat) {

        return SeatState.builder()
                .number(seat.getNumber())
                .playerName(seat.getPlayer() == null ? "Empty" : seat.getPlayer().getName())
                .free(seat.isFree())
                .cardsCount(seat.getCardsId().size())
                .role(getPlayerRole(game, seat));
    }

    private Role getPlayerRole(Game game, Seat seat) {
        if (game.getAttackerSeat().getNumber() == seat.getNumber()) {
            return Role.ATTACK;
        } else if (game.getDefenderSeat().getNumber() == seat.getNumber()) {
            return Role.DEFENCE;
        } else {
            return Role.WAITING;
        }
    }
}
