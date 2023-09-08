package ru.est0y.services;

import ru.est0y.domain.Game;

import java.util.Optional;

public interface GameService {
    Optional<Game> getById(String gameId);
    Game save(Game game);
    Game getByIdAndPlayerId(String gameId,String playerId);
    Game getByIdAndAttackerPlayerId(String gameId,String playerId);
    Game getByIdAndDefenderPlayerId(String gameId,String playerId);
}
