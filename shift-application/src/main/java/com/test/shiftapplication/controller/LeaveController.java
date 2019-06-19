package com.test.shiftapplication.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.shiftapplication.model.LeaveModel;
import com.test.shiftapplication.model.UserModel;
import com.test.shiftapplication.service.LeaveService;

@RestController
public class LeaveController {
	@Autowired
	LeaveService leaveService;

	@PostMapping(value = "/leaves", produces = "application/json", consumes = "application/json")
	public @ResponseBody Object saveLeaves(@RequestBody LeaveModel leaveModel, HttpSession session) {
		UserModel model = (UserModel) session.getAttribute("user-profile");
		leaveModel.setName(model.getName());
		leaveModel.setCognizantId(model.getCognizantId());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(leaveModel.getLeaveStartDate());
		long startDateInMillis = calendar.getTimeInMillis();
		calendar.setTime(leaveModel.getLeaveEndDate());
		long endDateInMillis = calendar.getTimeInMillis();
		int days = (int) ((endDateInMillis - startDateInMillis) / (1000 * 60 * 60 * 24));
		leaveModel.setNoOfLeaveDays(days);
		leaveModel.setApprovalStatus(false);
		return leaveService.saveLeaves(leaveModel);
	}

	@GetMapping("/leaves")
	public @ResponseBody Object findAll() {
		return leaveService.findAll();
	}

	@PutMapping(value = "/leaves", produces = "application/json", consumes = "application/json")
	public @ResponseBody Object updateLeaves(@RequestBody LeaveModel leaveModel) {
		if (leaveModel.isApprovalStatus()) {
			leaveModel.setApprovalMessage("Approved");
		} else {
			leaveModel.setApprovalMessage("Rejected");
		}
		return leaveService.updateLeaves(leaveModel);
	}

	@DeleteMapping("/leaves/{id}")
	public void deleteLeaves(@PathVariable("id") long leaveId) {
		leaveService.deleteLeave(leaveId);
	}

	@GetMapping("/leaves/cognizantId/{cognizantId}")
	public List<LeaveModel> findByCognizantId(@PathVariable("cognizantId") String cognizantId) {
		return leaveService.findByCognizantId(cognizantId);
	}

	@GetMapping("leaves/month/{month}")
	public List<LeaveModel> findLeaveByMonth(@PathVariable("month") Integer month) {
		return leaveService.findLeaveByMonth(month);
	}

	@GetMapping("/leaves/afterDate/{afterDate}")
	public List<LeaveModel> findByLeavesDateAfter(@PathVariable("afterDate") String afterDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		List<LeaveModel> listOfLeavesResponse = new ArrayList<>();
		Date receivedDate = null;
		try {
			receivedDate = dateFormat.parse(afterDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		listOfLeavesResponse = getListOfJiras(receivedDate);
		return listOfLeavesResponse;
	}

	private List<LeaveModel> getListOfJiras(Date receivedDate) {
		SimpleDateFormat requiredDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LeaveModel> listOfLeaves = (List<LeaveModel>) leaveService.findAll();
		List<LeaveModel> listOfLeavesResponse = new ArrayList<>();
		Date getDate = null;
		for (int i = 0; i < listOfLeaves.size(); i++) {
			try {
				getDate = requiredDateFormat.parse(listOfLeaves.get(i).getLeaveStartDate().toString());
				if (receivedDate.before(getDate)) {
					listOfLeavesResponse.add(listOfLeaves.get(i));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listOfLeavesResponse;
	}
}
