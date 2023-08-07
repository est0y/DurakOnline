package ru.est0y.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

/*@Entity
@Table(name = "card_stacks")*/
@NoArgsConstructor
@Data
@AllArgsConstructor
public class CardStack {

 /*   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;*/
    private int number;
    private List<Integer> cardsId;
}
