package ru.est0y.exceptions.attack;

import ru.est0y.domain.cards.Card;

public class NotFoundCardValueOnTable extends RuntimeException {
    public NotFoundCardValueOnTable(Card card) {
        super("Not exist same CardValue On Table for :" + card.toString());
    }
}
