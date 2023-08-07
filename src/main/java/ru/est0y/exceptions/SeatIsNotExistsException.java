package ru.est0y.exceptions;

public class SeatIsNotExistsException extends RuntimeException{
    public SeatIsNotExistsException(String gameId,int seatNumber){
        super("gameId:"+gameId+" seatNumber:"+seatNumber);

    }
}
