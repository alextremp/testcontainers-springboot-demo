package com.github.alextremp.testcontainersdemo.infrastructure;

import java.io.File;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static java.lang.String.valueOf;
import static java.lang.System.setProperty;

@SpringBootTest
@Testcontainers
abstract class AbstractIntegrationTest {

  private static final String MESSAGE_STORE_DB_SERVICE = "message-store-db";
  private static final Integer MESSAGE_STORE_DB_PORT = 5432;
  private static final String SPRING_MESSAGE_STORE_DB_PORT = "message-store-db.port";

  private static final String MQ_SERVER_SERVICE = "mq-server";
  private static final Integer MQ_SERVER_PORT = 5672;
  private static final String SPRING_MQ_SERVER_PORT = "mq-server.port";


  @Container
  private static DockerComposeContainer dockerServices =
        new DockerComposeContainer<>(new File("docker-compose.yml"))
              .withLocalCompose(true)
              .withExposedService(MESSAGE_STORE_DB_SERVICE, MESSAGE_STORE_DB_PORT)
              .withExposedService(MQ_SERVER_SERVICE, MQ_SERVER_PORT);

  @BeforeAll
  public static void beforeAll() {
    setProperty(SPRING_MESSAGE_STORE_DB_PORT, valueOf(dockerServices.getServicePort(MESSAGE_STORE_DB_SERVICE, MESSAGE_STORE_DB_PORT)));
    setProperty(SPRING_MQ_SERVER_PORT, valueOf(dockerServices.getServicePort(MQ_SERVER_SERVICE, MQ_SERVER_PORT)));
  }
}
