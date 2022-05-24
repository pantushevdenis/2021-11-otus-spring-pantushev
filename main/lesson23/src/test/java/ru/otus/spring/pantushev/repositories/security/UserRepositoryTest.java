package ru.otus.spring.pantushev.repositories.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.pantushev.domain.security.Authority;
import ru.otus.spring.pantushev.domain.security.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DisplayName("repository User")
@DataJpaTest
class UserRepositoryTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("EntityContext инициализирован")
    void shoulInitiateentityManager() {
        assertNotNull(em);
    }

    @Test
    @DisplayName("бин userRepository инициализирован")
    void shouldInitiatedUserRepository() {
        assertNotNull(userRepository);
    }

    @Test
    @DisplayName("Список одержит только три определенных юзера (роли должны грузиться сразу)")
    void shouldReturnGetAll3Users() {
        User ua = new User("Admin", "Admin123", (byte) 1);
        ua.setAuthorities(Arrays.asList(
            new Authority(ua.getUsername(), "ROLE_ADMIN", ua),
            new Authority(ua.getUsername(), "ROLE_USER", ua)
        ));

        User u1 = new User("User1", "123", (byte) 1);
        u1.setAuthorities(Arrays.asList(
            new Authority(ua.getUsername(), "ROLE_USER", u1)
        ));

        User u2 = new User("User2", "123", (byte) 1);
        u2.setAuthorities(Arrays.asList(
            new Authority(ua.getUsername(), "ROLE_USER", u2)
        ));

        Iterable<User> actualList = userRepository.findAll();
        assertThat(actualList)
            .containsExactlyInAnyOrder(
                ua,
                u1,
                u2
            );
    }

    @Test
    @DisplayName("Печать")
    void shouldToString() {
        Iterable<User> actualList = userRepository.findAll();
        actualList.forEach(o -> {
            System.out.println(o);
        });


    }



}
