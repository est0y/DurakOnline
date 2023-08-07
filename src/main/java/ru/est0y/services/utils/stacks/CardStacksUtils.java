package ru.est0y.services.utils.stacks;

import ru.est0y.domain.CardStack;

import java.util.List;

public interface CardStacksUtils {
    CardStack getStackByNumber(int number, List<CardStack> stacks);
    List<Integer> takeAllCardsId(List<CardStack> stacks);
    long getOpenStacksCount(List<CardStack> stacks);
    boolean isExistOpenStacks(List<CardStack> stacks);

}
