package com.test.shiftapplication.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.test.shiftapplication.model.UserModel;

public interface DefaultUserDao extends CrudRepository<UserModel, Long> {
	public UserModel findByEmail(@Param("email") String email);

	public UserModel findByName(@Param("name") String name);

	public UserModel findByCognizantId(@Param("cognizantId") String cognizantId);

	@Modifying
	@Query("update UserModel u set u.lastloginTime = :lastLoginTime where u.userId = :userId")
	public void updateLastLoginTime(@Param("lastLoginTime") String lastLoginTime, @Param("userId") long userId);

}
