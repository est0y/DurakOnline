package ru.est0y.exceptions.pass;

public class FailedPassException extends RuntimeException {
    public FailedPassException(Exception e) {
        super(e);
    }

}
