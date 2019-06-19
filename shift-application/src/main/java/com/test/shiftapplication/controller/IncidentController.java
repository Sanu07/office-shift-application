package com.test.shiftapplication.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	@PostMapping(value = "/incidents", produces = "application/json", consumes = "application/json")
	public @ResponseBody Object saveIncident(@RequestBody IncidentModel incidentModel) {
		return incidentService.saveIncidentDetails(incidentModel);
	}

	@GetMapping("/incidents")
	public @ResponseBody Object findAll() {
		return incidentService.findAll();
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

	@GetMapping("/incidents/beforeDate/{beforeDate}")
	public List<IncidentModel> findByIssueDateBefore(
			@PathVariable("beforeDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beforeDate) {
		return incidentService.findByIssueDateBefore(beforeDate);
	}
}
