package ru.est0y.exceptions.take;

public class FailedTakeException extends RuntimeException {
    public FailedTakeException(Exception e) {
        super(e);
    }

}
