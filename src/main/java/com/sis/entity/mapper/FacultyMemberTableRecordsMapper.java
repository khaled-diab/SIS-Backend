package com.sis.entity.mapper;

import com.sis.dto.facultyMember.FacultyMemberTableRecordsDTO;
import com.sis.entity.FacultyMember;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class FacultyMemberTableRecordsMapper implements Mapper<FacultyMember, FacultyMemberTableRecordsDTO> {

    @Override
    public ArrayList<FacultyMemberTableRecordsDTO> toDTOs(Collection<FacultyMember> entity) {
        return entity.stream().map(this::toDTO).collect(toCollection(ArrayList<FacultyMemberTableRecordsDTO>::new));
    }

    @Override
    public PageResult<FacultyMemberTableRecordsDTO> toDataPage(PageResult<FacultyMember> entity) {
        return new PageResult<>(entity.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<FacultyMemberTableRecordsDTO>::new)), entity.getTotalCount(), entity.getPageSize(), entity.getCurrPage());
    }

    @Override
    public ArrayList<FacultyMember> toEntities(Collection<FacultyMemberTableRecordsDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<FacultyMember>::new));
    }

    @Override
    public FacultyMemberTableRecordsDTO toDTO(FacultyMember entity) {
        FacultyMemberTableRecordsDTO dto = new FacultyMemberTableRecordsDTO();
        dto.setId(entity.getId());
        dto.setNameAr(entity.getNameAr());
        dto.setUniversityMail(entity.getUniversityMail());
        if (entity.getDegree() != null) {
            dto.setDegreeName(entity.getDegree().getNameEn());
        }
        if (entity.getDepartment() != null) {
            dto.setDepartmentName(entity.getDepartment().getNameEn());
        }
        if (entity.getCollege() != null) {
            dto.setCollegeName(entity.getCollege().getNameEn());
        }
        return dto;
    }

    @Override
    public FacultyMember toEntity(FacultyMemberTableRecordsDTO facultyMemberTableRecordsDTO) {
        return null;
    }

}
