package com.sis.entity.mapper;

import com.sis.dto.AcademicProgramDTO;
import com.sis.entity.AcademicProgram;
import com.sis.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
public class AcademicProgramMapper implements Mapper<AcademicProgram,AcademicProgramDTO>{

    @Autowired
    private DepartmentMapper departmentMapper ;

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public AcademicProgramDTO toDTO(AcademicProgram entity) {
        AcademicProgramDTO academicProgramDTO = new AcademicProgramDTO();
        academicProgramDTO.setCode(entity.getCode());
        academicProgramDTO.setName_ar(entity.getNameAr());
        academicProgramDTO.setName_en(entity.getNameEn());
        academicProgramDTO.setId(entity.getId());
        if(entity.getCollegeId()!=null) {
            academicProgramDTO.setCollegeDTO(collegeMapper.toDTO(entity.getCollegeId()));
        }
        if(entity.getDepartmentId()!=null) {
            academicProgramDTO.setDepartmentDTO(departmentMapper.toDTO(entity.getDepartmentId()));
        }
        return academicProgramDTO;
    }

    @Override
    public AcademicProgram toEntity(AcademicProgramDTO dto) {
        AcademicProgram academicProgram= new AcademicProgram() ;
        academicProgram.setCode(dto.getCode());
        academicProgram.setNameAr(dto.getName_ar());
        academicProgram.setId(dto.getId());
        academicProgram.setNameEn(dto.getName_en());
        if(departmentMapper.toEntity(dto.getDepartmentDTO())!=null){
            academicProgram.setDepartmentId(this.departmentMapper.toEntity(dto.getDepartmentDTO()));
        }
        if(collegeMapper.toEntity(dto.getCollegeDTO())!=null){
            academicProgram.setCollegeId(this.collegeMapper.toEntity(dto.getCollegeDTO()));
        }
        return academicProgram ;
    }

    @Override
    public ArrayList<AcademicProgramDTO> toDTOs(Collection<AcademicProgram> academicPrograms) {
        return academicPrograms.stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<AcademicProgramDTO>::new));
    }

    @Override
    public ArrayList<AcademicProgram> toentity(Collection<AcademicProgramDTO> academicProgramDTOS) {
        return academicProgramDTOS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<AcademicProgram>::new));
    }

    @Override
    public PageResult<AcademicProgramDTO> toDataPage(PageResult<AcademicProgram> pageResult) {
        return new PageResult<>(pageResult.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<AcademicProgramDTO>::new)), pageResult.getTotalCount(), pageResult.getPageSize(), pageResult.getCurrPage());

    }

}
