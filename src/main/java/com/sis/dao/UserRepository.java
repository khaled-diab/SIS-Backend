package com.sis.dao;

import com.sis.entities.security.User;

import java.util.Optional;

public interface UserRepository extends BaseDao<User> {
    /**
     * findByUsername
     *
     * @param username
     * @return User
     */
    Optional<User> findByUsername(String username);

    /**
     * findByEmailOrUsername
     *
     * @param email
     * @param username
     * @return User
     */
    User findByEmailOrUsername(String email, String username);

}
