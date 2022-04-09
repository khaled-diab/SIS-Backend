package com.sis.repository;

import com.sis.entity.AcademicTerm;
import org.springframework.data.jpa.repository.Query;

public interface AcademicTermDao extends BaseDao<AcademicTerm>{


    //Abdo.Amr
    @Query(value = "SELECT * FROM academic_term WHERE end_date=( SELECT MAX(end_date) FROM academic_term )", nativeQuery = true)
    AcademicTerm getCurrentAcademicTerm();


}
