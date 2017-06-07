package com.james.entity;

import com.james.enumeration.AuthorityName;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by james on 6/1/17.
 */
@Entity(name = "authority")
@Table(name = "authority")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long authorityId;

    @Basic
    @Column(name = "authority", nullable = false, unique = true, length = 45)
    @Enumerated(EnumType.STRING)
    private AuthorityName authority;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<User> users;

    public Authority() {
        ;
    }

    public Authority(AuthorityName authority) {
        this.authority = authority;
    }

    @JsonIgnore
    public Authority(AuthorityName authority, Set<User> users) {
        this.authority = authority;
        this.users = users;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(long authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public String getAuthority() {
        return authority.name();
    }

    public void setAuthority(String authority) {
        this.authority = AuthorityName.valueOf(authority);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
