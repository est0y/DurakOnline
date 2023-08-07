package ru.est0y.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "players")
@AllArgsConstructor
//@Data
@NoArgsConstructor
@Getter
public class Player {
    @Id
    private String id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
   /*   @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;*/
}
