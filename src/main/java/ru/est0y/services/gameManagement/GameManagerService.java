package ru.est0y.services.gameManagement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.configs.props.GameProps;
import ru.est0y.domain.*;
import ru.est0y.domain.cards.Suit;
import ru.est0y.repositories.CardRepository;
import ru.est0y.services.cardServices.CardService;
import ru.est0y.services.GameService;
import ru.est0y.services.gameManagement.startGame.GameStarter;

import java.util.*;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class GameManagerService {
    private final GameProps gameProps;
    private final GameService gameService;
    private final CardService<Integer> cardIdService;
    private final CardRepository cardRepository;
    private GameStarter gameStarter;

    public void startGame(Game game) {
        //TODO переделать на chain of responceability
        gameStarter.start(game);
        gameService.save(game);
    }

    public Game createGame(String gameId) {
        return gameService.save(
                new Game(
                        gameId,
                        createSeats(gameProps.getSeatsCount()),
                        null,
                        null,
                        new PlayingTable(
                                createDeck(gameProps.getDeckStartValue(), gameProps.getDeckEndValue()),
                                null,
                                List.of()),
                        null,
                        0
                )
        );
    }

    private List<Seat> createSeats(int count) {
        List<Seat> seats = new ArrayList<>();
        for (int seatNumber = 1; seatNumber <= count; seatNumber++) {
            seats.add(new Seat(null, seatNumber, null, List.of(), true));
        }
        return seats;
    }

    private List<Integer> createDeck(int startValue, int endValue) {
        List<Integer> cardsList = new ArrayList<>(IntStream.rangeClosed(startValue, endValue).boxed().toList());
        Collections.shuffle(cardsList);
        return cardsList;
    }

    public void createAndStartGame(String gameId) {
        startGame(createGame(gameId));
    }

    private Optional<Seat> getFirstMoveSeat(List<Seat> seats, Suit trump) {
        return seats.stream().min((seat1, seat2) -> {
            var minTrumpCard1 = cardIdService.getMinTrumpCard(seat1.getCardsId(), trump);
            var minTrumpCard2 = cardIdService.getMinTrumpCard(seat2.getCardsId(), trump);
            if (minTrumpCard1.isEmpty() && minTrumpCard2.isPresent()) {
                return -1;
            } else if (minTrumpCard1.isPresent() && minTrumpCard2.isEmpty()) {
                return 1;
            }
            return minTrumpCard1.map(card -> card.getValue().compareTo(minTrumpCard2.get().getValue())).orElse(0);
        });
    }
}
