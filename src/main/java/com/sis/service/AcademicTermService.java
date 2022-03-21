package com.sis.service;


import com.sis.dao.AcademicTermDao;
import com.sis.entities.AcademicTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AcademicTermService extends BaseServiceImp<AcademicTerm> {
    private AcademicTermDao academicTermRepository ;
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
