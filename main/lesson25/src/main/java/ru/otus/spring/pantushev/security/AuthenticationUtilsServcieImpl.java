package ru.otus.spring.pantushev.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.otus.spring.pantushev.dto.AuthenticationCommonPageDataDto;

@Service
public class AuthenticationUtilsServcieImpl implements AuthenticationUtilsService {
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public AuthenticationCommonPageDataDto toAuthenticationCommonPageDataDto(Authentication authentication) {
        AuthenticationCommonPageDataDto dto = new AuthenticationCommonPageDataDto();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails)principal;
            dto.setUsername(userDetails.getUsername());
            dto.setAnonymouse(false);
            userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .forEach(a -> {
                    if (AppAutorities.USER_READER.is(a))
                        dto.setUserReader(true);
                    else if (AppAutorities.USER_WRITER.is(a))
                        dto.setUserWriter(true);
                    else if (AppAutorities.ADMIN.is(a))
                        dto.setAdmin(true);

                });
        }
        else {
            String username = principal.toString();
            dto.setUsername(username);
            dto.setAnonymouse(true);
            dto.setUserReader(false);
            dto.setUserWriter(false);
            dto.setAdmin(false);
        }
        return dto;
    }
}
