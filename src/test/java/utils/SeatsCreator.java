package utils;

import ru.est0y.domain.Seat;

import java.util.ArrayList;
import java.util.List;

public class SeatsCreator {
    public List<Seat> getSeats(int count) {
        List<Seat> seats = new ArrayList<>();
        for (var i = 1; i <= count; i++) {
            seats.add(new Seat(null, i, null, new ArrayList<>(), true));
        }
        return seats;
    }
}
