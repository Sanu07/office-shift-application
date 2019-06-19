package com.test.shiftapplication.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.test.shiftapplication.model.JiraModel;
import com.test.shiftapplication.model.UserModel;
import com.test.shiftapplication.service.JiraService;

@RestController
public class JiraController {
	@Autowired
	JiraService jiraService;

	@PostMapping(value = "/jiras")
	public @ResponseBody Object save(@RequestBody JiraModel jiraDetails, HttpSession session) {
		// JiraModel jiraModel = new JiraModel("EDU-1212", "529", "Reporter
		// Name1", "Assignee Name1", new Date(2017,01,20), new Date(2017,01,21),
		// new Date(2017,01,22), "2", "completed", "Summary of jira 1",
		// "Comments 1", "4");
		UserModel userModel = (UserModel) session.getAttribute("user-profile");
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		jiraDetails.setUpdatedAt(dateFormat.format(date));
		jiraDetails.setCreatedAt(dateFormat.format(date));
		jiraDetails.setUpdatedBy(userModel.getName());
		jiraDetails.setCreatedBy(userModel.getName());
		return jiraService.saveJira(jiraDetails);
	}

	@GetMapping("/jiras")
	public @ResponseBody Object findAll() {
		return jiraService.findAll();
	}

	@PutMapping("/jiras")
	public @ResponseBody Object update(@RequestBody JiraModel jiraModel, HttpSession session) {
		UserModel userModel = (UserModel) session.getAttribute("user-profile");
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		jiraModel.setUpdatedAt(dateFormat.format(date));
		jiraModel.setUpdatedBy(userModel.getName());
		return jiraService.updateJira(jiraModel);
	}

	@DeleteMapping("/jiras/{id}")
	public void deleteJira(@PathVariable("id") long jiraId) {
		jiraService.deleteJira(jiraId);
	}

	@GetMapping("/jiras/jiraNumber/{jiraNumber}")
	public JiraModel findByJiraNumber(@PathVariable("jiraNumber") String jiraNumber) {
		return jiraService.findByJiraNumber(jiraNumber);
	}

	@GetMapping("/jiras/applicationName/{applicationName}")
	public List<JiraModel> findByApplicationName(@PathVariable("applicationName") String applicationName) {
		return jiraService.findByApplicationName(applicationName);
	}

	@GetMapping("/jiras/assigneeName/{assigneeName}")
	public List<JiraModel> findByAssigneeName(@PathVariable("assigneeName") String assigneeName) {
		return jiraService.findByAssigneeName(assigneeName);
	}

	@GetMapping("/jiras/reporterName/{reporterName}")
	public List<JiraModel> findByReporterName(@PathVariable("reporterName") String reporterName) {
		return jiraService.findByReporterName(reporterName);
	}

	@GetMapping("/jiras/currentStatus/{currentStatus}")
	public List<JiraModel> findByCurrentStatus(@PathVariable("currentStatus") String currentStatus) {
		return jiraService.findByCurrentStatus(currentStatus);
	}

	@GetMapping("/jiras/deploymentDate/{deploymentDate}")
	public List<JiraModel> findByDeploymentDate(@PathVariable("deploymentDate") String deploymentDate) {
		SimpleDateFormat databaseDateFormat = new SimpleDateFormat("mm/dd/yyyy");
		Date convertedDeploymentDate = null;
		try {
			convertedDeploymentDate = databaseDateFormat.parse(deploymentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jiraService.findByDeploymentDate(convertedDeploymentDate);
	}

	// this method is used to find the jiras created after a particular date
	@GetMapping("/jiras/afterDate/{afterDate}")
	public List<JiraModel> findByJirasDateAfter(@PathVariable("afterDate") String afterDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		List<JiraModel> listOfJirasResponse = new ArrayList<>();
		Date receivedDate = null;
		try {
			receivedDate = dateFormat.parse(afterDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		listOfJirasResponse = getListOfJiras(receivedDate);
		return listOfJirasResponse;
	}

	private List<JiraModel> getListOfJiras(Date receivedDate) {
		SimpleDateFormat requiredDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		List<JiraModel> listOfJiras = (List<JiraModel>) jiraService.findAll();
		List<JiraModel> listOfJirasResponse = new ArrayList<>();
		Date getDate = null;
		for (int i = 0; i < listOfJiras.size(); i++) {
			try {
				getDate = requiredDateFormat.parse(listOfJiras.get(i).getCreatedAt());
				if (receivedDate.before(getDate)) {
					listOfJirasResponse.add(listOfJiras.get(i));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listOfJirasResponse;
	}
}
