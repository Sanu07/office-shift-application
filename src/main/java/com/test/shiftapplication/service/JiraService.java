package com.test.shiftapplication.service;

import java.util.Date;
import java.util.List;

import com.test.shiftapplication.model.JiraModel;

public interface JiraService {
	JiraModel saveJira(JiraModel jiraModel);

	Iterable<JiraModel> findAll();

	JiraModel updateJira(JiraModel jiraModel);

	void deleteJira(long jiraId);

	JiraModel findByJiraNumber(String jiraNumber);

	List<JiraModel> findByApplicationName(String applicationName);

	List<JiraModel> findByAssigneeName(String assigneeName);

	List<JiraModel> findByReporterName(String reporterName);

	List<JiraModel> findByCurrentStatus(String currentStatus);

	List<JiraModel> findByDeploymentDate(Date deploymentDate);

	List<JiraModel> findByIssueDateAfter(String finalDate);
}
