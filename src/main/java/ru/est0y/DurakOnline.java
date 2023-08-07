package ru.est0y;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DurakOnline {
    public static void main(String[] args) {
        var context = SpringApplication.run(DurakOnline.class, args);
        context.getBean(GameProducer.class).createGames();
    }
}
