package com.test.shiftapplication.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.shiftapplication.dao.DefaultLeaveDao;
import com.test.shiftapplication.model.LeaveModel;
import com.test.shiftapplication.service.LeaveService;

@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {
	@Autowired
	DefaultLeaveDao defaultLeaveDao;

	@Override
	@Transactional
	public LeaveModel saveLeaves(LeaveModel leaveModel) {
		return defaultLeaveDao.save(leaveModel);
	}

	@Override
	@Transactional
	public Iterable<LeaveModel> findAll() {
		return defaultLeaveDao.findAll();
	}

	@Override
	@Transactional
	public LeaveModel updateLeaves(LeaveModel leaveModel) {
		return defaultLeaveDao.save(leaveModel);
	}

	@Override
	@Transactional
	public void deleteLeave(long leaveId) {
		defaultLeaveDao.deleteById(leaveId);
	}

	@Override
	@Transactional
	public List<LeaveModel> findByCognizantId(String cognizantId) {
		return defaultLeaveDao.findByCognizantId(cognizantId);
	}

	@Override
	public List<LeaveModel> findLeaveByMonth(Integer month) {
		return defaultLeaveDao.findByLeaveStartDate(month);
	}
}
