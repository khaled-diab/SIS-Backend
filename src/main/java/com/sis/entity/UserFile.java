package com.sis.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user_file")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFile extends BaseEntity {

    private String directories;
    private String fileName;
    private String type;
    private String status;
    private Date uploadDate;
}
