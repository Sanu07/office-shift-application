package com.test.shiftapplication.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.shiftapplication.dao.DefaultJiraDao;
import com.test.shiftapplication.model.JiraModel;
import com.test.shiftapplication.service.JiraService;

@Service("jiraService")
public class JiraServiceImpl implements JiraService {
	@Autowired
	DefaultJiraDao defaultJiraDao;

	@Override
	@Transactional
	public JiraModel saveJira(JiraModel jiraModel) {
		return defaultJiraDao.save(jiraModel);
	}

	@Override
	@Transactional
	public Iterable<JiraModel> findAll() {
		return defaultJiraDao.findAll();
	}

	@Override
	@Transactional
	public JiraModel updateJira(JiraModel jiraModel) {
		return defaultJiraDao.save(jiraModel);
	}

	@Override
	@Transactional
	public void deleteJira(long jiraId) {
		defaultJiraDao.deleteById(jiraId);
	}

	@Override
	@Transactional
	public JiraModel findByJiraNumber(String jiraNumber) {
		return defaultJiraDao.findByJiraNumber(jiraNumber);
	}

	@Override
	@Transactional
	public List<JiraModel> findByApplicationName(String applicationName) {
		return defaultJiraDao.findByapplicationName(applicationName);
	}

	@Override
	@Transactional
	public List<JiraModel> findByAssigneeName(String assigneeName) {
		return defaultJiraDao.findByAssigneeName(assigneeName);
	}

	@Override
	@Transactional
	public List<JiraModel> findByReporterName(String reporterName) {
		return defaultJiraDao.findByReporterName(reporterName);
	}

	@Override
	@Transactional
	public List<JiraModel> findByCurrentStatus(String currentStatus) {
		return defaultJiraDao.findByCurrentStatus(currentStatus);
	}

	@Override
	public List<JiraModel> findByDeploymentDate(Date deploymentDate) {
		return defaultJiraDao.findByDeploymentDate(deploymentDate);
	}

	@Override
	public List<JiraModel> findByIssueDateAfter(String finalDate) {
		return defaultJiraDao.findByIssueDateAfter(finalDate);
	}
}
