package com.test.shiftapplication.dao;

import org.springframework.data.repository.CrudRepository;

import com.test.shiftapplication.model.StatusModel;

public interface DefaultStatusDao extends CrudRepository<StatusModel, Long> {

}
