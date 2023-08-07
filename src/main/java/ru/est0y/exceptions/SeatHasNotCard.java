package ru.est0y.exceptions;

import ru.est0y.domain.Seat;

public class SeatHasNotCard extends RuntimeException {
    public SeatHasNotCard(Seat seat,int cardId) {
        super(String.format("%s does not have a card with id: %d",seat.toString(),cardId));
    }

}
