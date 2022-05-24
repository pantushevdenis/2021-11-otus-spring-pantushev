package ru.otus.spring.pantushev.services;

import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.spring.pantushev.properties.DebugSettings;

@Component
public class CommandLimeRunnerImpl implements CommandLineRunner {
    private DebugSettings debugSettings;

    @Autowired
    public CommandLimeRunnerImpl(DebugSettings debugSettings) {
        this.debugSettings = debugSettings;
    }

    @Override
    public void run(String... args) throws Exception {
        if (debugSettings.isShowH2Console()) {
            Console.main(args);
        }
    }
}
