package ru.otus.spring.pantushev.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix="questionsdao")
@Component
public class QuestionsDaoConfig {
    private ResourceCSVConfig resourcecsv;

    @Data
    public static class ResourceCSVConfig {
        private String filename;
    }
}
