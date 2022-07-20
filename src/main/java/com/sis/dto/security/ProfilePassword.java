package com.sis.dto.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfilePassword {
    private  String userName;
    private  String oldPass;
    private  String newPass;
}
