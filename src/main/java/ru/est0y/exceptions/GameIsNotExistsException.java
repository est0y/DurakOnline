package ru.est0y.exceptions;

public class GameIsNotExistsException extends RuntimeException{
    public GameIsNotExistsException(String gameId){
        super(gameId);
    }
}
