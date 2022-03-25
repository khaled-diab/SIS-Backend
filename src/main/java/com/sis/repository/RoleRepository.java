package com.sis.repository;

import com.sis.entities.security.Role;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends BaseDao<Role> {

    @Query(value = "select * from sis.role where role_name='ADMIN'",nativeQuery = true)
    Role getRoleAdmin();

    @Query(value = "select * from sis.role where role_name='STUDENT'",nativeQuery = true)
    Role getRoleStudent();

    @Query(value = "select * from sis.role where role_name='FACULTY_MEMBER'",nativeQuery = true)
    Role getRoleFacultyMember();
}
