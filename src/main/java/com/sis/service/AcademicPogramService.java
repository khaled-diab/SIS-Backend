package com.sis.service;

import com.sis.dao.AcademicPogramDao;
import com.sis.entities.AcademicProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AcademicPogramService extends BaseServiceImp<AcademicProgram>{
    @Autowired
    private AcademicPogramDao academicPogramDao ;
    @Override
    public JpaRepository<AcademicProgram, Long> Repository() {
        return academicPogramDao;
    }
}
