package ru.otus.spring.pantushev.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.otus.spring.pantushev.dto.AuthenticationCommonPageDataDto;
import ru.otus.spring.pantushev.entities.Author;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorsControllerTest {
    private static final String AUTHOR_NAME = "AuthorName1";
    private static final Author AUTHOR = new Author(1L, AUTHOR_NAME);
    private static final String USER_NAME = "UserName1";
    private static final AuthenticationCommonPageDataDto AUTHENTICATION_COMMON_PAGE_DATA_DTO = new AuthenticationCommonPageDataDto(USER_NAME, false, true, true, true);

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
    }

    @WithAnonymousUser
    @Test
    public void shouldRedirectingToLoginForAnon() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/authors").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is3xxRedirection());
    }


    @WithMockUser(
        username = "User1",
        authorities = {"ROLE_USER_READER"}
    )
    @Test
    public void shouldGrantedForReader() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/authors").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}