package com.sis.entity.mapper;

import com.sis.dto.UserFileDto;
import com.sis.entity.UserFile;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class UserFileMapper implements Mapper<UserFile, UserFileDto> {

    @Override
    public UserFileDto toDTO(UserFile userFile) {
        return UserFileDto
                .builder().directories(userFile.getDirectories()).fileName(userFile.getFileName()).type(userFile.getType()).uploadDate(userFile.getUploadDate())
                .build();
    }

    @Override
    public UserFile toEntity(UserFileDto userFileDto) {
        return UserFile
                .builder().directories(userFileDto.getDirectories()).fileName(userFileDto.getFileName()).type(userFileDto.getType()).uploadDate(userFileDto.getUploadDate())
                .build();
    }

    @Override
    public ArrayList<UserFileDto> toDTOs(Collection<UserFile> e) {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<UserFile> toEntities(Collection<UserFileDto> userFileDtos) {
        return new ArrayList<>();
    }


    public List<UserFileDto> toDTOsOptional(Optional<Collection<UserFile>> userFiles) {
        return userFiles.map(files -> files.stream().map(this::toDTO).collect(Collectors.toList())).orElse(null);
    }


    public List<UserFile> toEntitiesOptional(Optional<Collection<UserFileDto>> userFileDtos) {
        return userFileDtos.map(files -> files.stream().map(this::toEntity).collect(Collectors.toList())).orElse(null);
    }

    @Override
    public PageResult<UserFileDto> toDataPage(PageResult<UserFile> entity) {
        return new PageResult<>(entity.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<UserFileDto>::new)), entity.getTotalCount(), entity.getPageSize(), entity.getCurrPage());
    }
}
