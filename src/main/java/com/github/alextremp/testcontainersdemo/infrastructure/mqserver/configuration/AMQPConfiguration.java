package com.github.alextremp.testcontainersdemo.infrastructure.mqserver.configuration;

import com.github.alextremp.testcontainersdemo.domain.message.ReceiveMessageService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguration {

  @Bean
  Queue messageDispatchedQueue(
        @Value("${mq-server.routing.message.queue.dispatched}") String messageDispatchedQueue) {
    return new Queue(messageDispatchedQueue, false);
  }

  @Bean
  TopicExchange topicExchange(
        @Value("${mq-server.routing.exchange}") String exchangeName) {
    return new TopicExchange(exchangeName);
  }

  @Bean
  Binding binding(
        Queue messageDispatchedQueue,
        TopicExchange topicExchange) {
    return BindingBuilder.bind(messageDispatchedQueue).to(topicExchange).with(messageDispatchedQueue.getName());
  }

  @Bean
  SimpleMessageListenerContainer messageDispatchedConsumer(
        ConnectionFactory connectionFactory,
        MessageListenerAdapter receiveTextListenerAdapter,
        @Value("${mq-server.routing.message.queue.dispatched}") String messageDispatchedQueue) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(messageDispatchedQueue);
    container.setConcurrentConsumers(1);
    container.setExclusive(true);
    container.setMessageListener(receiveTextListenerAdapter);
    return container;
  }

  @Bean
  MessageListenerAdapter receiveTextListenerAdapter(ReceiveMessageService receiveMessageService) {
    return new MessageListenerAdapter(receiveMessageService, "receiveText");
  }

}
