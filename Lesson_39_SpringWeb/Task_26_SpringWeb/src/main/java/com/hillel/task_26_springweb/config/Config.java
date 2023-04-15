package com.hillel.task_26_springweb.config;

import com.hillel.task_26_springweb.entity.Order;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.hillel.task_26_springweb")
public class Config {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
