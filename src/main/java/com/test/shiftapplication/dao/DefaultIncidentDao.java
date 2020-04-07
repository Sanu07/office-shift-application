package com.test.shiftapplication.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.test.shiftapplication.model.IncidentModel;

@Repository("defaultIncidentDao")
public interface DefaultIncidentDao extends CrudRepository<IncidentModel, Long> {
	IncidentModel findByIncidentNumber(@Param("incidentNumber") String incidentNumber);

	List<IncidentModel> findByApplicationName(@Param("applicationName") String applicationName);

	List<IncidentModel> findByAssigneeName(@Param("assigneeName") String assigneeName);

	@Query("from IncidentModel im where Date(im.issueDate) = :issueDate")
	List<IncidentModel> findByIssueDate(@Param("issueDate") Date issueDate);

	List<IncidentModel> findByCurrentStatusOrderByApplicationName(@Param("currentStatus") String currentStatus);

	List<IncidentModel> findByIssueDateBetween(@Param("startDate") @DateTimeFormat Date startDate,
			@Param("endDate") @DateTimeFormat Date endDate);

	@Query("from IncidentModel im where im.issueDate > :afterDate")
	List<IncidentModel> findByIssueDateAfter(@Param("afterDate") @DateTimeFormat String afterDate);

	List<IncidentModel> findByIssueDateBefore(@Param("beforeDate") @DateTimeFormat Date beforeDate);

}
