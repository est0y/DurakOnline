package ru.est0y.services.cardServices;

import ru.est0y.domain.cards.Card;
import ru.est0y.domain.cards.Suit;

import java.util.List;
import java.util.Optional;

public interface CardService <T>{
    boolean isBeat(T card, T cardOnStack, Suit suit);

    Optional<Card> getMinTrumpCard(List<T> cards, Suit trump);
}
