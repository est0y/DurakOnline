package ru.est0y.exceptions.attack;

public class FailedAttackException extends RuntimeException {
    public FailedAttackException(Exception e) {
        super(e);
    }

}
