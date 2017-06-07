package com.james.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * Created by james on 6/1/17.
 */
@Entity(name = "user")
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Basic
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @Basic
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "authority_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "user")
    private Set<Todo> todos;

    public User()
    {
        ;
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User(String username, String password, boolean enabled, Set<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends Authority> getAuthorities() {
        return authorities;
    }

    public Set<Todo> getTodos() {
        return todos;
    }

    public void setTodos(Set<Todo> todos) {
        this.todos = todos;
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
}
