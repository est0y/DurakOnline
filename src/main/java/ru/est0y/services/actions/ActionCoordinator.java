package ru.est0y.services.actions;

public interface ActionCoordinator<T> {
    void coordinate(String gameId,String playerId,T action);
}
