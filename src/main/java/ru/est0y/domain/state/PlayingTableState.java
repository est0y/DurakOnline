package ru.est0y.domain.state;

import lombok.Builder;
import lombok.Data;
import ru.est0y.domain.CardStack;
import ru.est0y.domain.cards.Suit;

import java.util.List;
@Builder
@Data
public class PlayingTableState {
    private Suit trump;
    private int cardsCountInDeck;
    private int bottomCard;
    private List<CardStack> cardStacks;
}
