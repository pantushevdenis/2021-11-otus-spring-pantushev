package ru.otus.spring.pantushev.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(
    prefix = "debugsettings"
 )
@Component
public class DebugSettings {
    private boolean showInfo;
    private boolean showH2Console;
}
