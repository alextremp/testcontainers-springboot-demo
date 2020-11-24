package com.github.alextremp.testcontainersdemo.infrastructure;

import com.github.alextremp.testcontainersdemo.infrastructure.testcontainers.DockerizedInfrastructure;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;

public class IntegrationTest {

  private static final DockerizedInfrastructure DOCKERIZED_INFRASTRUCTURE = new DockerizedInfrastructure();

  @BeforeAll
  static void dockerComposeUp() {
    DOCKERIZED_INFRASTRUCTURE.start();
  }

  @AfterAll
  static void dockerComposeDown() {
    DOCKERIZED_INFRASTRUCTURE.stop();
  }

  @Nested
  class Application extends ApplicationTestCase {
  }
}
