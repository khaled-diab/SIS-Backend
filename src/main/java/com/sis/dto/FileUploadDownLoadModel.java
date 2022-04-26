package com.sis.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FileUploadDownLoadModel {

    private List<String> directories = new ArrayList<>();
    private String fileName;
    private Boolean removeOthers;
}
