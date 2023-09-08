package ru.est0y.services.actions.turnManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;

import java.util.ArrayList;
import java.util.List;
@SpringBootTest
class TurnManagerImplTest {
    @Autowired
    private TurnManagerImpl turnManager ;

    @Test
    void nextAttacker() {
        var seats = seats(3);
        var seatWithNum2 = seats.get(1);
        var seatWithNum3 = seats.get(2);
        var game = new Game(null, seats, seatWithNum2, seatWithNum3, null,null,0);
        var result = turnManager.nextAttacker(game);
        Assertions.assertEquals(1, result.getNumber());
    }

    @Test
    void nextAttacker2() {
        var seats = seats(3);
        var seatWithNum1 = seats.get(0);
        var seatWithNum2 = seats.get(1);
        var game = createGame(seats, seatWithNum1, seatWithNum2);
        var result = turnManager.nextAttacker(game);
        Assertions.assertEquals(3, result.getNumber());
    }

    @Test
    void nextAttacker3() {
        var seats = seats(3);
        var seatWithNum3 = seats.get(2);
        var seatWithNum1 = seats.get(0);
        var game = createGame(seats, seatWithNum3, seatWithNum1);
        var result = turnManager.nextAttacker(game);
        Assertions.assertEquals(2, result.getNumber());
    }
    @Test
    void nextAttacker4() {
        var seats = seats(5);
        var seatWithNum2 = seats.get(1);
        var seatWithNum4 = seats.get(3);
        var game = createGame(seats, seatWithNum2, seatWithNum4);
        var result = turnManager.nextAttacker(game);
        Assertions.assertEquals(3, result.getNumber());
    }
    @Test
    void nextAttacker5() {
        var seats = seats(5);
        var seatWithNum3 = seats.get(2);
        var seatWithNum2 = seats.get(1);
        var game = createGame(seats, seatWithNum3, seatWithNum2);
        var result = turnManager.nextAttacker(game);
        Assertions.assertEquals(4, result.getNumber());
    }
    private Game createGame(List<Seat> seats, Seat attacker, Seat defender) {
        return new Game(null, seats, attacker, defender, null,null,0);

    }

    private List<Seat> seats(int count) {
        List<Seat> seats = new ArrayList<>();
        for (var i = 1; i <= count; i++) {
            seats.add(new Seat(null, i, null, List.of(), true));
        }
        return seats;
    }
}