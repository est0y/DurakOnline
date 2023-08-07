package ru.est0y.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "seats")
@AllArgsConstructor
@NoArgsConstructor
//@Data
@Setter
public class Seat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private int number;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;
    //@JdbcTypeCode(SqlTypes.)
    //@Enumerated(EnumType.STRING)
   // private Role role;
    private List<Integer> cardsId;
    private boolean isFree;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return number == seat.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", number=" + number +
                ", player=" + player +
                ", cardsId=" + cardsId +
                ", isFree=" + isFree +
                '}';
    }

    public boolean isFree() {
        return this.isFree;
    }
}
