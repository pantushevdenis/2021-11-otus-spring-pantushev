package ru.otus.spring.pantushev.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AppAutorities {
    USER_READER("ROLE_USER_READER")
    , USER_WRITER("ROLE_USER_WRITER")
    , ADMIN("ROLE_ADMIN")
    ;

    private final String appAutorities;

    public boolean is(String autority) {
        return this.appAutorities.equals(autority);
    }

    @Override
    public String toString() {
        return appAutorities;
    }
}
