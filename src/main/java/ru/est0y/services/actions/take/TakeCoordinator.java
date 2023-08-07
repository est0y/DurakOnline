package ru.est0y.services.actions.take;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.est0y.domain.playerActions.TakeAction;
import ru.est0y.exceptions.take.FailedTakeException;
import ru.est0y.repositories.GameRepository;
import ru.est0y.services.ExceptionWrapper;
import ru.est0y.services.actions.SimpleActionCoordinator;
import ru.est0y.services.messaging.GameMessagingService;
import ru.est0y.services.utils.HibernateUtils;

@Service
@RequiredArgsConstructor
public class TakeCoordinator implements SimpleActionCoordinator<TakeAction> {
    private final GameRepository gameRepository;
    private final TakeActionPipeline takeActionPipeline;
    private final GameMessagingService gameMessagingService;
    private final ExceptionWrapper exceptionWrapper;
    private final HibernateUtils hibernateUtils;
    @Transactional
    @Override
    public void coordinate(String gameId, String playerId) {
        exceptionWrapper.wrap(() -> innerCoordinate(gameId, playerId), FailedTakeException::new);
    }

    private void innerCoordinate(String gameId, String playerId) {
        var game = gameRepository.findByIdAndDefenderSeat_Player_Id(gameId, playerId).orElseThrow();
        hibernateUtils.doInSessionWithTransaction(session -> {
            takeActionPipeline.run(game);
            session.merge(game);
        });
        gameMessagingService.sendToAll(game);
    }
}
