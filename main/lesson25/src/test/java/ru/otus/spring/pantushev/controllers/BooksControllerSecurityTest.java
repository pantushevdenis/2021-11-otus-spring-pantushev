package ru.otus.spring.pantushev.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
class BooksControllerTest {
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

    //getBooks ------------------------------------

    @WithAnonymousUser
    @Test
    public void getBooks_shouldRedirectingToLoginForAnon() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is3xxRedirection());
    }


    @WithMockUser(
        username = "User2",
        authorities = {"ROLE_USER_READER"}
    )
    @Test
    public void getBooks_shouldOkToLoginForUserReader() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @WithMockUser(
        username = "User1",
        authorities = {"ROLE_USER_READER", "ROLE_USER_WRITER"}
    )
    @Test
    public void getBooks_shouldOkToLoginForUserWriter() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @WithMockUser(
        username = "Admin",
        authorities = {"ROLE_USER_READER", "ROLE_USER_WRITER", "ROLE_ADMIN"}
    )
    @Test
    public void getBooks_shouldOkToLoginForAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }



    //getBooksEdit -----------------

    @WithAnonymousUser
    @Test
    public void getBookssEdit_shouldRedirectingToLoginForAnon() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books/edit?id=1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is3xxRedirection());
    }


    @WithMockUser(
        username = "User2",
        authorities = {"ROLE_USER_READER"}
    )
    @Test
    public void getBooksEdit_should403ToLoginForUserReader() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books/edit?id=1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(403));
    }

    @WithMockUser(
        username = "User1",
        authorities = {"ROLE_USER_READER", "ROLE_USER_WRITER"}
    )
    @Test
    public void getBooksEdit_shouldOkToLoginForUserWriter() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books/edit?id=1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @WithMockUser(
        username = "Admin",
        authorities = {"ROLE_USER_READER", "ROLE_USER_WRITER", "ROLE_ADMIN"}
    )
    @Test
    public void getBooksEdit_shouldOkToLoginForAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books/edit?id=1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    //getBooksAdd --------------------------------------

    @WithAnonymousUser
    @Test
    public void getBooksAdd_shouldRedirectingToLoginForAnon() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books/add").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is3xxRedirection());
    }


    @WithMockUser(
        username = "User2",
        authorities = {"ROLE_USER_READER"}
    )
    @Test
    public void getBooksAdd_should403ToLoginForUserReader() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books/add").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(403));
    }

    @WithMockUser(
        username = "User1",
        authorities = {"ROLE_USER_READER", "ROLE_USER_WRITER"}
    )
    @Test
    public void getbooksAdd_shouldOkToLoginForUserWriter() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books/add").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @WithMockUser(
        username = "Admin",
        authorities = {"ROLE_USER_READER", "ROLE_USER_WRITER", "ROLE_ADMIN"}
    )
    @Test
    public void getBooksAdd_shouldOkToLoginForAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books/add").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    //postBooksSave --------------------------------------

    @WithAnonymousUser
    @Test
    public void postBooksSave_shouldRedirectingToLoginForAnon() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/books/save").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(403));
    }


    @WithMockUser(
        username = "User2",
        authorities = {"ROLE_USER_READER"}
    )
    @Test
    public void postBooksSave_should403ToLoginForUserReader() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/books/save").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(403));
    }

    @WithMockUser(
        username = "User1",
        authorities = {"ROLE_USER_READER", "ROLE_USER_WRITER"}

    )
    @Test
    public void postBooksSave_shouldOkToLoginForUserWriter() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/books/save").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @WithMockUser(
        username = "Admin",
        authorities = {"ROLE_USER_READER", "ROLE_USER_WRITER", "ROLE_ADMIN"}
    )
    @Test
    public void postBooksSave_shouldOkToLoginForAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/books/edit").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }



    //getBooksDelete --------------------------------------

    @WithAnonymousUser
    @Test
    public void getBooksSave_shouldRedirectingToLoginForAnon() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books/delete").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is3xxRedirection());
    }


    @WithMockUser(
        username = "User2",
        authorities = {"ROLE_USER_READER"}
    )
    @Test
    public void getBooksDelete_should403ToLoginForUserReader() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books/delete").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(403));
    }

    @WithMockUser(
        username = "User1",
        authorities = {"ROLE_USER_READER", "ROLE_USER_WRITER"}
    )
    @Test
    public void getBooksDelete_should403ToLoginForUserWriter() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books/delete").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(403));
    }

    @WithMockUser(
        username = "Admin",
        authorities = {"ROLE_USER_READER", "ROLE_USER_WRITER", "ROLE_ADMIN"}
    )
    @Test
    public void getBooksDelete_shouldOkToLoginForAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books/delete").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }


}