package com.github.alextremp.testcontainersdemo.infrastructure;

import com.github.alextremp.testcontainersdemo.infrastructure.messagestoredb.repository.mybatis.MessageMapper;
import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.jdbc.Sql;

import static java.lang.String.format;
import static org.awaitility.Awaitility.await;

@Sql("/fixtures/message-store-db/clean.sql")
class ApplicationTest extends AbstractIntegrationTest {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private MessageMapper messageMapper;

  @Value("${mq-server.routing.exchange}")
  private String demoExchange;

  @Value("${mq-server.routing.message.queue.dispatched}")
  private String messageDispatchedQueue;

  @Test
  void shouldSaveEmittedMessage() {
    String givenText = "hello world";
    rabbitTemplate.convertAndSend(demoExchange, messageDispatchedQueue, givenText);

    await().atMost(Duration.ONE_SECOND)
          .alias(format("message [%s] has been saved", givenText))
          .until(() -> messageMapper.selectOneByText(givenText).isPresent());
  }

}