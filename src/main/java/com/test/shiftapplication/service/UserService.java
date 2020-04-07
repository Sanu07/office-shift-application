package com.test.shiftapplication.service;

import com.test.shiftapplication.model.UserModel;

public interface UserService {
	public UserModel saveUser(UserModel userModel);

	public UserModel findByEmail(String email);

	public UserModel updateUser(UserModel userModel);

	public void deleteUser(long userId);

	public UserModel findByName(String name);

	public UserModel findByUserId(long userId);

	public Iterable<UserModel> findAll();

	public UserModel findByCognizantId(String cognizantId);

	public void updateLastloginTime(String lastLoginTime, long userId);
}
