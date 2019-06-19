package com.test.shiftapplication.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.shiftapplication.dao.DefaultIncidentDao;
import com.test.shiftapplication.model.IncidentModel;
import com.test.shiftapplication.service.IncidentService;

@Service("incidentService")
public class IncidentServiceImpl implements IncidentService {

	@Autowired
	DefaultIncidentDao defaultIncidentDao;

	@Override
	@Transactional
	public IncidentModel saveIncidentDetails(IncidentModel incidentModel) {
		return defaultIncidentDao.save(incidentModel);
	}

	@Override
	@Transactional
	public Iterable<IncidentModel> findAll() {
		return defaultIncidentDao.findAll();
	}

	@Override
	@Transactional
	public IncidentModel updateIncidentDetails(IncidentModel incidentModel) {
		return defaultIncidentDao.save(incidentModel);
	}

	@Override
	@Transactional
	public void deleteIncident(long incidentId) {
		defaultIncidentDao.deleteById(incidentId);
	}

	@Override
	@Transactional
	public IncidentModel findByIncidentNumber(String incidentNumber) {
		return defaultIncidentDao.findByIncidentNumber(incidentNumber);
	}

	@Override
	@Transactional
	public List<IncidentModel> findByApplicationName(String applicationName) {
		return defaultIncidentDao.findByApplicationName(applicationName);
	}

	@Override
	@Transactional
	public List<IncidentModel> findByAssigneeName(String assigneeName) {
		return defaultIncidentDao.findByAssigneeName(assigneeName);
	}

	@Override
	@Transactional
	public List<IncidentModel> findByIssueDate(Date issueDate) {
		return defaultIncidentDao.findByIssueDate(issueDate);
	}

	@Override
	@Transactional
	public List<IncidentModel> findByCurrentStatus(String currentStatus) {
		return defaultIncidentDao.findByCurrentStatusOrderByApplicationName(currentStatus);
	}

	@Override
	public List<IncidentModel> findByIssueDateBetween(Date startDate, Date endDate) {
		return defaultIncidentDao.findByIssueDateBetween(startDate, endDate);
	}

	@Override
	public List<IncidentModel> findByIssueDateAfter(String afterDate) {
		return defaultIncidentDao.findByIssueDateAfter(afterDate);
	}

	@Override
	public List<IncidentModel> findByIssueDateBefore(Date beforeDate) {
		return defaultIncidentDao.findByIssueDateBefore(beforeDate);
	}
}
