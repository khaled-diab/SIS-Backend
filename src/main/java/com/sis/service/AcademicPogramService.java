package com.sis.service;

import com.sis.entity.AcademicProgram;
import com.sis.repository.AcademicPogramrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AcademicPogramService extends BaseServiceImp<AcademicProgram>{
    @Autowired
    private AcademicPogramrepository academicPogramrepository;
    @Override
    public JpaRepository<AcademicProgram, Long> Repository() {
        return academicPogramrepository;
    }
}
