package com.xishanqu.redpacket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-07-13
 * STOMP是一个简单的可互操作的协议，通常被用于通过中间服务器在客户端之间进行异步消息传递
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //表示定义一个前缀，并开启sockjs支持
        registry.addEndpoint("/chat").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //表示设置消息代理的前缀
        registry.enableSimpleBroker("/topic","/queue");
        //表示配置一个或多个前缀，通过这些前缀过滤出被注解方法的消息
        registry.setApplicationDestinationPrefixes("/app");
    }
}
