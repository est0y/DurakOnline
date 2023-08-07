package ru.est0y.services;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ExceptionWrapperImpl implements ExceptionWrapper {
    @Override
    public void wrap(Runnable runnable, Function<Exception,RuntimeException> function) {
        try {
            runnable.run();
        } catch (Exception e) {
            throw function.apply(e);
        }
    }
}
