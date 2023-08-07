package ru.est0y.domain;

import jakarta.persistence.*;
import lombok.*;
import ru.est0y.domain.cards.Suit;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayingTable {
    List<Integer> deck;
    Suit trump;
    List<CardStack> cardStacks;
}
