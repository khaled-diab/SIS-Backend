package com.sis.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserFileDto extends BaseDTO {
    private String directories;
    private String fileName;
    private String type;
    private Date uploadDate;
}
