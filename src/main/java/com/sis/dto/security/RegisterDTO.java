package com.sis.dto.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class RegisterDTO {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String nationalityID;


}
