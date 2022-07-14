package com.sis.entity.mapper;

import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;
import com.sis.entity.AttendanceDetails;
import com.sis.entity.Lecture;
import com.sis.entity.Section;
import com.sis.entity.Student;
import com.sis.service.LectureService;
import com.sis.util.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Component
@AllArgsConstructor
public class AttendanceDetailsMapper implements Mapper<AttendanceDetails, AttendanceDetailsDTO> {


    private LectureService lectureService;
    @Override
    public AttendanceDetailsDTO toDTO(AttendanceDetails entity) {
        AttendanceDetailsDTO attendanceDetailsDTO = AttendanceDetailsDTO.builder()
                .attendanceStatus(entity.getAttendanceStatus())
//                .attendanceDate(entity.getAttendanceDate())
//                .lectureStartTime(entity.getLectureStartTime())
//                .lectureEndTime(entity.getLectureEndTime())
                .build();
        attendanceDetailsDTO.setId(entity.getId());

        if (entity.getStudent() != null) {
            attendanceDetailsDTO.setStudentId(entity.getStudent().getId());
            attendanceDetailsDTO.setNameAr(entity.getStudent().getNameAr());
            attendanceDetailsDTO.setNameEn(entity.getStudent().getNameEn());
            attendanceDetailsDTO.setUniversityId(entity.getStudent().getUniversityId());
//            attendanceDetailsDTO.setDepartmentName(entity.getStudent().getDepartmentId().getNameAr());
//            attendanceDetailsDTO.setCollegeName(entity.getStudent().getCollegeId().getNameAr());

        }
        if (entity.getLecture() != null) {
            attendanceDetailsDTO.setLectureId(entity.getLecture().getId());
        }
        if (entity.getSection() != null) {
//            attendanceDetailsDTO.setSectionNumber(entity.getSection().getSectionNumber());
            attendanceDetailsDTO.setSectionId(entity.getSection().getId());
        }

        return attendanceDetailsDTO;
    }

    @Override
    public AttendanceDetails toEntity(AttendanceDetailsDTO dto) {
        AttendanceDetails attendanceDetails = new AttendanceDetails();
        Lecture lecture = this.lectureService.findById(dto.getLectureId());

        Section section = new Section();
        section.setId(dto.getSectionId());
//        section.setSectionNumber(dto.getSectionNumber());

        Student student = new Student();
        student.setId(dto.getStudentId());

        attendanceDetails.setLecture(lecture);
        attendanceDetails.setSection(section);
        attendanceDetails.setStudent(student);
        if (dto != null) {
            attendanceDetails.setAttendanceStatus(dto.getAttendanceStatus());
            attendanceDetails.setAttendanceDate(lecture.getLectureDate());
            attendanceDetails.setLectureStartTime(lecture.getLectureStartTime());
            attendanceDetails.setLectureEndTime(lecture.getLectureEndTime());
            attendanceDetails.setId(dto.getId());
//            if (dto.getLectureDTO() != null) {
//                attendanceDetails.setLecture(this.lectureMapper.toEntity(dto.getLectureDTO()));
//            }
//            if (dto.getSectionDTO() != null) {
//                attendanceDetails.setSection(this.sectionMapper.toEntity(dto.getSectionDTO()));
//            }
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
    public PageResult<AttendanceDetailsDTO> toDataPage(PageResult<AttendanceDetails> pageResult) {
        return new PageResult<>(pageResult.getData().stream().map(entity -> toDTO(entity)).collect(toCollection(ArrayList<AttendanceDetailsDTO>::new)), pageResult.getTotalCount(), pageResult.getPageSize(), pageResult.getCurrPage());
    }






}
