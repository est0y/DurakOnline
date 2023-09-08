package ru.est0y.services.gameManagement.startGame;

import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.est0y.configs.props.GameProps;
import ru.est0y.domain.Game;
import ru.est0y.domain.cards.Suit;
import ru.est0y.repositories.CardRepository;

import java.util.*;
import java.util.stream.IntStream;
@Component
@Order(0)
@AllArgsConstructor
public class PlayingTableStart implements GameStartProcessor {
    private final GameProps gameProps;
    private final CardRepository cardRepository;

    @Override
    public void handle(Game game) {
        var table = game.getPlayingTable();
        table.setDeck(generateDeck());
        table.setTrump(getBottomCardSuit(table.getDeck()));
    }

    private List<Integer> generateDeck() {
        List<Integer> cardsList = new ArrayList<>(IntStream.rangeClosed(
                gameProps.getDeckStartValue(),
                gameProps.getDeckEndValue()
        ).boxed().toList());
        Collections.shuffle(cardsList);
        return cardsList;
    }
    private Suit getBottomCardSuit(List<Integer> deck){
        return cardRepository.findById(deck.get(deck.size()-1)).getSuit();
    }
}
