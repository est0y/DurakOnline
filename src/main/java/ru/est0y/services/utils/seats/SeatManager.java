package ru.est0y.services.utils.seats;

import ru.est0y.domain.Seat;

import java.util.List;

public interface SeatManager {
    Seat getSeatByNumber(int number, List<Seat> seats);
}
