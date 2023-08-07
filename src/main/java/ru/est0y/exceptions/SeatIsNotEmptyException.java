package ru.est0y.exceptions;

public class SeatIsNotEmptyException extends RuntimeException{
    public SeatIsNotEmptyException(String gameId,int seatNumber){
        super("gameId:"+gameId+" seatNumber:"+seatNumber);

    }
}
