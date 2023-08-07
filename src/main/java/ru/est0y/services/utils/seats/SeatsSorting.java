package ru.est0y.services.utils.seats;

import ru.est0y.domain.Seat;

import java.util.List;

public interface SeatsSorting  {
    List<Seat> sortToDealCards(List<Seat> seats,Seat attackingSeat,Seat defendingSeat);

}
