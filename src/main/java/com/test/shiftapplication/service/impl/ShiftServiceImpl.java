package com.test.shiftapplication.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.shiftapplication.dao.DefaultShiftDao;
import com.test.shiftapplication.model.MonthlyShiftModel;
import com.test.shiftapplication.service.ShiftService;

@Service("shiftService")
public class ShiftServiceImpl implements ShiftService {
	@Autowired
	DefaultShiftDao shiftDao;

	@Override
	@Transactional
	public Iterable<MonthlyShiftModel> saveShifts(List<MonthlyShiftModel> shiftModel) {
		return shiftDao.saveAll(shiftModel);
	}

	@Override
	@Transactional
	public Iterable<MonthlyShiftModel> updateShifts(List<MonthlyShiftModel> shiftModel) {
		return shiftDao.saveAll(shiftModel);
	}

	@Override
	@Transactional
	public Iterable<MonthlyShiftModel> getAllShiftsByMonth(String month, String year) {
		return shiftDao.findByMonthAndYear(month, year);
	}

	@Override
	@Transactional
	public Iterable<MonthlyShiftModel> getAllShiftsByMonthAndUser(String month, String year, int userId) {
		return shiftDao.findByMonthAndYearAndUserId(month, year, userId);
	}
}
