package ru.est0y.services.actions.pass;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.est0y.domain.playerActions.PassAction;
import ru.est0y.exceptions.pass.FailedPassException;
import ru.est0y.repositories.GameRepository;
import ru.est0y.services.ExceptionWrapper;
import ru.est0y.services.actions.SimpleActionCoordinator;
import ru.est0y.services.actions.pass.PassActionPipeline;
import ru.est0y.services.messaging.GameMessagingService;
import ru.est0y.services.utils.HibernateUtils;

@Service
@RequiredArgsConstructor
public class PassCoordinator implements SimpleActionCoordinator<PassAction> {
    private final GameRepository gameRepository;
    private final PassActionPipeline passActionPipeline;
    private final GameMessagingService gameMessagingService;
    private final ExceptionWrapper exceptionWrapper;
    private final HibernateUtils hibernateUtils;
    @Transactional
    @Override
    public void coordinate(String gameId, String playerId) {
        exceptionWrapper.wrap(() -> innerCoordinate(gameId, playerId), FailedPassException::new);
    }

    private void innerCoordinate(String gameId, String playerId) {
        var game = gameRepository.findByIdAndAttackerSeat_Player_Id(gameId, playerId).orElseThrow();
        hibernateUtils.doInSessionWithTransaction(session -> {
            passActionPipeline.run(game);
            session.merge(game);
        });
        gameMessagingService.sendToAll(game);
    }
}
