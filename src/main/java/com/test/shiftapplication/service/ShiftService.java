package com.test.shiftapplication.service;

import java.util.List;

import com.test.shiftapplication.model.MonthlyShiftModel;

public interface ShiftService {
	Iterable<MonthlyShiftModel> saveShifts(List<MonthlyShiftModel> shiftModel);

	Iterable<MonthlyShiftModel> updateShifts(List<MonthlyShiftModel> shiftModel);

	Iterable<MonthlyShiftModel> getAllShiftsByMonth(String month, String year);

	Iterable<MonthlyShiftModel> getAllShiftsByMonthAndUser(String month, String year, int i);
}
