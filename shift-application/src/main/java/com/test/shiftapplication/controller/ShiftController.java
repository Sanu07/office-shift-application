package com.test.shiftapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.shiftapplication.model.MonthlyShiftModel;
import com.test.shiftapplication.service.ShiftService;

@RestController
public class ShiftController {
	@Autowired
	ShiftService shiftService;

	@PostMapping("/shifts")
	public @ResponseBody Object saveShifts(@RequestBody List<MonthlyShiftModel> shiftModel) {
		return shiftService.saveShifts(shiftModel);
	}

	@PutMapping("/shifts")
	public @ResponseBody Object updateShifts(@RequestBody List<MonthlyShiftModel> shiftModel) {
		return shiftService.updateShifts(shiftModel);
	}

	@GetMapping("/shifts/month/{month}/{year}")
	public @ResponseBody Object getAllShiftsByMonth(@PathVariable("month") String month,
			@PathVariable("year") String year) {
		List<MonthlyShiftModel> list = (List<MonthlyShiftModel>) shiftService.getAllShiftsByMonth(month, year);
		return list;
	}

	@GetMapping("/checkIfShiftPlanExists")
	public @ResponseBody Boolean checkIfShiftPlanExists() {
		return false;
	}

	@GetMapping("/shifts/month/{month}/{year}/{userId}")
	public @ResponseBody Object getAllShiftsByMonthAndUser(@PathVariable("month") String month,
			@PathVariable("year") String year, @PathVariable("userId") String userId) {
		List<MonthlyShiftModel> list = (List<MonthlyShiftModel>) shiftService.getAllShiftsByMonthAndUser(month, year,
				Integer.parseInt(userId));
		return list;
	}
}
