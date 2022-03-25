package com.sis.service;


import com.sis.entities.AcademicTerm;
import com.sis.repository.AcademicTermDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AcademicTermService extends BaseServiceImp<AcademicTerm> {
    private final AcademicTermDao academicTermRepository;
    public AcademicTermService(AcademicTermDao academicTermRepository){
        this.academicTermRepository = academicTermRepository ;
    }
    @Override
    public JpaRepository<AcademicTerm, Long> Repository() {
        return  academicTermRepository ;
    }

    //Abdo.Amr
    public AcademicTerm getCurrentAcademicTerm(){
        return this.academicTermRepository.getCurrentAcademicTerm();
    }
}
