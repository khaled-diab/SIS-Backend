package com.sis.dto.security;

import com.sis.dto.BaseDTO;
import com.sis.dto.UserFileDto;
import com.sis.entity.security.Role;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private String password;
    private List<UserFileDto> userFileList = new ArrayList<>();

}
