package com.test.shiftapplication.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	
	List<JiraModel> jiras = new ArrayList<JiraModel>();

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
		return (List<JiraModel>) jiraService.findAll();	
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
	
	@GetMapping("/jiras/{afterDate}/past12")
	public List<Map<String, Integer>> getPastJiras(@PathVariable("afterDate") String afterDate) {
		return getPastTwelveMonthsJiras((List<JiraModel>)jiraService.findAll());
	}
	
	private List<Map<String, Integer>> getPastTwelveMonthsJiras(List<JiraModel> listOfJiras) {
		Map<String, Integer> jirasMap = new LinkedHashMap<>();
		Map<String, Integer> jiraStatusMap = new LinkedHashMap<String, Integer>();
		List<Map<String, Integer>> list = new ArrayList<Map<String,Integer>>();
		SimpleDateFormat dbDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		SimpleDateFormat mapKeyFormat = new SimpleDateFormat("MMM-yyyy");
		jirasMap.put(monthYear(new Date(), -11), 0);
		jirasMap.put(monthYear(new Date(), -10), 0);
		jirasMap.put(monthYear(new Date(), -9), 0);
		jirasMap.put(monthYear(new Date(), -8), 0);
		jirasMap.put(monthYear(new Date(), -7), 0);
		jirasMap.put(monthYear(new Date(), -6), 0);
		jirasMap.put(monthYear(new Date(), -5), 0);
		jirasMap.put(monthYear(new Date(), -4), 0);
		jirasMap.put(monthYear(new Date(), -3), 0);
		jirasMap.put(monthYear(new Date(), -2), 0);
		jirasMap.put(monthYear(new Date(), -1), 0);
		jirasMap.put(monthYear(new Date(), -0), 0);
		jiraStatusMap.put("Open", 0);
		jiraStatusMap.put("Closed", 0);
		for (int i = 0; i < listOfJiras.size(); i++) {
			String mapKey = null;
			String statusMapKey = null;
			try {
				mapKey = mapKeyFormat.format(dbDateFormat.parse(listOfJiras.get(i).getCreatedAt()));
				statusMapKey = listOfJiras.get(i).getCurrentStatus(); 
				if (jirasMap.containsKey(mapKey)) {
					jirasMap.put(mapKey, jirasMap.get(mapKey) + 1);
				}
				jiraStatusMap.put(statusMapKey, jiraStatusMap.get(statusMapKey) + 1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		list.add(jirasMap);
		list.add(jiraStatusMap);
		return list;
	}
	
	private String monthYear(Date date, int diff) {
		if (date == null) {
			date = new Date();
		}
		SimpleDateFormat monthYear = new SimpleDateFormat("MMM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, diff);
		Date resultDate = cal.getTime();
		return monthYear.format(resultDate);
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
