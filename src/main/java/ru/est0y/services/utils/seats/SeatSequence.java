package ru.est0y.services.utils.seats;

import ru.est0y.domain.Seat;

import java.util.List;

public interface SeatSequence {
    Seat getNextSeat(Seat seat, List<Seat>seats);
}
