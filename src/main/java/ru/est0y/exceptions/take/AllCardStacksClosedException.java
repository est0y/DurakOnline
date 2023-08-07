package ru.est0y.exceptions.take;

import ru.est0y.domain.CardStack;

import java.util.List;

public class AllCardStacksClosedException extends RuntimeException {
    public AllCardStacksClosedException(List<CardStack> stack) {
        super(stack.toString());
    }
}
