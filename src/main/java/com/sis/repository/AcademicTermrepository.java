package com.sis.repository;

import com.sis.entity.AcademicTerm;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AcademicTermrepository extends BaseRepository<AcademicTerm> {


    //Abdo.Amr
//    @Query(value = "SELECT * FROM academic_term WHERE end_date=( SELECT MAX(end_date) FROM academic_term )",
//            nativeQuery = true)
    @Query(value = "SELECT * FROM academic_term WHERE status = 1 order by end_date DESC", nativeQuery = true)
    List<AcademicTerm> getCurrentAcademicTerm();


}
