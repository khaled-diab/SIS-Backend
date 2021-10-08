package com.sis.dao;

import com.sis.entities.User;

public interface UserRepository extends BaseDao<User> {
	/**
	 * findByUsername
	 * 
	 * @param username
	 * @return User
	 */
	User findByUsername(String username);

	/**
	 * findByEmailOrUsername
	 * 
	 * @param email
	 * @param username
	 * @return User
	 */
	User findByEmailOrUsername(String email, String username);
	
}
