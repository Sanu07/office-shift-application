package com.test.shiftapplication.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.shiftapplication.model.IncidentModel;
import com.test.shiftapplication.service.IncidentService;

@RestController
public class IncidentController {
	@Autowired
	IncidentService incidentService;

	List<IncidentModel> incidents;

	@PostMapping(value = "/incidents", produces = "application/json", consumes = "application/json")
	public @ResponseBody Object saveIncident(@RequestBody IncidentModel incidentModel) {
		return incidentService.saveIncidentDetails(incidentModel);
	}

	@GetMapping("/incidents")
	public @ResponseBody Object findAll() {
		return (List<IncidentModel>) incidentService.findAll();
	}

	@PutMapping(value = "/incidents", produces = "application/json", consumes = "application/json")
	public @ResponseBody Object updateIncidents(@RequestBody IncidentModel incidentModel) {
		return incidentService.updateIncidentDetails(incidentModel);
	}

	@DeleteMapping("/incidents/{id}")
	public void deleteIncident(@PathVariable("id") long incidentId) {
		incidentService.deleteIncident(incidentId);
	}

	@GetMapping("/incidents/incidentNumber/{incidentNumber}")
	public IncidentModel findByJiraNumber(@PathVariable("incidentNumber") String incidentNumber) {
		return incidentService.findByIncidentNumber(incidentNumber);
	}

	@GetMapping("/incidents/applicationName/{applicationName}")
	public List<IncidentModel> findByApplicationName(@PathVariable("applicationName") String applicationName) {
		return incidentService.findByApplicationName(applicationName);
	}

	@GetMapping("/incidents/assigneeName/{assigneeName}")
	public List<IncidentModel> findByAssigneeName(@PathVariable("assigneeName") String assigneeName) {
		return incidentService.findByAssigneeName(assigneeName);
	}

	@GetMapping("/incidents/issueDate/{issueDate}")
	public List<IncidentModel> findByIssueDate(@PathVariable("issueDate") Date issueDate) {
		return incidentService.findByIssueDate(issueDate);
	}

	@GetMapping("/incidents/currentStatus/{currentStatus}")
	public List<IncidentModel> findByCurrentStatus(@PathVariable("currentStatus") String currentStatus) {
		return incidentService.findByCurrentStatus(currentStatus);
	}

	@GetMapping("/incidents/weekly/{startDate}/{endDate}")
	public List<IncidentModel> findByIssueDateBetween(
			@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		return incidentService.findByIssueDateBetween(startDate, endDate);
	}

	// this method is used to find the incidents created after a particular date
	@GetMapping("/incidents/afterDate/{afterDate}")
	public List<IncidentModel> findByIssueDateAfter(@PathVariable("afterDate") String afterDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		// SimpleDateFormat requiredDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date receivedDate = null;
		List<IncidentModel> listOfIncidents = new ArrayList<>();
		try {
			receivedDate = dateFormat.parse(afterDate);
			listOfIncidents = getListOfIncidents(receivedDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfIncidents;
	}
	
	@GetMapping("/incidents/{afterDate}/past12")
	public List<Map<String, Integer>> getPastIncidents(@PathVariable("afterDate") String afterDate) {
		return getPastTwelveMonthsIncidents((List<IncidentModel>) incidentService.findAll());
	}

	private List<IncidentModel> getListOfIncidents(Date receivedDate) {
		SimpleDateFormat requiredDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		List<IncidentModel> listOfIncidents = (List<IncidentModel>) incidentService.findAll();
		List<IncidentModel> listOfIncidentsResponse = new ArrayList<>();
		Date getDate = null;
		for (int i = 0; i < listOfIncidents.size(); i++) {
				try {
					getDate = requiredDateFormat.parse(listOfIncidents.get(i).getIssueDate());
					if (receivedDate.before(getDate)) {
						listOfIncidentsResponse.add(listOfIncidents.get(i));
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return listOfIncidentsResponse;
	}

	private List<Map<String, Integer>> getPastTwelveMonthsIncidents(List<IncidentModel> listOfIncidents) {
		Map<String, Integer> incidentsMap = new LinkedHashMap<>();
		Map<String, Integer> incidentStatusMap = new LinkedHashMap<>();
		List<Map<String, Integer>> list = new ArrayList<Map<String,Integer>>();
		SimpleDateFormat dbDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		SimpleDateFormat mapKeyFormat = new SimpleDateFormat("MMM-yyyy");
		incidentsMap.put(monthYear(new Date(), -11), 0);
		incidentsMap.put(monthYear(new Date(), -10), 0);
		incidentsMap.put(monthYear(new Date(), -9), 0);
		incidentsMap.put(monthYear(new Date(), -8), 0);
		incidentsMap.put(monthYear(new Date(), -7), 0);
		incidentsMap.put(monthYear(new Date(), -6), 0);
		incidentsMap.put(monthYear(new Date(), -5), 0);
		incidentsMap.put(monthYear(new Date(), -4), 0);
		incidentsMap.put(monthYear(new Date(), -3), 0);
		incidentsMap.put(monthYear(new Date(), -2), 0);
		incidentsMap.put(monthYear(new Date(), -1), 0);
		incidentsMap.put(monthYear(new Date(), -0), 0);
		incidentStatusMap.put("Open", 0);
		incidentStatusMap.put("Closed", 0);
		for (int i = 0; i < listOfIncidents.size(); i++) {
			String mapKey = null;
			String statusMapKey = null;
			try {
				mapKey = mapKeyFormat.format(dbDateFormat.parse(listOfIncidents.get(i).getIssueDate()));
				statusMapKey = listOfIncidents.get(i).getCurrentStatus();
				if (incidentsMap.containsKey(mapKey)) {
					incidentsMap.put(mapKey, incidentsMap.get(mapKey) + 1);
				}
				incidentStatusMap.put(statusMapKey, incidentStatusMap.get(statusMapKey) + 1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		list.add(incidentsMap);
		list.add(incidentStatusMap);
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

	@GetMapping("/incidents/beforeDate/{beforeDate}")
	public List<IncidentModel> findByIssueDateBefore(
			@PathVariable("beforeDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beforeDate) {
		return incidentService.findByIssueDateBefore(beforeDate);
	}
}
