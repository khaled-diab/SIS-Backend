package com.sis.entities.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sis.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "type")
    private String type;

    @Column(name = "token")
    private String token;

    @NotNull
    @Column(name = "password")
    @JsonIgnore
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

}
