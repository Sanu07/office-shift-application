package com.test.shiftapplication.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.test.shiftapplication.model.JiraModel;

public interface DefaultJiraDao extends CrudRepository<JiraModel, Long> {
	JiraModel findByJiraNumber(@Param("jiraNumber") String jiraNumber);

	List<JiraModel> findByapplicationName(@Param("applicationName") String applicationName);

	List<JiraModel> findByAssigneeName(@Param("assigneeName") String assigneeName);

	List<JiraModel> findByReporterName(@Param("reporterName") String reporterName);

	@Query("from JiraModel jd where Date(jd.deploymentDate) = :deploymentDate")
	List<JiraModel> findByDeploymentDate(@Param("deploymentDate") Date deploymentDate);

	List<JiraModel> findByCurrentStatus(@Param("currentStatus") String currentStatus);

	@Query("from JiraModel jm where jm.createdAt > :createdAt")
	List<JiraModel> findByIssueDateAfter(@Param("createdAt") String finalDate);
}
