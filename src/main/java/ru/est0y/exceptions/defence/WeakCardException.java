package ru.est0y.exceptions.defence;

public class WeakCardException extends RuntimeException {
    public WeakCardException(int defenceCardId, int cardOnStackId) {
        super(String.format("Card with id:%d does not beat card with id:%d",
                defenceCardId,
                cardOnStackId));
    }

}
