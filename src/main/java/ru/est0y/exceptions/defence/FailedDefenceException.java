package ru.est0y.exceptions.defence;

public class FailedDefenceException extends RuntimeException {
    public FailedDefenceException(Exception e) {
        super(e);
    }

}
