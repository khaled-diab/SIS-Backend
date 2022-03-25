package com.sis.service;

import com.sis.entities.Lecture;
import com.sis.repository.AttendanceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;


public class AttendanceReportService extends BaseServiceImp<Lecture> {
    @Autowired
    AttendanceDetailsRepository attendanceDetailsRepository;

    @Override
    public JpaRepository<Lecture, Long> Repository() {
        return null;
    }

}
