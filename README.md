# TestContainers en el contexto de SpringBootTest

Este proyecto es la base del post [Integrando TestContainers en el contexto de Spring en nuestros tests](https://dev.to/adevintaspain/integrando-testcontainers-en-el-contexto-de-spring-en-nuestros-tests-5b7d) para jugar con distintas formas de integrar TestContainers en el flujo de ejecución de tests con [Junit5](https://junit.org/junit5/docs/current/user-guide/) para un servicio desarrollado con [Spring Boot](https://spring.io/projects/spring-boot), dejando al final que sea Spring quien haga el trabajo por nosotros, aprovechándonos de las características del ciclo de vida del ApplicationContext durante la ejecución de los tests, focalizado en la asignación de puertos dinámicos para las infraestructuras externas levantadas localmente para los tests.

Como referencia a las pruebas comentadas en el post, se puede consultar los cambios que aplicarían en cada caso en estas PR:

> - :confused: _[TestContainers integrados con JUnit](https://github.com/alextremp/testcontainers-springboot-demo/pull/3/files)_
> - :rocket: _[TestContainers singleton gestionados manualmente](https://github.com/alextremp/testcontainers-springboot-demo/pull/2/files)_
> - :heart_eyes: _[TestContainers gestionados por Spring](https://github.com/alextremp/testcontainers-springboot-demo/pull/1/files)_


La rama master es operativa pero sin TestContainers, por lo que para poder ejecutar los tests debe levantarse las infraestructuras externas con docker compose:

```
docker-compose up -d
./gradlew clean test
```

