package ru.est0y.domain.state;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import ru.est0y.domain.GameStatus;

import java.util.List;
@Data
@Builder
public class GameState {
     private int yourSeatNumber;
     private GameStatus status;
     @Singular
     private List<SeatState>seats;
     private PlayingTableState playingTable;

}
