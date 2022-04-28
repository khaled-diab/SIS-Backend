package com.sis.dto.security;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class LoginDTO {

    private String username;
    private String password;

}
