package ru.est0y.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import ru.est0y.domain.playerActions.AttackAction;
import ru.est0y.domain.playerActions.DefenceAction;
import ru.est0y.domain.playerActions.PassAction;
import ru.est0y.domain.playerActions.TakeAction;
import ru.est0y.services.actions.ActionCoordinator;
import ru.est0y.services.actions.SimpleActionCoordinator;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ActionsController {
    private final ActionCoordinator<AttackAction> attackCoordinator;
    private final ActionCoordinator<DefenceAction> defenceCoordinator;
    private final SimpleActionCoordinator<PassAction> passCoordinator;
    private final SimpleActionCoordinator<TakeAction> takeCoordinator;

    @MessageMapping("/game/id.{gameId}/attack/card.{cardId}")
    public void attack(Principal principal, @DestinationVariable String gameId, @DestinationVariable int cardId) {
        attackCoordinator.coordinate(gameId, principal.getName(), new AttackAction(cardId));
    }

    @MessageMapping("/game/id.{gameId}/defence")
    public void defence(Principal principal, @DestinationVariable String gameId, DefenceAction defenceAction) {
        defenceCoordinator.coordinate(gameId, principal.getName(), defenceAction);
    }

    @MessageMapping("/game/id.{gameId}/pass")
    public void passAction(Principal principal, @DestinationVariable String gameId) {
        passCoordinator.coordinate(gameId, principal.getName());
    }

    @MessageMapping("/game/id.{gameId}/take")
    public void takeAction(Principal principal, @DestinationVariable String gameId) {
        takeCoordinator.coordinate(gameId, principal.getName());
    }


}
