package com.sis.repository;

import com.sis.entity.security.User;

import java.util.Optional;

public interface UserRepository extends Baserepository<User> {
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
    Optional<User> findByEmailOrUsername(String email, String username);

}
