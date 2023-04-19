package com.hillel.task_27_springdata.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
@PropertySource("classpath:application-test.properties")
public class DbConfig {
    @Autowired
    private final Environment environment;

    @Bean
    public DataSource getSqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        String URL = environment.getProperty("spring.url");
        String USERNAME = environment.getProperty("spring.user.name");
        String PASSWORD = environment.getProperty("spring.password");

        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getSqlDataSource());
    }
}
