package com.sis.service;

import com.sis.dao.AcademicYearDao;
import com.sis.entities.AcademicYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AcademicYearService extends BaseServiceImp<AcademicYear>{
    @Autowired
    private AcademicYearDao academicYearDao ;
    @Override
    public JpaRepository<AcademicYear, Long> Repository() {
        return academicYearDao;
    }
}
