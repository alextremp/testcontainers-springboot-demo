package com.github.alextremp.testcontainersdemo.infrastructure.configuration;

import com.github.alextremp.testcontainersdemo.domain.message.MessageFactory;
import com.github.alextremp.testcontainersdemo.domain.message.MessageRepository;
import com.github.alextremp.testcontainersdemo.domain.message.ReceiveMessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

  @Bean
  MessageFactory messageFactory() {
    return new MessageFactory();
  }

  @Bean
  ReceiveMessageService receiveMessageService(MessageFactory messageFactory, MessageRepository messageRepository) {
    return new ReceiveMessageService(messageFactory, messageRepository);
  }

}
