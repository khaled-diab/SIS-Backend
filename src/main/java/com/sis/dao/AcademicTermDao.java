package com.sis.dao;

import com.sis.entities.AcademicTerm;
import org.springframework.data.jpa.repository.Query;

public interface AcademicTermDao extends BaseDao<AcademicTerm>{


    //UC011
    @Query(value="SELECT * FROM academic_term WHERE end_date=( SELECT MAX(end_date) FROM academic_term )", nativeQuery = true)
    public AcademicTerm getCurrentAcademicTerm();


}
