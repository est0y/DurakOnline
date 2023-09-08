package ru.est0y.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import jakarta.persistence.Table;

import java.util.*;

@Entity
@Table(name = "games")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {
    @Id
    @Column(name = "id")
    private String id;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
   @JoinColumn(name = "game_id")
   @OrderBy("number ASC")
    private List<Seat> seats;

   @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attacker_seat_id")
    private Seat attackerSeat;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "defender_seat_id")
    private Seat defenderSeat;

    @JdbcTypeCode(SqlTypes.JSON)
    private PlayingTable playingTable;

    private GameStatus gameStatus;
    private int passCount;
    public void incrementPassCount(){
        passCount++;
    }
}
