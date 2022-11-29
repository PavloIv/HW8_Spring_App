package com.ip.hw8.entity;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Embeddable;
@NoArgsConstructor
@Embeddable
public class Role implements GrantedAuthority {
    private String name;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}