package com.sis.service;

import com.sis.dto.attendanceDetails.StudentLecture;
import com.sis.entity.mapper.LectureMapper;
import com.sis.repository.AttendanceDetailsRepository;
import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.dto.attendanceReport.AttendanceReportDTO;
import com.sis.dto.student.StudentRecordDTO;
import com.sis.entity.AttendanceDetails;
import com.sis.entity.Lecture;
import com.sis.entity.mapper.AttendanceDetailsMapper;
import com.sis.entity.mapper.StudentRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class AttendanceDetailsService extends BaseServiceImp<AttendanceDetails>{


    @Autowired
    private AttendanceDetailsRepository attendanceDetailsRepository;

    @Autowired
    private AttendanceDetailsMapper attendanceDetailsMapper;

    @Autowired
    private  StudentService studentService;

    @Autowired
    private StudentRecordMapper studentRecordMapper;

    @Autowired
    private LectureMapper lectureMapper;

    @Override
    public JpaRepository<AttendanceDetails, Long> Repository() {
        return this.attendanceDetailsRepository;
    }

    public ArrayList<AttendanceDetailsDTO> findStudentAttendances(long studentId, long courseId){
        ArrayList<AttendanceDetails> attendanceDetails= this.attendanceDetailsRepository.findStudentAttendances(studentId, courseId);
        if(attendanceDetails!=null){
            return this.attendanceDetailsMapper.toDTOs(attendanceDetails);
        }
        return null;
    }
    // this function is written by Abdo Ramadan
    public AttendanceReportDTO findAttendanceReportDTOByLecture(Long lectureId){
        ArrayList<AttendanceDetails> attendanceDetails = this.attendanceDetailsRepository.
                findAttendanceDetailsByLectureId(lectureId);
        AttendanceReportDTO attendanceReportDTO = new AttendanceReportDTO();
        if(attendanceDetails!= null){
            attendanceReportDTO.setAttendanceDetailsDTOs(this.attendanceDetailsMapper.toDTOs(attendanceDetails));
        }
        return  attendanceReportDTO;
    }

    public ArrayList<AttendanceDetailsDTO> getAttendanceDetailsByLecture(long lectureId){
        ArrayList<AttendanceDetails> attendanceDetails = this.attendanceDetailsRepository.findAttendanceDetailsByLectureId(lectureId);
        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOS = new ArrayList<>();
        if(attendanceDetails!= null){
            attendanceDetailsDTOS = this.attendanceDetailsMapper.toDTOs(attendanceDetails);
        }
        return  attendanceDetailsDTOS;
    }
    public void saveAttendances(LectureDTO lectureDTO){
        ArrayList<StudentRecordDTO> studentRecordDTOS ;
        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOs=new ArrayList<>();

        studentRecordDTOS=this.studentRecordMapper.dtosToDTOs(studentService.findStudentsBySection(lectureDTO.getAcademicYearDTO().getId(), lectureDTO.getAcademicTermDTO().getId(), lectureDTO.getSectionDTO().getId()));

        for(StudentRecordDTO studentDTO:studentRecordDTOS){
            AttendanceDetailsDTO attendanceDetailsDTO = AttendanceDetailsDTO.builder()
                    .studentId(studentDTO.getId())
                    .universityId(studentDTO.getUniversityId())
//                    .collegeName(studentDTO.getCollegeName())
//                    .departmentName(studentDTO.getDepartmentName())
                    .lectureId(lectureDTO.getId())
                    .attendanceStatus("Absent")
//                    .attendanceDate(lectureDTO.getLectureDate())
//                    .lectureStartTime(lectureDTO.getLectureStartTime())
//                    .lectureEndTime(lectureDTO.getLectureEndTime())
//                    .sectionNumber(lectureDTO.getSectionDTO().getSectionNumber())
                    .sectionId(lectureDTO.getSectionDTO().getId())
                    .build();
            attendanceDetailsDTOs.add(attendanceDetailsDTO);
        }
        this.attendanceDetailsRepository.saveAll(this.attendanceDetailsMapper.toEntities(attendanceDetailsDTOs));
    }


    public AttendanceDetailsDTO addAutoAttendance( long attendanceCode,  StudentLecture studentLecture) {

        long studentId = studentLecture.getStudentId();
        LectureDTO lectureDTO = studentLecture.getLectureDTO();
        Lecture lecture = this.lectureMapper.toEntity(lectureDTO);
        AttendanceDetailsDTO attendanceDetailsDTO2 = null;
        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOS = this.getAttendanceDetailsByLecture(lecture.getId());
        for (AttendanceDetailsDTO attendanceDetailsDTO : attendanceDetailsDTOS) {
            if (attendanceDetailsDTO.getStudentId() == studentId) {
                if ((lectureDTO.getAttendanceCode() == attendanceCode) && (lectureDTO.getAttendanceStatus())) {
                    attendanceDetailsDTO.setAttendanceStatus("Present");
                    this.save(this.attendanceDetailsMapper.toEntity(attendanceDetailsDTO));
                    attendanceDetailsDTO2 = attendanceDetailsDTO;
                    break;
                }
            }
        }
        return attendanceDetailsDTO2;
    }

}
