package com.test.shiftapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.shiftapplication.dao.DefaultStatusDao;
import com.test.shiftapplication.model.StatusModel;
import com.test.shiftapplication.service.StatusService;

@Service("statusService")
public class StatusServiceImpl implements StatusService {
	@Autowired
	DefaultStatusDao defaultStatusDao;

	public StatusModel saveStatus(StatusModel statusModel) {
		return defaultStatusDao.save(statusModel);
	}

	public Iterable<StatusModel> getAllStatus() {
		return defaultStatusDao.findAll();
	}

}
