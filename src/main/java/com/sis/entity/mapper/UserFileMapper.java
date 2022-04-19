package com.sis.entity.mapper;

import com.sis.dto.UserFileDto;
import com.sis.entity.UserFile;
import com.sis.util.PageResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserFileMapper implements Mapper<UserFile, UserFileDto> {

    @Override
    public UserFileDto toDTO(UserFile userFile) {
        return new UserFileDto(userFile.getFileLink());
    }

    @Override
    public UserFile toEntity(UserFileDto userFileDto) {
        return new UserFile(userFileDto.getFileLink());
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
        return null;
    }
}
