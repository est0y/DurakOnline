package ru.est0y.services.actions.attack;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.est0y.domain.Game;
import ru.est0y.domain.playerActions.AttackAction;
import ru.est0y.exceptions.GameIsNotExistsException;
import ru.est0y.exceptions.attack.FailedAttackException;
import ru.est0y.repositories.GameRepository;
import ru.est0y.services.ExceptionWrapper;
import ru.est0y.services.actions.ActionCoordinator;
import ru.est0y.services.actions.attack.AttackActionPipeline;
import ru.est0y.services.messaging.GameMessagingService;
import ru.est0y.services.utils.HibernateUtils;

import java.util.function.Consumer;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AttackCoordinator implements ActionCoordinator<AttackAction> {
    private final GameRepository gameRepository;
    private final AttackActionPipeline attackActionPipeline;
    private final GameMessagingService gameMessagingService;
    private final ExceptionWrapper exceptionWrapper;
    private final HibernateUtils hibernateUtils;

    @Transactional
    @Override
    public void coordinate(String gameId, String playerId, AttackAction action) {
        exceptionWrapper.wrap(()->innerCoordinate(gameId,playerId,action), FailedAttackException::new);
    }

    private void innerCoordinate(String gameId, String playerId, AttackAction action) {
        var game = gameRepository.findByIdAndAttackerSeat_Player_Id(gameId, playerId).orElseThrow();
        hibernateUtils.doInSessionWithTransaction(session -> {
            attackActionPipeline.run(game.getAttackerSeat(), game, action);
            session.merge(game);
        });
        gameMessagingService.sendToAll(game);
    }

}
