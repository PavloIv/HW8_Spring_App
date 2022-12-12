package com.ip.hw8.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ip.hw8.anotation.Duplicate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Duplicate(id = "id", email = "email")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email")
    @Email(message = "email is not correct")
    @NotBlank(message = "email can not be empty")
    private String email;
    @Column(name = "password")
    @JsonIgnore
    @Length(min = 6, message = "password must be 6 or more symbols")
    @NotBlank(message = "password can not be empty")
    private String password;
    @Column(name = "active")
    private boolean active;
    @Column(name = "first_name")
    @NotBlank(message = "firstname can not be empty")
    private String firstName;
    @Column(name = "last_name")
    @NotBlank(message = "lastname can not be empty")
    private String lastName;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "id"))
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
        return isActive();
    }

}
