package org.com.dungeonpoc.chat.router;

import java.util.Map;
import org.com.dungeonpoc.chat.handler.ChatWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;

@Configuration
public class WebSocketRouter {
    @Bean
    public HandlerMapping handlerMapping(ChatWebSocketHandler handler) {
        return new SimpleUrlHandlerMapping(Map.of("/wss/chat", handler), -1);
    }

}
