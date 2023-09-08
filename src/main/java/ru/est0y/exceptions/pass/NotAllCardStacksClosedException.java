package ru.est0y.exceptions.pass;

import ru.est0y.domain.CardStack;

import java.util.List;

public class NotAllCardStacksClosedException extends RuntimeException {
    public NotAllCardStacksClosedException(List<CardStack>stacks){
        super(stacks.toString());
    }
}
