package com.danielwindel.config;

import com.danielwindel.chats.WebSocketChatHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketChatHandler webSocketChatHandler;

    public WebSocketConfig(WebSocketChatHandler webSocketChatHandler) {
        this.webSocketChatHandler = webSocketChatHandler;
    }

    private boolean isDeployedEnvironment() {
        return System.getProperty("DEPLOYED") == null;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        String webSocketEndpointUrl;

        if (isDeployedEnvironment()) {
            webSocketEndpointUrl = "wss://xplora.fly.dev/api/ws/chat/{id}";
        } else
            webSocketEndpointUrl = "api/ws/chat/{id}";

        registry
                .addHandler(webSocketChatHandler, webSocketEndpointUrl)
                .setAllowedOrigins("*");
    }

}
