package com.sis.repository;

import com.sis.entity.FacultyMember;

import java.util.Optional;

public interface FacultyMemberRepository extends BaseRepository<FacultyMember> {
    FacultyMember findByNationalID(String id);

    FacultyMember findByUniversityMail(String mail);

    FacultyMember findByPhone(String phone);

    Optional<FacultyMember> findByUser_Id(Long userID);
}
