package com.test.shiftapplication.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.test.shiftapplication.model.WSRModel;

public interface DefaultWSRDao extends CrudRepository<WSRModel, Long> {

	Iterable<WSRModel> findByWSRDate(@Param("WSRDate") String wsrDate);

}
