package com.sis.service;


import com.sis.entity.AcademicTerm;
import com.sis.repository.AcademicTermrepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AcademicTermService extends BaseServiceImp<AcademicTerm> {
    private final AcademicTermrepository academicTermRepository;

    public AcademicTermService(AcademicTermrepository academicTermRepository) {
        this.academicTermRepository = academicTermRepository;
    }

    @Override
    public JpaRepository<AcademicTerm, Long> Repository() {
        return academicTermRepository;
    }

    //Abdo.Amr
    public AcademicTerm getCurrentAcademicTerm() {
        return this.academicTermRepository.getCurrentAcademicTerm().get(0);
    }
}
