package com.sis.service;

import com.sis.entity.AcademicYear;
import com.sis.repository.AcademicYearrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AcademicYearService extends BaseServiceImp<AcademicYear>{
    @Autowired
    private AcademicYearrepository academicYearrepository;
    @Override
    public JpaRepository<AcademicYear, Long> Repository() {
        return academicYearrepository;
    }
}
