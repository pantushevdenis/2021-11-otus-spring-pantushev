package ru.otus.spring.pantushev.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix="interface")
@Component
public class UserInterfaceConfig {
    String locale;
}
