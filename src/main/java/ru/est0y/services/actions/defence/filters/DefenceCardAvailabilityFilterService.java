package ru.est0y.services.actions.defence.filters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;
import ru.est0y.domain.playerActions.DefenceAction;
import ru.est0y.exceptions.SeatHasNotCard;
import ru.est0y.services.actions.handle.ActionFilterService;

@Service
@AllArgsConstructor
public class DefenceCardAvailabilityFilterService implements ActionFilterService<DefenceAction> {


    @Override
    public void doFilter(Seat seat, Game game, DefenceAction action) {
        boolean doesSeatHaveCard = seat.getCardsId().contains(action.defenceCardId());
        if (!doesSeatHaveCard){
            throw new SeatHasNotCard(seat,action.defenceCardId());
        }

    }
}
