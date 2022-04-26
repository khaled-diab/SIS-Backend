package com.sis.entity.mapper;

import com.sis.dto.UserFileDto;
import com.sis.entity.UserFile;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class UserFileMapper implements Mapper<UserFile, UserFileDto> {

    @Override
    public UserFileDto toDTO(UserFile userFile) {
        return UserFileDto.builder().directories(userFile.getDirectories()).fileName(userFile.getFileName()).build();
    }

    @Override
    public UserFile toEntity(UserFileDto userFileDto) {
        return UserFile.builder().directories(userFileDto.getDirectories()).fileName(userFileDto.getFileName()).build();
    }

    @Override
    public ArrayList<UserFileDto> toDTOs(Collection<UserFile> e) {
        return (ArrayList<UserFileDto>) e.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public ArrayList<UserFile> toEntities(Collection<UserFileDto> userFileDtos) {
        return (ArrayList<UserFile>) userFileDtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public PageResult<UserFileDto> toDataPage(PageResult<UserFile> entity) {
        return new PageResult<>(entity.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<UserFileDto>::new)), entity.getTotalCount(), entity.getPageSize(), entity.getCurrPage());
    }
}
