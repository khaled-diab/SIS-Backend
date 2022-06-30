package com.sis.dto.security;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class RegisterDTO {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String nationalityID;


}
