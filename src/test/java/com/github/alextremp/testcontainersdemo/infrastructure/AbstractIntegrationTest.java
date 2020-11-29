package com.github.alextremp.testcontainersdemo.infrastructure;

import com.github.alextremp.testcontainersdemo.infrastructure.testcontainers.DockerizedInfrastructure;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {DockerizedInfrastructure.class, Application.class})
abstract class AbstractIntegrationTest {
}
