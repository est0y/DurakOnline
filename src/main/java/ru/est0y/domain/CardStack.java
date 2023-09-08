package ru.est0y.domain;

import lombok.*;


import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CardStack {

    private int number;
    private List<Integer> cardsId;
}
