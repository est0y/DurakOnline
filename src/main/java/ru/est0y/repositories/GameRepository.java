package ru.est0y.repositories;


import org.springframework.data.repository.CrudRepository;
import ru.est0y.domain.Game;

import java.util.Optional;

public interface GameRepository extends CrudRepository<Game, String> {

    //  @Query("SELECT g FROM Game g JOIN g.seats s WHERE g.id=:gameId AND s.player.id = :playerId")
    // Optional<Game> findByGameIdAndPlayerId(@Param("gameId") String gameId,@Param("playerId") String playerId);
    // @Query("SELECT g FROM Game g WHERE g.id=:gameId AND g.attackerSeat.player.id = :playerId")
    // Optional<Game> findByGameIdAndAttackerSeatWithPlayerId(@Param("gameId") String gameId,@Param("playerId") String playerId);
    //@Query("SELECT g FROM Game g WHERE g.id=:gameId AND g.defenderSeat.player.id = :playerId")
    //Optional<Game> findByGameIdAndDefenderSeatWithPlayerId(@Param("gameId") String gameId,@Param("playerId") String playerId);
    //@Query("SELECT g FROM Game g JOIN g.seats s WHERE g.id=:gameId AND s.player.id = :playerId")
    Optional<Game> findBySeats_Player_Id( String playerId);
    Optional<Game> findByIdAndSeats_Player_Id(String gameId, String playerId);

    Optional<Game> findByIdAndAttackerSeat_Player_Id(String gameId, String playerId);

    Optional<Game> findByIdAndDefenderSeat_Player_Id(String gameId, String playerId);

    boolean existsByIdAndSeats_Player_Id(String gameId, String playerId);

}
