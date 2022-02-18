package com.sis.service;

import com.sis.dao.AttendanceDetailsRepository;
import com.sis.entities.AttendanceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AttendanceDetailsService extends BaseServiceImp<AttendanceDetails>{


    @Autowired
    private AttendanceDetailsRepository attendanceDetailsRepository;


    @Override
    public JpaRepository<AttendanceDetails, Long> Repository() {
        return this.attendanceDetailsRepository;
    }
}
