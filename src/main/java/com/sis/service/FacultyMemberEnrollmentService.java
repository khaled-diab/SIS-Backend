package com.sis.service;

import com.sis.entities.FacultyMemberEnrollment;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FacultyMemberEnrollmentService extends BaseServiceImp<FacultyMemberEnrollment> {

    @Override
    public JpaRepository<FacultyMemberEnrollment, Long> Repository() {
        return null;
    }
}
