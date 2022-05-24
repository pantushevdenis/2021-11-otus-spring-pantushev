package ru.otus.spring.pantushev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationCommonPageDataDto {
    private String username;
    private boolean isAnonymouse;
    private boolean isUserReader;
    private boolean isUserWriter;
    private boolean isAdmin;
}
