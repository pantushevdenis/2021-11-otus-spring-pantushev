package ru.otus.spring.pantushev.services;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableConfigurationProperties
@ComponentScan({"ru.otus.spring.pantushev.services"})
public class TestContextConfig {
}
