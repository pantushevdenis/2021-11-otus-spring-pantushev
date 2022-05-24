package ru.otus.spring.pantushev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;

@Service
public class IOServiceSys
    implements IOService
{
    private final InputStream in = System.in;
    private final PrintStream out = System.out;

    @Override
    public InputStream getIn() {
        return in;
    }

    @Override
    public PrintStream getOut() {
        return out;
    }
}
