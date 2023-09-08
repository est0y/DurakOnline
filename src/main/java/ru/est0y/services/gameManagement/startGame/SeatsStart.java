package ru.est0y.services.gameManagement.startGame;

import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.est0y.configs.props.GameProps;
import ru.est0y.domain.Game;
import ru.est0y.services.cardServices.CardDispenser;

import java.util.ArrayList;
@Component
@Order(1)
@AllArgsConstructor
public class SeatsStart implements GameStartProcessor {
private final GameProps gameProps;
    @Override
    public void handle(Game game) {
        var deck = game.getPlayingTable().getDeck();
        game.getSeats().forEach(s -> {
            var sublist = deck.subList(0, gameProps.getMinHandOnNewTurn());
            s.setCardsId(new ArrayList<>(sublist));
            sublist.clear();
        });
    }
}
