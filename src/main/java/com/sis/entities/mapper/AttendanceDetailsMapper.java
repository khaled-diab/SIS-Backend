package com.sis.entities.mapper;

import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;
import com.sis.entities.AttendanceDetails;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class AttendanceDetailsMapper implements Mapper<AttendanceDetails, AttendanceDetailsDTO> {

    private StudentMapper studentMapper;
    private LectureMapper lectureMapper;
    private SectionMapper sectionMapper;

    @Override
    public AttendanceDetailsDTO toDTO(AttendanceDetails entity) {
        AttendanceDetailsDTO attendanceDetailsDTO = AttendanceDetailsDTO.builder()
                .attendanceStatus(entity.getAttendanceStatus())
                .attendanceDate(entity.getAttendanceDate())
                .lectureStartTime(entity.getLectureStartTime())
                .lectureEndTime(entity.getLectureEndTime())
                .build();
        attendanceDetailsDTO.setId(entity.getId());

        if (entity.getStudent() != null) {
            attendanceDetailsDTO.setStudentDTO(this.studentMapper.toDTO(entity.getStudent()));
        }
        if (entity.getLecture() != null) {
            attendanceDetailsDTO.setLectureDTO(this.lectureMapper.toDTO(entity.getLecture()));
        }
        if (entity.getSection() != null) {
            attendanceDetailsDTO.setSectionDTO(this.sectionMapper.toDTO(entity.getSection()));
        }

        return attendanceDetailsDTO;
    }

    @Override
    public AttendanceDetails toEntity(AttendanceDetailsDTO dto) {
        AttendanceDetails attendanceDetails = new AttendanceDetails();
        if (dto != null) {
            attendanceDetails.setAttendanceStatus(dto.getAttendanceStatus());
            attendanceDetails.setAttendanceDate(dto.getAttendanceDate());
            attendanceDetails.setLectureStartTime(dto.getLectureStartTime());
            attendanceDetails.setLectureEndTime(dto.getLectureEndTime());
            attendanceDetails.setId(dto.getId());
            if (dto.getStudentDTO() != null) {
                attendanceDetails.setStudent(this.studentMapper.toEntity(dto.getStudentDTO()));
            }
            if (dto.getLectureDTO() != null) {
                attendanceDetails.setLecture(this.lectureMapper.toEntity(dto.getLectureDTO()));
            }
            if (dto.getStudentDTO() != null) {
                attendanceDetails.setSection(this.sectionMapper.toEntity(dto.getSectionDTO()));
            }
        }
        return attendanceDetails;
    }

    @Override
    public ArrayList<AttendanceDetailsDTO> toDTOs(Collection<AttendanceDetails> attendanceDetails) {
        return attendanceDetails.stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<AttendanceDetailsDTO>::new));
    }

    @Override
    public ArrayList<AttendanceDetails> toEntities(Collection<AttendanceDetailsDTO> attendanceDetailsDTOS) {
        return attendanceDetailsDTOS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<AttendanceDetails>::new));
    }

    @Override
    public PageResult<AttendanceDetailsDTO> toDataPage(PageResult<AttendanceDetails> entities) {
        return new PageResult<>(entities.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<AttendanceDetailsDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
    }
}
