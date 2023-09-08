package ru.est0y.services.actions.defence;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;
import ru.est0y.domain.playerActions.DefenceAction;
import ru.est0y.services.actions.ActionService;
import ru.est0y.services.utils.stacks.CardStacksUtils;

@Service
@AllArgsConstructor
@Slf4j
public class DefenceActionService implements ActionService<DefenceAction> {
    private final CardStacksUtils cardStacksUtils;

    @Override
    public void act(Seat seat, Game game, DefenceAction action) {
        //todo проверить существует ли stack
        var defenceCardId = action.defenceCardId();
        var stackNumber = action.stackNumber();
        var table = game.getPlayingTable();
        seat.getCardsId().remove((Integer) defenceCardId);
        var selectedStack = cardStacksUtils.getStackByNumber(stackNumber, table.getCardStacks());
        selectedStack.getCardsId().add(defenceCardId);
        log.info(String.format("Seat with number %s defence %d->[%d]%d",
                seat.getNumber(),
                defenceCardId,
                stackNumber,
                selectedStack.getCardsId().get(0)));
    }
}
