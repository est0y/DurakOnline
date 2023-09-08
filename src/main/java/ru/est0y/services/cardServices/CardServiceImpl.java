package ru.est0y.services.cardServices;

import org.springframework.stereotype.Service;
import ru.est0y.domain.cards.Card;
import ru.est0y.domain.cards.Suit;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
@Service
public class CardServiceImpl implements CardService<Card> {


    @Override
    public boolean isBeat(Card attackingCard, Card cardOnStack, Suit trump) {
        if (attackingCard.getSuit() == cardOnStack.getSuit()) {
            return 0 < attackingCard.getValue().compareTo(cardOnStack.getValue());
        } else {
            return attackingCard.getSuit() == trump;
        }
    }

    @Override
    public Optional<Card> getMinTrumpCard(List<Card> cards, Suit trump) {
        return cards.stream()
                .filter(card -> card.getSuit() == trump)
                .min(Comparator.comparing(Card::getValue));
    }
}
