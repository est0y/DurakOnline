package ru.est0y.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.est0y.domain.Seat;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
   // @Query(value = "SELECT s.* ,p.name, p.principal_id FROM seats s JOIN players p ON p.id = s.player_id where s.game_id=:gameId",nativeQuery = true)
   /* @Query(value = "SELECT * FROM seats s WHERE s.game_id = :gameId AND s.number = :number LIMIT 1",nativeQuery = true)
    Optional<Seat> findByGameIdAndNumber(@Param("gameId")String gameId, @Param("number")int number);
*/
    //@Query("FROM Seat s WHERE s.player.principalId=:principalId")
    Optional<Seat> findByPlayerId(String playerId);
    @Query(value = "SELECT * FROM seats WHERE game_id=:gameId AND number=:number",nativeQuery = true)
    Optional<Seat> findByGameIdAndNumber(@Param("gameId")String gameId,@Param("number")int number);
    @Query(value = "SELECT * FROM seats WHERE  game_id=:gameId AND number=:number AND is_free=true",nativeQuery = true)
    Optional<Seat> findByGameIdAndNumberAndFree(@Param("gameId")String gameId,@Param("number")int number);
}
