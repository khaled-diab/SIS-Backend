package com.sis.repository;

import com.sis.entity.FacultyMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FacultyMemberRepository extends BaseRepository<FacultyMember> {
    FacultyMember findByNationalID(String id);

    FacultyMember findByUniversityMail(String mail);

    FacultyMember findByPhone(String phone);

    Optional<FacultyMember> findByUser_Id(Long userID);

    @Query(value = "select * from faculty_member where user_id is not null", nativeQuery = true)
    Page<FacultyMember> findAllWithUser(Pageable pageable);

    FacultyMember findFacultyMemberByUserId(Long userId);

    List<FacultyMember> getFacultyMembersByCollegeId(Long collegeId);
}
