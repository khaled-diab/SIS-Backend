package com.sis.dao;

import com.sis.entities.Section;
import com.sis.entities.StudentEnrollment;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Collection;

public interface SectionRepository extends BaseDao<Section>{

    @Query(value="SELECT lecture_id FROM section_lecture WHERE section_id =:sectionId", nativeQuery = true)
    public ArrayList<Long> findFacultyMemberLectures(long sectionId);



}
