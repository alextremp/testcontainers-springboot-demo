package com.github.alextremp.testcontainersdemo.infrastructure;

import com.github.alextremp.testcontainersdemo.infrastructure.testcontainers.DockerizedInfrastructure;

public abstract class AbstractIntegrationTest {

  private static final DockerizedInfrastructure DOCKERIZED_INFRASTRUCTURE = new DockerizedInfrastructure();

  static {
    DOCKERIZED_INFRASTRUCTURE.start();
  }
}
