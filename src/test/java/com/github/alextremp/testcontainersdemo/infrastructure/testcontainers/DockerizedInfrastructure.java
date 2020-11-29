package com.github.alextremp.testcontainersdemo.infrastructure.testcontainers;

import java.io.File;
import javax.annotation.PreDestroy;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.DockerComposeContainer;

import static java.lang.String.format;

@Component
public class DockerizedInfrastructure {

  private static final String MESSAGE_STORE_DB_SERVICE = "message-store-db";
  private static final Integer MESSAGE_STORE_DB_PORT = 5432;
  private static final String SPRING_MESSAGE_STORE_DB_PORT = "message-store-db.port";

  private static final String MQ_SERVER_SERVICE = "mq-server";
  private static final Integer MQ_SERVER_PORT = 5672;
  private static final String SPRING_MQ_SERVER_PORT = "mq-server.port";

  private final DockerComposeContainer dockerServices;

  public DockerizedInfrastructure(ConfigurableApplicationContext configurableApplicationContext) {
    // Inicializamos los servicios de docker-compose
    dockerServices = new DockerComposeContainer<>(new File("docker-compose.yml"))
          .withLocalCompose(true)
          .withExposedService(MESSAGE_STORE_DB_SERVICE, MESSAGE_STORE_DB_PORT)
          .withExposedService(MQ_SERVER_SERVICE, MQ_SERVER_PORT);

    // Arrancamos los servicios dockerizados
    dockerServices.start();

    // Refrescamos el contexto de Spring con los puertos designados
    TestPropertyValues.of(
          format("%s=%s",
                SPRING_MESSAGE_STORE_DB_PORT,
                dockerServices.getServicePort(MESSAGE_STORE_DB_SERVICE, MESSAGE_STORE_DB_PORT)),
          format("%s=%s",
                SPRING_MQ_SERVER_PORT,
                dockerServices.getServicePort(MQ_SERVER_SERVICE, MQ_SERVER_PORT))
    ).applyTo(configurableApplicationContext.getEnvironment());
  }

  @PreDestroy
  void preDestroy() {
    // Apagamos los servicios dockerizados
    dockerServices.stop();
  }
}
