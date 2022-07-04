package com.sis.entity.mapper;



import com.sis.dto.student.StudentDTO;
import com.sis.dto.student.StudentRecordDTO;
import com.sis.entity.Student;
import com.sis.util.PageResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
public class StudentRecordMapper implements Mapper<Student, StudentRecordDTO> {

    @Override
    public StudentRecordDTO toDTO(Student entity) {
        StudentRecordDTO dto = new StudentRecordDTO();
        dto.setId(entity.getId());
        dto.setNameEn(entity.getNameEn());
        dto.setYear(entity.getYear());
        dto.setNameAr(entity.getNameAr());
        dto.setUniversityId(entity.getUniversityId());
        if (entity.getDepartmentId() != null) {
            dto.setDepartmentName(entity.getDepartmentId().getNameEn());
        }
        if (entity.getCollegeId() != null) {
            dto.setCollegeName(entity.getCollegeId().getNameAr());
        }
        return dto;
    }



    public StudentRecordDTO dtoToDto(StudentDTO studentDTO) {
        StudentRecordDTO dto = new StudentRecordDTO();
        dto.setId(studentDTO.getId());
        dto.setNameEn(studentDTO.getNameEn());
        dto.setYear(studentDTO.getYear());
        dto.setNameAr(studentDTO.getNameAr());
        dto.setUniversityId(studentDTO.getUniversityId());
        if(studentDTO.getDepartmentDTO()!=null) {
            dto.setDepartmentName(studentDTO.getDepartmentDTO().getNameEn());
        }
        if(studentDTO.getCollegeDTO()!=null) {
            dto.setCollegeName(studentDTO.getCollegeDTO().getNameAr());
        }
        return dto;
    }

    @Override
    public Student toEntity(StudentRecordDTO dto) {
        Student entity = new Student();
        entity.setId(dto.getId());
        return entity;
    }


    @Override
    public ArrayList<StudentRecordDTO> toDTOs(Collection<Student> students) {
        return students.stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<StudentRecordDTO>::new));
    }

    public ArrayList<StudentRecordDTO> dtosToDTOs(Collection<StudentDTO> students) {
        return students.stream().map(entity -> dtoToDto(entity)).collect(toCollection(ArrayList<StudentRecordDTO>::new));
    }
    @Override
    public ArrayList<Student> toEntities(Collection<StudentRecordDTO> studentRecordDTOS) {
        return studentRecordDTOS.stream().map(entity -> toEntity(entity)).collect(toCollection(ArrayList<Student>::new));

    }


    @Override
    public PageResult<StudentRecordDTO> toDataPage(PageResult<Student> entities) {
        return new PageResult<>(entities.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<StudentRecordDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());

    }
}
