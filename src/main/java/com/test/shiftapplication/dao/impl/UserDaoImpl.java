package com.test.shiftapplication.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.shiftapplication.dao.UserDao;
import com.test.shiftapplication.model.UserModel;

public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void saveUser(UserModel userModel) {
		sessionFactory.getCurrentSession().save(userModel);
	}

}
