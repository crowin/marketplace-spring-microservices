package org.github.crowin.userservice.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.github.crowin.userservice.util.UserRole;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter @Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, insertable = false, updatable = false)
    private UserRole role;
}
