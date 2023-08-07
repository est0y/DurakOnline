package ru.est0y.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.est0y.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, String> {
}
