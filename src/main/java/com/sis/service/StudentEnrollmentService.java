package com.sis.service;

import com.sis.dao.StudentEnrollmentRepository;
import com.sis.entities.StudentEnrollment;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentEnrollmentService extends BaseServiceImp<StudentEnrollment> {

    @Autowired
    private StudentEnrollmentRepository studentEnrollmentRepository;
    @Override
    public JpaRepository<StudentEnrollment, Long> Repository() {
        return studentEnrollmentRepository;
    }

}
