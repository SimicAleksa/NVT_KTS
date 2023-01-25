package com.example.nvt_kts_back.configurations;
/*
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketForChatConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/chatSocket")
                .setAllowedOrigins("http://localhost:4200") //ovde je pitanje sta staviti, da li se misli na root adresu ili na tacnu adresu
                .withSockJS();
    }

    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.enableSimpleBroker("/chat-updates"); // opet ovo se verovatno menja na tacnu lokaciju nase mape
    }
}
*/