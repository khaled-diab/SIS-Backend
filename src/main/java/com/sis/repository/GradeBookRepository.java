package com.sis.repository;


import com.sis.entity.GradeBook;

import java.util.List;

public interface GradeBookRepository extends BaseRepository<GradeBook> {
    List<GradeBook> getGradeBooksByAcademicTerm_IdAndStudentId(Long term, Long studentId);

    List<GradeBook> getGradeBooksByStudentId(Long studentId);

    List<GradeBook> getGradeBooksBySectionId(Long sectionId);

}
