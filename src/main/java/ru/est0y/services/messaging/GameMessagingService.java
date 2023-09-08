package ru.est0y.services.messaging;

import ru.est0y.domain.Game;

public interface GameMessagingService {
    void sendToAll(Game game);
    void sendToUser(Game game,String userId);
}
