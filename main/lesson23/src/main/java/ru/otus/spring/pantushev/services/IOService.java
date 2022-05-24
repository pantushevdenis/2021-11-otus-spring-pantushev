package ru.otus.spring.pantushev.services;

import java.io.InputStream;
import java.io.PrintStream;

public interface IOService {
    InputStream getIn();
    PrintStream getOut();
}
