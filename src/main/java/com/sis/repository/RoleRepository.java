package com.sis.repository;

import com.sis.entity.security.Role;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends BaseRepository<Role> {

    @Query(value = "select * from sis.role where role_name='ADMIN'", nativeQuery = true)
    Role getRoleAdmin();

    @Query(value = "select * from sis.role where role_name='STUDENT'", nativeQuery = true)
    Role getRoleStudent();

    @Query(value = "select * from sis.role where role_name='FACULTY_MEMBER'", nativeQuery = true)
    Role getRoleFacultyMember();
}
