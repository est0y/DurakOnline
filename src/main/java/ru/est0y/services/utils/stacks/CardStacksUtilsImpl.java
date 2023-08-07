package ru.est0y.services.utils.stacks;

import org.springframework.stereotype.Service;
import ru.est0y.domain.CardStack;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardStacksUtilsImpl implements CardStacksUtils {
    @Override
    public CardStack getStackByNumber(int number, List<CardStack> stacks) {
        return stacks.get(number - 1);
    }

    @Override
    public List<Integer> takeAllCardsId(List<CardStack> stacks) {
        var allCardsId = new ArrayList<>(stacks.stream().flatMap(stack -> stack.getCardsId().stream()).toList());
        stacks.clear();
        return allCardsId;
    }

    @Override
    public long getOpenStacksCount(List<CardStack> stacks) {
        return stacks.stream().filter(stack -> stack.getCardsId().size() == 1).count();
    }

    @Override
    public boolean isExistOpenStacks(List<CardStack> stacks) {
        return stacks.stream().anyMatch(s -> s.getCardsId().size() == 1);
    }
}
