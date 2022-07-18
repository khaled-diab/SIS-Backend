package com.sis.service;

import com.sis.entity.*;
import com.sis.repository.GradeBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GradeBookService extends BaseServiceImp<GradeBook> {

    private final GradeBookRepository gradeBookRepository;

    @Override
    public JpaRepository<GradeBook, Long> Repository() {
        return gradeBookRepository;
    }


}
