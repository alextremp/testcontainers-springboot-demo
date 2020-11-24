package com.github.alextremp.testcontainersdemo.infrastructure.testcontainers;

import java.io.File;
import org.testcontainers.containers.DockerComposeContainer;

import static java.lang.String.valueOf;
import static java.lang.System.setProperty;

public class DockerizedInfrastructure {

  private static final String MESSAGE_STORE_DB_SERVICE = "message-store-db";
  private static final Integer MESSAGE_STORE_DB_PORT = 5432;
  private static final String SPRING_MESSAGE_STORE_DB_PORT = "message-store-db.port";

  private static final String MQ_SERVER_SERVICE = "mq-server";
  private static final Integer MQ_SERVER_PORT = 5672;
  private static final String SPRING_MQ_SERVER_PORT = "mq-server.port";

  private final DockerComposeContainer dockerServices;

  public DockerizedInfrastructure() {
    // Inicializamos los servicios de docker-compose
    dockerServices = new DockerComposeContainer<>(new File("docker-compose.yml"))
          .withLocalCompose(true)
          .withExposedService(MESSAGE_STORE_DB_SERVICE, MESSAGE_STORE_DB_PORT)
          .withExposedService(MQ_SERVER_SERVICE, MQ_SERVER_PORT);
  }

  public void start() {
    // Arrancamos los servicios dockerizados
    dockerServices.start();

    // Seteamos System properties para propiedades del contexto de Spring
    setProperty(SPRING_MESSAGE_STORE_DB_PORT, valueOf(dockerServices.getServicePort(MESSAGE_STORE_DB_SERVICE, MESSAGE_STORE_DB_PORT)));
    setProperty(SPRING_MQ_SERVER_PORT, valueOf(dockerServices.getServicePort(MQ_SERVER_SERVICE, MQ_SERVER_PORT)));
  }

  public void stop() {
    // Apagamos los servicios dockerizados
    dockerServices.stop();
  }
}
