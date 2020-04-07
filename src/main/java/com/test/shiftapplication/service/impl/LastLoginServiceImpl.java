package com.test.shiftapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.shiftapplication.dao.DefaultLastLoginTimeDao;
import com.test.shiftapplication.model.LastLoginModel;
import com.test.shiftapplication.service.LastLoginService;

@Repository("lastLoginService")
public class LastLoginServiceImpl implements LastLoginService {
	@Autowired
	DefaultLastLoginTimeDao defaultLastLoginTimeDao;

	public Object getLastLoginTime(String cognizantId) {
		return defaultLastLoginTimeDao.findByCognizantId(cognizantId);
	}

	@Override
	public Object saveLastLogiData(LastLoginModel lastLoginModel) {
		return defaultLastLoginTimeDao.save(lastLoginModel);
	}
}
