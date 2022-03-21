package com.sis.service;

import com.sis.dao.AttendanceDetailsRepository;
import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;
import com.sis.dto.lecture.LectureDTO;
import com.sis.dto.section.SectionDTO;
import com.sis.dto.student.StudentDTO;
import com.sis.dto.attendanceReport.AttendanceReportDTO;
import com.sis.entities.AttendanceDetails;
import com.sis.entities.Lecture;
import com.sis.entities.mapper.AttendanceDetailsMapper;
import com.sis.entities.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AttendanceDetailsService extends BaseServiceImp<AttendanceDetails>{


    @Autowired
    private AttendanceDetailsRepository attendanceDetailsRepository;

    @Autowired
    private AttendanceDetailsMapper attendanceDetailsMapper;

    @Autowired
    private  StudentService studentService;


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

    public ArrayList<AttendanceDetailsDTO> getAttendanceDetailsByLecture(Lecture lecture){
        ArrayList<AttendanceDetails> attendanceDetails = this.attendanceDetailsRepository.findAttendanceDetailsByLecture(lecture);
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



}
