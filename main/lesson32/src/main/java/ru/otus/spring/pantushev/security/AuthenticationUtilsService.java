package ru.otus.spring.pantushev.security;

import org.springframework.security.core.Authentication;
import ru.otus.spring.pantushev.dto.AuthenticationCommonPageDataDto;

public interface AuthenticationUtilsService {
    Authentication getAuthentication();
    AuthenticationCommonPageDataDto toAuthenticationCommonPageDataDto(Authentication authentication);
}
