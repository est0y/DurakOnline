package ru.est0y.services.actions.attack.filters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;
import ru.est0y.domain.playerActions.AttackAction;
import ru.est0y.exceptions.SeatHasNotCard;
import ru.est0y.services.actions.handle.ActionFilterService;
@Service
@AllArgsConstructor
public class AttackCardAvailabilityFilterService implements ActionFilterService<AttackAction> {

    @Override
    public void doFilter(Seat seat, Game game, AttackAction action) {
        boolean doesSeatHaveCard = seat.getCardsId().contains(action.attackCardId());
        if (!doesSeatHaveCard)throw new SeatHasNotCard(seat,action.attackCardId());

    }
}
