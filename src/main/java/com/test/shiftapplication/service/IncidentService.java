package com.test.shiftapplication.service;

import java.util.Date;
import java.util.List;

import com.test.shiftapplication.model.IncidentModel;

public interface IncidentService {
	IncidentModel saveIncidentDetails(IncidentModel incidentModel);

	Iterable<IncidentModel> findAll();
	
	IncidentModel updateIncidentDetails(IncidentModel incidentModel);

	void deleteIncident(long incidentId);

	IncidentModel findByIncidentNumber(String incidentsNumber);

	List<IncidentModel> findByApplicationName(String applicationName);

	List<IncidentModel> findByAssigneeName(String assigneeName);

	List<IncidentModel> findByIssueDate(Date issueDate);

	List<IncidentModel> findByCurrentStatus(String currentStatus);

	List<IncidentModel> findByIssueDateBetween(Date startDate, Date endDate);

	List<IncidentModel> findByIssueDateAfter(String afterDate);

	List<IncidentModel> findByIssueDateBefore(Date beforeDate);
}
