package ru.est0y.services.utils.seats;

import org.springframework.stereotype.Service;
import ru.est0y.domain.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatsUtilsImpl implements SeatsUtils {
    @Override
    public List<Seat> sortToDealCards(List<Seat> seats, Seat attackingSeat, Seat defendingSeat) {

        var attackerSeatNumber = attackingSeat.getNumber();
        var defendingSeatNumber = defendingSeat.getNumber();
        var seatsCount = seats.size();
        var seatByNumber = seats.stream().collect(Collectors.toMap(Seat::getNumber, s -> s));
        List<Seat> sortList = new ArrayList<>();
        for (var i = defendingSeatNumber + 1; i <= seatsCount; i++) {
            if (i == attackerSeatNumber) break;
            sortList.add(seatByNumber.get(i));
        }
        if (sortList.size() == seatsCount) return sortList;
        int limit = sortList.size() > 1 ? sortList.get(0).getNumber() : attackerSeatNumber;
        for (var i = 1; i < limit; i++) {
            //if (i == defendingSeatNumber) continue;
            if (i == attackerSeatNumber) continue;
            sortList.add(seatByNumber.get(i));
        }
        sortList.add(0, attackingSeat);
        sortList.add(defendingSeat);
        return sortList;
    }

    @Override
    public Seat getNextSeat(Seat seat, List<Seat> seats) {
        var nextSeatNumber = seat.getNumber() == seats.size() ? 1 : seat.getNumber() + 1;
       // return seats.stream().filter(s -> s.getNumber() == nextSeatNumber).findFirst().orElseThrow();
        return getSeatByNumber(nextSeatNumber,seats);
    }

    @Override
    public Seat getSeatByNumber(int number, List<Seat> seats) {
        return seats.get(number - 1);
    }
}
