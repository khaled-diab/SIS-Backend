package com.sis.dao;

import com.sis.entities.FacultyMember;

public interface FacultyMemberRepository extends BaseDao<FacultyMember> {
    FacultyMember findByNationalID(String id);

    FacultyMember findByUniversityMail(String mail);

    FacultyMember findByPhone(String phone);
}
