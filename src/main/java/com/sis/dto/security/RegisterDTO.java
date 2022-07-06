package com.sis.dto.security;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class RegisterDTO {

    private String englishName;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String nationalityID;
    private Date birthDate;


}
