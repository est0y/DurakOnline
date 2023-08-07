package ru.est0y.configs.props;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties("game")
@AllArgsConstructor
public class GameProps {
    private final int gamesCount;
    private final int seatsCount;
    private final int deckStartValue;
    private final int deckEndValue;
    private final int minHandOnNewTurn;

}
