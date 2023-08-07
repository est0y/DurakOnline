package ru.est0y.services.actions.attack.filters;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Game;
import ru.est0y.domain.Seat;
import ru.est0y.domain.playerActions.AttackAction;
import ru.est0y.exceptions.attack.NotFoundCardValueOnTable;
import ru.est0y.repositories.CardRepository;
import ru.est0y.services.actions.handle.ActionFilterService;

@Service
@AllArgsConstructor
@Slf4j
public class AttackCardValueFilterService implements ActionFilterService<AttackAction> {

    private final CardRepository cardRepository;

    @Override
    public void doFilter(Seat seat, Game game, AttackAction action) {
        //todo check role?
        //todo проверить можно ли игроку еще подкидывать
        //todo и после атаки заканчивать ход если статус игры DEFENDER_TAKES и больше нельзя подкидывать
        if (game.getPlayingTable().getCardStacks().size() == 0) {
            return;
        }
        var attackCard = cardRepository.findById(action.attackCardId());
        boolean isExistSameCardValueOnTable = game.getPlayingTable().getCardStacks().stream()
                .flatMap(s -> s.getCardsId().stream())
                .map(cardRepository::findById)
                .anyMatch(card -> card.getValue() == attackCard.getValue());
        if (!isExistSameCardValueOnTable) throw new NotFoundCardValueOnTable(attackCard);
    }
}
