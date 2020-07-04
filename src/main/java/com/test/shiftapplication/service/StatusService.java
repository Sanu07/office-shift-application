package com.test.shiftapplication.service;

import com.test.shiftapplication.model.StatusModel;

public interface StatusService {
	StatusModel saveStatus(StatusModel statusModel);

	public Iterable<StatusModel> getAllStatus();

}
