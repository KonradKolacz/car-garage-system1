package com.example.test04system1;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class BaseIT {
    private static final String RABBIT_DOCKER_IMAGE = "rabbitmq:3-management";
    private static final String QUEUE_NAME = "alert";
    private static final String MYSQL_DOCKER_IMAGE = "mysql:5.7";
    private static final String DATABASE_NAME = "mysql";
    private static final String MYSQL_USERNAME = "guest";
    private static final String MYSQL_PASSWORD = "guest123";



    @Container
    private static RabbitMQContainer rabbitMQContainer = new RabbitMQContainer(RABBIT_DOCKER_IMAGE)
            .withQueue(QUEUE_NAME);

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.host", rabbitMQContainer::getHost);
        registry.add("spring.rabbitmq.port", rabbitMQContainer::getAmqpPort);
    }


    @Container
    private static MySQLContainer<?> mysql = new MySQLContainer<>(MYSQL_DOCKER_IMAGE)
            .withDatabaseName(DATABASE_NAME)
            .withUsername(MYSQL_USERNAME)
            .withPassword(MYSQL_PASSWORD);

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

}
