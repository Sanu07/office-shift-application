package com.test.shiftapplication.service;

import com.test.shiftapplication.model.WSRModel;

public interface WSRService {
	Iterable<WSRModel> getAllWSRDetails();

	Iterable<WSRModel> getWSRByDate(String wsrDate);

	WSRModel saveWSRDEtails(WSRModel wsrModel);

	Iterable<WSRModel> findAll();
}
