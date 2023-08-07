package ru.est0y.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@RequiredArgsConstructor
@Slf4j
public class WebSocketDisconnectListener implements ApplicationListener<SessionDisconnectEvent> {


    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
     log.info("user disconnect with session id:"+event.getSessionId());
        //logger.info("user disconnect with id:"+ Objects.requireNonNull(event.getUser()).getName());

    }
}