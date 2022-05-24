package ru.otus.spring.pantushev.domain.security;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User implements UserDetails {
    @Id
    @Column(name ="UserName")
    private String username;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Enabled", nullable = false)
    private byte enabled;

    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    private List<Authority> authorities = new ArrayList<>();

    public User(String username, String password, byte enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) && password.equals(user.password) && enabled == user.enabled;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, enabled);
    }

   @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return (enabled != 0);
    }
}
