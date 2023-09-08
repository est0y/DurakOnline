package ru.est0y.repositories;

import org.springframework.stereotype.Component;
import ru.est0y.domain.cards.Card;
import ru.est0y.domain.cards.CardValue;
import ru.est0y.domain.cards.Suit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CardRepositoryImpl implements CardRepository {
    private final static Map<Integer, Card> cards = new HashMap<>();

    static {
        int counter = 1;
        for (var cardValue : CardValue.values()) {
            for (var suit : Suit.values()) {
                cards.put(counter, new Card(suit, cardValue));
                counter++;
            }
        }
    }

    @Override
    public Card findById(int id) {
        return cards.get(id);
    }

    @Override
    public List<Card> findById(List<Integer> ids) {
        return ids.stream().map(this::findById).toList();
    }

    @Override
    public List<Card> findBySuit(Suit suit) {
        return cards.values().stream().filter(card -> card.getSuit().equals(suit)).toList();
    }
}
