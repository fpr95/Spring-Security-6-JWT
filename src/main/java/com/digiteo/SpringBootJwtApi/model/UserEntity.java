package com.digiteo.SpringBootJwtApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "userEntity")
@Table(name = "users")
// the implementation of UserDetails in the entity is just for the demo purposes
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String pwd;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(
            insertable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    private LocalDateTime timestamp;

    public UserEntity(
            Long id,
            String name,
            String pwd,
            String email,
            LocalDateTime timestamp) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }
}
