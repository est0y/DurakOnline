package ru.est0y.services.cardServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.cards.Card;
import ru.est0y.domain.cards.Suit;
import ru.est0y.repositories.CardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CardsIdService implements CardService<Integer> {
    private final CardService<Card> cardService;
    private final CardRepository cardRepository;

    @Override
    public boolean isBeat(Integer cardId, Integer cardOnStackId, Suit suit) {
        var card = cardRepository.findById(cardId);
        var cardOnStack = cardRepository.findById(cardOnStackId);
        return cardService.isBeat(card, cardOnStack, suit);
    }


    @Override
    public Optional<Card> getMinTrumpCard(List<Integer> cardsId, Suit trump) {
        return cardService.getMinTrumpCard(cardRepository.findById(cardsId), trump);
    }

    List<Integer> takeTopCardsId(int count, List<Integer> cardsId) {
        if (cardsId.size() < count) {
            count = cardsId.size();
        }
        var sublist = cardsId.subList(0, count);
        var result = new ArrayList<>(sublist);
        sublist.clear();
        return result;
    }
}
