package ru.otus.spring.pantushev.security;

import org.springframework.security.core.Authentication;
import ru.otus.spring.pantushev.dto.AuthenticationCommonPageDataDto;

public interface AuthenticationUtilsService {
    AuthenticationCommonPageDataDto getAuthenticationCommonPageDataDto(Authentication authentication);
}
