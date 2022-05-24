package ru.otus.spring.pantushev.entities.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AUTHORITIES")
@IdClass(AuthorityKey.class)
public class Authority implements GrantedAuthority {
    @Id
    @Column(name = "UserName")
    private String userName;

    @Id
    @Column(name = "Authority")
    private String authority;

    @ManyToOne()
    @JoinColumn(
        name = "UserName",
        referencedColumnName = "UserName",
        insertable = false,
        updatable = false
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

}
