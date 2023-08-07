package ru.est0y.services.actions.defence.filters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;
import ru.est0y.domain.playerActions.DefenceAction;
import ru.est0y.exceptions.defence.WeakCardException;
import ru.est0y.services.actions.handle.ActionFilterService;
import ru.est0y.services.cardServices.CardService;
import ru.est0y.services.utils.stacks.CardStacksUtils;


@Service
@AllArgsConstructor
public class DefenceActionFilterService implements ActionFilterService<DefenceAction> {
    private final CardService<Integer> cardIdService;
    private final CardStacksUtils cardStacksUtils;

    @Override
    public void doFilter(Seat seat, Game game, DefenceAction action) {
        var table = game.getPlayingTable();
        var openStack = table.getCardStacks().stream()
                .filter(s -> s.getNumber() == action.stackNumber() && s.getCardsId().size() == 1)
                .findFirst().orElseThrow();
        if (!cardIdService.isBeat(action.defenceCardId(), openStack.getCardsId().get(0), table.getTrump())) {
            throw new WeakCardException(action.defenceCardId(),openStack.getCardsId().get(0));
        }
    }
}
