package ru.est0y.domain.cards;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Card {
    private final Suit suit;
    private final CardValue value;

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", value='" + value + '\'' +
                '}';
    }
}
