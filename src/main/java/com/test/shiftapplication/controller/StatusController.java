package com.test.shiftapplication.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.shiftapplication.model.StatusModel;
import com.test.shiftapplication.service.StatusService;

@RestController
public class StatusController {
	@Autowired
	StatusService statusService;

	@PostMapping("/status")
	public @ResponseBody Object saveStatus(@RequestBody StatusModel statusModel) {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR);
		int minutes = calendar.get(Calendar.MINUTE);
		if (calendar.get(Calendar.AM_PM) == Calendar.AM) {
			statusModel.setTime(hour + ":" + minutes + " AM");
		} else {
			statusModel.setTime(hour + ":" + minutes + " PM");
		}
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
		statusModel.setDate(dateFormat.format(date));
		return statusService.saveStatus(statusModel);
	}

	@GetMapping("/status")
	public @ResponseBody Object findAllStatus() {
		return statusService.getAllStatus();
	}
}
