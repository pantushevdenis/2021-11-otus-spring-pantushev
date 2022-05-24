package ru.otus.spring.pantushev.services;

import java.util.Locale;

public interface MessageSourceService {
    String getMessage(String code, Object[] args);
    public Locale getLocale();

}
