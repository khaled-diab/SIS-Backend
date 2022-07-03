package com.sis.service;

import com.sis.dto.attendanceDetails.AttendanceBySection;
import com.sis.dto.attendanceDetails.AttendanceBySectionAndStudentDTO;
import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;
import com.sis.dto.attendanceReport.AttendanceReportDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.entity.AttendanceDetails;
import com.sis.entity.Lecture;
import com.sis.entity.mapper.AttendanceDetailsMapper;
import com.sis.repository.AttendanceDetailsRepository;
import com.sis.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;

@Service
public class AttendanceDetailsService extends BaseServiceImp<AttendanceDetails>{


    @Autowired
    private AttendanceDetailsRepository attendanceDetailsRepository;

    @Autowired
    private AttendanceDetailsMapper attendanceDetailsMapper;

    @Autowired
    private  StudentService studentService;

    @Autowired
    private LectureRepository lectureRepository;


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
        ArrayList<AttendanceDetails> attendanceDetails =
                this.attendanceDetailsRepository.findAttendanceDetailsByLectureId(lectureId);
        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOs = new ArrayList<>();
        if(attendanceDetails!= null){
            attendanceDetailsDTOs=  this.attendanceDetailsMapper.toDTOs(attendanceDetails);
        }

        return  attendanceDetailsDTOs;

    }
    public void saveAttendances(LectureDTO lectureDTO){
        ArrayList<StudentDTO> studentDTOs=new ArrayList<>();
        ArrayList<AttendanceDetailsDTO> attendanceDetailsDTOs=new ArrayList<>();

             studentDTOs=this.studentService.findStudentsBySection(lectureDTO.getAcademicYearDTO().getId(), lectureDTO.getAcademicTermDTO().getId(), lectureDTO.getSectionDTO().getId());

        for(StudentDTO studentDTO:studentDTOs){
            AttendanceDetailsDTO attendanceDetailsDTO = AttendanceDetailsDTO.builder()
                    .studentDTO(studentDTO)
                    .lectureDTO(lectureDTO)
                    .attendanceStatus("Absent")
                    .attendanceDate(lectureDTO.getLectureDate())
                    .lectureStartTime(lectureDTO.getLectureStartTime())
                    .lectureEndTime(lectureDTO.getLectureEndTime())
                    .sectionDTO(lectureDTO.getSectionDTO())
                    .build();
            attendanceDetailsDTOs.add(attendanceDetailsDTO);
        }
        this.attendanceDetailsRepository.saveAll(this.attendanceDetailsMapper.toEntities(attendanceDetailsDTOs));
    }
    //Abdo Ramadan
    public ArrayList<AttendanceBySectionAndStudentDTO> getAttendanceDetailsBySectoinAndStudentId(Long sectionId,
                                                                                                 Long studentId){
        ArrayList<AttendanceDetails> attendanceDetailsList =
                this.attendanceDetailsRepository.findAttendanceDetailsBySectionIdAndStudentId(sectionId ,studentId);
        ArrayList<AttendanceBySectionAndStudentDTO> attendanceBySectionAndStudentDTOS = new ArrayList<>();
        int n = attendanceDetailsList.size();
        for(int i = 0 ;i < n ;i++){
            AttendanceDetails attendanceDetails = attendanceDetailsList.get(i);
            AttendanceBySectionAndStudentDTO attendanceBySectionAndStudentDTO = new AttendanceBySectionAndStudentDTO();
            attendanceBySectionAndStudentDTO.setAttendanceDate(attendanceDetails.getAttendanceDate());
            attendanceBySectionAndStudentDTO.setAttendanceStatus(attendanceDetails.getAttendanceStatus());
            attendanceBySectionAndStudentDTO.setLectureStartTime(attendanceDetails.getLectureStartTime());
            attendanceBySectionAndStudentDTO.setLectureEndTime(attendanceDetails.getLectureEndTime());
            attendanceBySectionAndStudentDTOS.add(attendanceBySectionAndStudentDTO);
        }
        return  attendanceBySectionAndStudentDTOS;
    }

    public ArrayList<AttendanceBySection> getAttendanceDetailsBySectoin(Long sectionId  ){
        ArrayList<AttendanceDetails> attendanceDetailsList =
                this.attendanceDetailsRepository.findStudentBySectionId(sectionId );
        ArrayList<AttendanceBySection> attendanceBySectionAndStudentDTOS = new ArrayList<>();
        int n = attendanceDetailsList.size();
        ArrayList<Long> lectureIds = lectureRepository.findFacultyMemberLectures(sectionId);

        for(int i = 0 ;i < n ;i++){
            AttendanceDetails attendanceDetails = attendanceDetailsList.get(i);
            AttendanceBySection attendanceBySection = new AttendanceBySection();
            attendanceBySection.setNameOfStudent(attendanceDetails.getStudent().getNameAr());
            attendanceBySection.setNumberOfLecture(lectureIds.size());
            Long studentId = attendanceDetails.getStudent().getId();
            int absenceNumber = attendanceDetailsRepository.findStudentAbsenceLecture(studentId, sectionId).size();
            attendanceBySection.setIdOfStudent(studentId);
            attendanceBySection.setAbsentLecture(absenceNumber);
            attendanceBySectionAndStudentDTOS.add(attendanceBySection);
        }
        return  attendanceBySectionAndStudentDTOS;
    }

}
