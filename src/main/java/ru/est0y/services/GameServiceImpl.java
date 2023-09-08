package ru.est0y.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.repositories.GameRepository;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    @Override
    public Optional<Game> getById(String gameId) {
        return gameRepository.findById(gameId);
    }

    @Override
    public Game save(Game game) {
       return gameRepository.save(game);
    }

    @Override
    public Game getByIdAndPlayerId(String gameId, String playerId) {
        return null;
    }

    @Override
    public Game getByIdAndAttackerPlayerId(String gameId, String playerId) {
        return null;
    }

    @Override
    public Game getByIdAndDefenderPlayerId(String gameId, String playerId) {
        return null;
    }
}
