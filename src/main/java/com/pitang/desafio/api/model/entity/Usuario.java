package com.pitang.desafio.api.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Carlos H. de Oliveira - carlos.h.oliveira@cho.eti.br
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "created_at", "last_login" }, allowGetters = true)

public class Usuario implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * The 'e-mail' field is working as 'username'
     */
    @Email(message = "Invalid fields")
    @NotBlank(message = "Missing fields")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "Invalid Fields")
    @NotBlank(message = "Missing fields")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "Invalid Fields")
    @NotBlank(message = "Missing fields")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "Invalid Fields")
    @NotBlank(message = "Missing fields")
    @Column(nullable = false)
    private String lastName;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at;

    @CreatedDate
    @Column(name = "last_login", nullable = false, updatable = true)
    private LocalDateTime last_login;

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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