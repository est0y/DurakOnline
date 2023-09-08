package ru.est0y.services.actions.attack.filters;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;
import ru.est0y.domain.playerActions.AttackAction;
import ru.est0y.exceptions.attack.ExceededNumberOfAttacksException;
import ru.est0y.services.actions.handle.ActionFilterService;
import ru.est0y.services.utils.games.GameUtils;


@Service
@AllArgsConstructor
@Slf4j
public class DefenderHaveCardsChecker implements ActionFilterService<AttackAction> {
    private final GameUtils gameUtils;

    @Override
    public void doFilter(Seat seat, Game game, AttackAction action) {
        var countAttacksLeft = gameUtils.getAvailableAttacksCount(game);
        if (countAttacksLeft == 0) {
            throw new ExceededNumberOfAttacksException();
        }
    }
}
