package com.sis.dao;

import com.sis.entities.Lecture;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Collection;

public interface LectureRepository extends BaseDao<Lecture> {
//    @Query(value="SELECT lecture_id FROM section_lecture WHERE  section_id= :sectionId  ", nativeQuery = true)
//    public Collection<Long> findLecturesBySections(long sectionId);
//
    //UC011
    @Query(value="SELECT lecture_id FROM section_lecture WHERE section_id =:sectionId", nativeQuery = true)
    public ArrayList<Long> findFacultyMemberLectures(long sectionId);


}
