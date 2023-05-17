package ru.chmelev;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "testcontainers")
@Setter
@Slf4j
public class TestContainersConfig {

    private String databaseName;
    private String databaseUser;
    private String databasePassword;


    @Bean(initMethod = "start", destroyMethod = "stop")
    public PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>("postgres:14.5")
                .withDatabaseName(databaseName)
                .withUsername(databaseUser)
                .withPassword(databasePassword)
                .waitingFor(Wait.forListeningPort());
    }

    @Bean
    public DataSource dataSource(PostgreSQLContainer<?> postgreSQLContainer) {
        log.info("URL for postgres testcontainer: [{}]", postgreSQLContainer.getJdbcUrl());
        return DataSourceBuilder.create()
                .driverClassName(postgreSQLContainer.getDriverClassName())
                .password(postgreSQLContainer.getPassword())
                .url(postgreSQLContainer.getJdbcUrl())
                .username(postgreSQLContainer.getUsername())
                .build();
    }


}
