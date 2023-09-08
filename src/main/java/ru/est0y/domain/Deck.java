package ru.est0y.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Deck {
    @Id
    private final int id;
    private List<Long> sequenceCardsId;

    @Override
    public String toString() {
        return "Deck{" +
                "id='" + id + '\'' +
                ", sequenceCardsId=" + sequenceCardsId +
                '}';
    }
}
