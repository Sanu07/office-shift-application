package com.test.shiftapplication.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "JIRA_DETAILS")
public class JiraModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "JIRA_ID")
	private long jiraId;
	@Column(name = "JIRA_NUMBER")
	private String jiraNumber;
	@Column(name = "APPLICATION_NAME")
	private String applicationName;
	@Column(name = "REPORTER_NAME")
	private String reporterName;
	@Column(name = "ASSIGNEE_NAME")
	private String assigneeName;
	@Column(name = "START_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date startDate;
	@Column(name = "END_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date endDate;
	@Column(name = "DEPLOYMENT_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date deploymentDate;
	@Column(name = "CURRENT_STATUS")
	private String currentStatus;
	@Column(name = "ISSUE_SUMMARY")
	private String issueSummary;
	@Column(name = "COMMENTS")
	private String comments;
	@Column(name = "TIME_SPENT")
	private String timeSpent;
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	@Column(name = "UPDATED_AT")
	private String updatedAt;
	@Column(name = "CREATED_AT")
	private String createdAt;
	@Column(name = "CREATED_BY")
	private String createdBy;

	public JiraModel() {
	 super();

	}

	public long getJiraId() {
	 return jiraId;
	}

	public void setJiraId(long jiraId) {
	 this.jiraId = jiraId;
	}

	public String getJiraNumber() {
	 return jiraNumber;
	}

	public void setJiraNumber(String jiraNumber) {
	 this.jiraNumber = jiraNumber;
	}

	public String getApplicationName() {
	 return applicationName;
	}

	public void setApplicationName(String applicationName) {
	 this.applicationName = applicationName;
	}

	public String getReporterName() {
	 return reporterName;
	}

	public void setReporterName(String reporterName) {
	 this.reporterName = reporterName;
	}

	public String getAssigneeName() {
	 return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
	 this.assigneeName = assigneeName;
	}

	public Date getStartDate() {
	 return startDate;
	}

	public void setStartDate(Date startDate) {
	 this.startDate = startDate;
	}

	public Date getEndDate() {
	 return endDate;
	}

	public void setEndDate(Date endDate) {
	 this.endDate = endDate;
	}

	public Date getDeploymentDate() {
	 return deploymentDate;
	}

	public void setDeploymentDate(Date deploymentDate) {
	 this.deploymentDate = deploymentDate;
	}

	public String getCurrentStatus() {
	 return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
	 this.currentStatus = currentStatus;
	}

	public String getIssueSummary() {
	 return issueSummary;
	}

	public void setIssueSummary(String issueSummary) {
	 this.issueSummary = issueSummary;
	}

	public String getComments() {
	 return comments;
	}

	public void setComments(String comments) {
	 this.comments = comments;
	}

	public String getTimeSpent() {
	 return timeSpent;
	}

	public void setTimeSpent(String timeSpent) {
	 this.timeSpent = timeSpent;
	}

	public String getUpdatedBy() {
	 return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
	 this.updatedBy = updatedBy;
	}

	public String getUpdatedAt() {
	 return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
	 this.updatedAt = updatedAt;
	}

	public String getCreatedAt() {
	 return createdAt;
	}

	public void setCreatedAt(String createdAt) {
	 this.createdAt = createdAt;
	}

	public String getCreatedBy() {
	 return createdBy;
	}

	public void setCreatedBy(String createdBy) {
	 this.createdBy = createdBy;
	}

	public JiraModel(long jiraId, String jiraNumber, String applicationName, String reporterName, String assigneeName,
			Date startDate, Date endDate, Date deploymentDate, String currentStatus, String issueSummary,
			String comments, String timeSpent, String updatedBy, String updatedAt, String createdAt, String createdBy) {
		super();
		this.jiraId = jiraId;
		this.jiraNumber = jiraNumber;
		this.applicationName = applicationName;
		this.reporterName = reporterName;
		this.assigneeName = assigneeName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.deploymentDate = deploymentDate;
		this.currentStatus = currentStatus;
		this.issueSummary = issueSummary;
		this.comments = comments;
		this.timeSpent = timeSpent;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}

	public JiraModel(String jiraNumber, String applicationName, String reporterName, String assigneeName,
			Date startDate, Date endDate, Date deploymentDate, String currentStatus, String issueSummary,
			String comments, String timeSpent, String updatedBy, String updatedAt, String createdAt, String createdBy) {
		super();
		this.jiraNumber = jiraNumber;
		this.applicationName = applicationName;
		this.reporterName = reporterName;
		this.assigneeName = assigneeName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.deploymentDate = deploymentDate;
		this.currentStatus = currentStatus;
		this.issueSummary = issueSummary;
		this.comments = comments;
		this.timeSpent = timeSpent;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}
}
