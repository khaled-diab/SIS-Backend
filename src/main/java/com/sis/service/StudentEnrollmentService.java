package com.sis.service;

import com.sis.entities.StudentEnrollment;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentEnrollmentService extends BaseServiceImp<StudentEnrollment> {

    @Override
    public JpaRepository<StudentEnrollment, Long> Repository() {
        return null;
    }
}
