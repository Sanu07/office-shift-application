package com.test.shiftapplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.test.shiftapplication.model.LeaveModel;

public interface DefaultLeaveDao extends CrudRepository<LeaveModel, Long> {

	List<LeaveModel> findByCognizantId(@Param("cognizantId") String cognizantId);

	@Query("from LeaveModel lm where Month(lm.leaveStartDate) = :month")
	List<LeaveModel> findByLeaveStartDate(@Param("month") Integer month);
}
