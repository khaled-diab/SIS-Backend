package com.sis.dto;

import com.sis.dto.security.UserDto;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto extends BaseDTO {
    private UserDto user;
}
