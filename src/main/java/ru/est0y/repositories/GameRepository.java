package ru.est0y.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.est0y.domain.Game;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, String> {

    Optional<Game> findBySeats_Player_Id( String playerId);
    Optional<Game> findByIdAndSeats_Player_Id(String gameId, String playerId);

    Optional<Game> findByIdAndAttackerSeat_Player_Id(String gameId, String playerId);

    Optional<Game> findByIdAndDefenderSeat_Player_Id(String gameId, String playerId);

    boolean existsByIdAndSeats_Player_Id(String gameId, String playerId);

}
