package ru.est0y.repositories;

import ru.est0y.domain.cards.Card;
import ru.est0y.domain.cards.Suit;

import java.util.List;

public interface CardRepository {
    Card findById(int id);

    List<Card> findById(List<Integer> ids);

    List<Card>findBySuit(Suit suit);
}
