package ru.est0y.domain.state;

import lombok.Builder;
import lombok.Data;
import ru.est0y.domain.Role;

import java.util.List;
@Data
@Builder
public class SeatState {
    private int number;
    private String playerName;
    private List<Integer> cardsId;
    private int cardsCount;
    private Role role;
    private boolean free;
}
