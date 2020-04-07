package com.test.shiftapplication.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.shiftapplication.dao.DefaultUserDao;
import com.test.shiftapplication.model.UserModel;
import com.test.shiftapplication.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	DefaultUserDao userRepository;

	@Override
	@Transactional
	public UserModel saveUser(UserModel userModel) {
		return userRepository.save(userModel);
	}

	@Override
	@Transactional
	public UserModel findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public UserModel updateUser(UserModel userModel) {
		return userRepository.save(userModel);
	}

	@Override
	@Transactional
	public void deleteUser(long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	@Transactional
	public UserModel findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	@Transactional
	public UserModel findByUserId(long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public Iterable<UserModel> findAll() {
		return userRepository.findAll();
	}

	@Override
	public UserModel findByCognizantId(String cognizantId) {
		return userRepository.findByCognizantId(cognizantId);
	}

	@Override
	@Transactional
	public void updateLastloginTime(String lastLoginTime, long userId) {
		userRepository.updateLastLoginTime(lastLoginTime, userId);
	}
}
