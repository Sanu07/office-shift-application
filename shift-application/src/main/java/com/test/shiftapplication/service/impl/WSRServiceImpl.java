package com.test.shiftapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.shiftapplication.dao.DefaultWSRDao;
import com.test.shiftapplication.model.WSRModel;
import com.test.shiftapplication.service.WSRService;

@Service("wsrService")
public class WSRServiceImpl implements WSRService {
	@Autowired
	DefaultWSRDao defaultWSRDao;

	@Override
	public Iterable<WSRModel> getAllWSRDetails() {
		return defaultWSRDao.findAll();
	}

	@Override
	public Iterable<WSRModel> getWSRByDate(String wsrDate) {
		return defaultWSRDao.findByWSRDate(wsrDate);
	}

	@Override
	public WSRModel saveWSRDEtails(WSRModel wsrModel) {
		return defaultWSRDao.save(wsrModel);
	}

	@Override
	public Iterable<WSRModel> findAll() {
		return defaultWSRDao.findAll();
	}
}
