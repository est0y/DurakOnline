package ru.est0y.services.actions.defence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.est0y.domain.playerActions.DefenceAction;
import ru.est0y.exceptions.defence.FailedDefenceException;
import ru.est0y.repositories.GameRepository;
import ru.est0y.services.ExceptionWrapper;
import ru.est0y.services.actions.ActionCoordinator;
import ru.est0y.services.actions.defence.DefenceActionPipeline;
import ru.est0y.services.messaging.GameMessagingService;
import ru.est0y.services.utils.HibernateUtils;
@Service
@RequiredArgsConstructor
public class DefenceCoordinator implements ActionCoordinator<DefenceAction> {
    private final GameRepository gameRepository;
    private final DefenceActionPipeline defenceActionPipeline;
    private final GameMessagingService gameMessagingService;
    private final ExceptionWrapper exceptionWrapper;
    private final HibernateUtils hibernateUtils;

    @Transactional
    @Override
    public void coordinate(String gameId, String playerId, DefenceAction action) {
        exceptionWrapper.wrap(()->innerCoordinate(gameId,playerId,action), FailedDefenceException::new);
    }
    private void innerCoordinate(String gameId, String playerId, DefenceAction action) {
        var game = gameRepository.findByIdAndDefenderSeat_Player_Id(gameId, playerId).orElseThrow();
        hibernateUtils.doInSessionWithTransaction(session -> {
            defenceActionPipeline.run(game.getDefenderSeat(), game, action);
            session.merge(game);
        });
        gameMessagingService.sendToAll(game);
    }
}
