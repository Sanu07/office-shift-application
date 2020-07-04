package com.test.shiftapplication.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.test.shiftapplication.model.LastLoginModel;

public interface DefaultLastLoginTimeDao extends CrudRepository<LastLoginModel, Long> {
	public Object findByCognizantId(@Param("cognizantId") String cognizantId);
}