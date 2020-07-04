package com.test.shiftapplication.service;

import org.springframework.stereotype.Service;

import com.test.shiftapplication.model.LastLoginModel;

@Service
public interface LastLoginService {
	public Object getLastLoginTime(String cognizantId);

	public Object saveLastLogiData(LastLoginModel lastLoginModel);
}
