package ru.est0y.services.cardServices;

import ru.est0y.domain.Game;

public interface CardDispenser {
    void dealCardsIdForSeatsToLimit(Game game);
}
