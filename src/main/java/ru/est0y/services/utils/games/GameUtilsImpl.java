package ru.est0y.services.utils.games;

import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;

@Service
public class GameUtilsImpl implements GameUtils {
    @Override
    public long getAvailableAttacksCount(Game game) {
        var openStackCount = game.getPlayingTable().getCardStacks().stream()
                .filter(s -> s.getCardsId().size() == 1).count();
        var cardCountDefender = game.getDefenderSeat().getCardsId().size();
        return cardCountDefender - openStackCount;
    }
}
