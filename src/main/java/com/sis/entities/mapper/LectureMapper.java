package com.sis.entities.mapper;

import com.sis.dto.lecture.LectureDTO;
import com.sis.entities.Lecture;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class LectureMapper implements Mapper<Lecture, LectureDTO> {

    private FacultyMemberMapper facultyMemberMapper;
    private CourseMapper courseMapper;
    private AcademicTermMapper academicTermMapper;
    private AcademicYearMapper academicYearMapper;
    private SectionMapper sectionMapper;

    @Override
    public LectureDTO toDTO(Lecture entity) {
        LectureDTO lectureDTO = LectureDTO.builder()
                .lectureDay(entity.getLectureDay())
                .lectureDate(entity.getLectureDate())
                .lectureStartTime(entity.getLectureStartTime())
                .lectureEndTime(entity.getLectureEndTime())
                .attendanceCode(entity.getAttendanceCode())
                .attendanceCodeExpiringTime(entity.getAttendanceCodeExpiringTime())
                .attendanceType(entity.getAttendanceType())
                .build();
        lectureDTO.setId(entity.getId());
        if (entity.getFacultyMemberId() != null) {
            lectureDTO.setFacultyMemberDTO(this.facultyMemberMapper.toDTO(entity.getFacultyMemberId()));
        }
        if (entity.getCourseId() != null) {
            lectureDTO.setCourseDTO(this.courseMapper.toDTO(entity.getCourseId()));
        }
        if (entity.getAcademicTermId() != null) {
            lectureDTO.setAcademicTermDTO(this.academicTermMapper.toDTO(entity.getAcademicTermId()));
        }
        if (entity.getAcademicYearId() != null) {
            lectureDTO.setAcademicYearDTO(this.academicYearMapper.toDTO(entity.getAcademicYearId()));
        }
        if (entity.getSections() != null) {
            lectureDTO.setSectionDTOs(this.sectionMapper.toDTOs(entity.getSections()));
        }
        return lectureDTO;
    }

    @Override
    public Lecture toEntity(LectureDTO dto) {
        Lecture lecture = new Lecture();
        if (dto != null) {
            lecture.setLectureDay(dto.getLectureDay());
            lecture.setLectureDate(dto.getLectureDate());
            lecture.setLectureStartTime(dto.getLectureStartTime());
            lecture.setLectureEndTime(dto.getLectureEndTime());
            lecture.setAttendanceCode(dto.getAttendanceCode());
            lecture.setAttendanceCodeExpiringTime(dto.getAttendanceCodeExpiringTime());
            lecture.setAttendanceType(dto.getAttendanceType());
            lecture.setId(dto.getId());
            if (dto.getFacultyMemberDTO() != null) {
                lecture.setFacultyMemberId(this.facultyMemberMapper.toEntity(dto.getFacultyMemberDTO()));
            }
            if (dto.getCourseDTO() != null) {
                lecture.setCourseId(this.courseMapper.toEntity(dto.getCourseDTO()));
            }
            if (dto.getAcademicTermDTO() != null) {
                lecture.setAcademicTermId(this.academicTermMapper.toEntity(dto.getAcademicTermDTO()));
            }
            if (dto.getAcademicYearDTO() != null) {
                lecture.setAcademicYearId(this.academicYearMapper.toEntity(dto.getAcademicYearDTO()));
            }
            if (dto.getSectionDTOs() != null) {
                lecture.setSections(this.sectionMapper.toEntities(dto.getSectionDTOs()));
            }
        }
        return lecture;
    }

    @Override
    public ArrayList<LectureDTO> toDTOs(Collection<Lecture> lectures) {
        return lectures.stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<LectureDTO>::new));
    }

    @Override
    public ArrayList<Lecture> toEntities(Collection<LectureDTO> lectureDTOS) {
        return lectureDTOS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<Lecture>::new));
    }

    @Override
    public PageResult<LectureDTO> toDataPage(PageResult<Lecture> entities) {
        return new PageResult<>(entities.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<LectureDTO>::new)), entities.getTotalCount(), entities.getPageSize(), entities.getCurrPage());
    }
}
