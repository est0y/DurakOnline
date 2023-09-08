package ru.est0y.exceptions.defence;

import ru.est0y.domain.GameStatus;

public class WrongPhaseException extends RuntimeException {
    public WrongPhaseException(GameStatus nowStatus, GameStatus expected){
        super(String.format("Now %s game status, but expected %s",nowStatus,expected));
    }
}
