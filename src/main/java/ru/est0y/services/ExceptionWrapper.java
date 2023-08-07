package ru.est0y.services;

import java.util.function.Function;

public interface ExceptionWrapper {
    void wrap(Runnable runnable, Function<Exception,RuntimeException> function);
}
