package com.sis.service;

import com.sis.dao.AttendanceDetailsRepository;
import com.sis.dto.attendanceDetails.AttendanceDetailsDTO;
import com.sis.dto.attendanceReport.AttendanceReportDTO;
import com.sis.entities.AttendanceDetails;
import com.sis.entities.Lecture;
import com.sis.entities.mapper.AttendanceDetailsMapper;
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


    @Override
    public JpaRepository<AttendanceDetails, Long> Repository() {
        return this.attendanceDetailsRepository;
    }

    public ArrayList<AttendanceDetailsDTO> findStudentAttendances(long studentId, long sectionId){
        ArrayList<AttendanceDetails> attendanceDetails= this.attendanceDetailsRepository.findStudentAttendances(studentId, sectionId);
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

}
