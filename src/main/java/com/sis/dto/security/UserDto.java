package com.sis.dto.security;

import com.sis.dto.BaseDTO;
import com.sis.entity.security.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto extends BaseDTO {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String type;
    private String token;
    private Role role;
}
