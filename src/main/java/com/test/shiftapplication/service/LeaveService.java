package com.test.shiftapplication.service;

import java.util.List;

import com.test.shiftapplication.model.LeaveModel;

public interface LeaveService {
	LeaveModel saveLeaves(LeaveModel leaveModel);

	Iterable<LeaveModel> findAll();

	LeaveModel updateLeaves(LeaveModel leaveModel);

	void deleteLeave(long leaveId);

	List<LeaveModel> findByCognizantId(String cognizantId);

	List<LeaveModel> findLeaveByMonth(Integer month);
}
