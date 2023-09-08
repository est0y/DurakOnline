package ru.est0y.exceptions.pass;

public class NoAttacksException extends RuntimeException{
    public NoAttacksException(){
        super("No attack actions were taken");
    }
}
