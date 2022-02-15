package com.sis.dao;

import com.sis.entities.FacultyMember;

import java.util.Optional;

public interface FacultyMemberRepository extends BaseDao<FacultyMember>{

    Optional<FacultyMember> findByUser_Id(Long userID);

}
