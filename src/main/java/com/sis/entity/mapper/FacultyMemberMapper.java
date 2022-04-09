package com.sis.entity.mapper;

import com.sis.dto.college.CollegeDTO;
import com.sis.dto.facultyMember.FacultyMemberDTO;
import com.sis.entity.FacultyMember;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class FacultyMemberMapper implements Mapper<FacultyMember, FacultyMemberDTO> {

    private CollegeMapper collegeMapper;

    private DepartmentMapper departmentMapper;

    private DegreeMapper degreeMapper;

    private UserMapper userMapper;

    @Override
    public ArrayList<FacultyMemberDTO> toDTOs(Collection<FacultyMember> entities) {
        return entities.stream().map(this::toDTO).collect(toCollection(ArrayList<FacultyMemberDTO>::new));
    }

    @Override
    public PageResult<FacultyMemberDTO> toDataPage(PageResult<FacultyMember> entities) {
        return new PageResult<>(entities.getData().stream().map(this::toDTO).collect(toCollection(ArrayList<FacultyMemberDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
    }

    @Override
    public ArrayList<FacultyMember> toEntities(Collection<FacultyMemberDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(toCollection(ArrayList<FacultyMember>::new));
    }

    @Override
    public FacultyMemberDTO toDTO(FacultyMember entity) {
        FacultyMemberDTO dto = new FacultyMemberDTO();
        CollegeDTO collegeDTO = collegeMapper.toDTO(entity.getCollege());
        dto.setId(entity.getId());
        dto.setUniversityMail(entity.getUniversityMail());
        dto.setAlternativeMail(entity.getAlternativeMail());
        dto.setBirthDate(entity.getBirthDate());
        dto.setNameAr(entity.getNameAr());
        dto.setNameEn(entity.getNameEn());
        dto.setNationalID(entity.getNationalID());
        dto.setNationality(entity.getNationality());
        dto.setPhone(entity.getPhone());
        dto.setPhoto(entity.getPhoto());
        if (entity.getDegree() != null) {
            dto.setDegreeDTO(this.degreeMapper.toDTO(entity.getDegree()));
        }
        if (entity.getDepartment() != null) {
            dto.setDepartmentDTO(this.departmentMapper.toDTO(entity.getDepartment()));
        }
        if (entity.getCollege() != null) {
            dto.setCollegeDTO(collegeDTO);
        }
        dto.setUser(userMapper.toDTO(entity.getUser()));
        return dto;
    }

    @Override
    public FacultyMember toEntity(FacultyMemberDTO dto) {
        FacultyMember entity = new FacultyMember();
        entity.setId(dto.getId());
        entity.setUniversityMail(dto.getUniversityMail());
        entity.setAlternativeMail(dto.getAlternativeMail());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPhone(dto.getPhone());
        entity.setPhoto(dto.getPhoto());
        entity.setNameAr(dto.getNameAr());
        entity.setNameEn(dto.getNameEn());
        entity.setNationalID(dto.getNationalID());
        entity.setNationality(dto.getNationality());
        if (dto.getDepartmentDTO() != null) {
            entity.setDegree(this.degreeMapper.toEntity(dto.getDegreeDTO()));
        }
        if (dto.getDepartmentDTO() != null) {

            entity.setDepartment(this.departmentMapper.toEntity(dto.getDepartmentDTO()));
        }
        if (dto.getCollegeDTO() != null) {
            entity.setCollege(this.collegeMapper.toEntity(dto.getCollegeDTO()));
        }
        entity.setUser(userMapper.toEntity(dto.getUser()));
        return entity;
    }

}
