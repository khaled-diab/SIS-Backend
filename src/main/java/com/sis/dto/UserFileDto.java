package com.sis.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserFileDto extends BaseDTO {
    private String directories;
    private String fileName;
    private String type;
}
