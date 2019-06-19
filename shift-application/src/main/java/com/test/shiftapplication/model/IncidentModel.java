package com.test.shiftapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INCIDENT_DETAILS")
public class IncidentModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INCIDENT_ID")
	private long incidentId;
	@Column(name = "INCIDENT_NUMBER")
	private String incidentNumber;
	@Column(name = "APPLICATION_NAME")
	private String applicationName;
	@Column(name = "ACKNOWLEDGED_BY")
	private String acknowledgedBy;
	@Column(name = "ASSIGNEE_NAME")
	private String assigneeName;
	@Column(name = "ISSUE_DATE")
	private String issueDate; /* time should also be included */
	@Column(name = "CURRENT_STATUS")
	private String currentStatus;
	@Column(name = "ISSUE_SUMMARY")
	private String issueSummary;
	@Column(name = "ISSUE_DESCRIPTION")
	private String issueDescription;
	@Column(name = "COMMENTS_ON_RESOLUTION")
	private String commentsOnResolution;
	@Column(name = "TIME_SPENT")
	private String timeSpent;
	@Column(name = "LINKED_JIRA")
	private String linkedJira;

	public long getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(long incidentId) {
		this.incidentId = incidentId;
	}

	public String getIncidentNumber() {
		return incidentNumber;
	}

	public void setIncidentNumber(String incidentNumber) {
		this.incidentNumber = incidentNumber;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getAcknowledgedBy() {
		return acknowledgedBy;
	}

	public void setAcknowledgedBy(String acknowledgedBy) {
		this.acknowledgedBy = acknowledgedBy;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
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

	public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public String getCommentsOnResolution() {
		return commentsOnResolution;
	}

	public void setCommentsOnResolution(String commentsOnResolution) {
		this.commentsOnResolution = commentsOnResolution;
	}

	public String getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}

	public String getLinkedJira() {
		return linkedJira;
	}

	public void setLinkedJira(String linkedJira) {
		this.linkedJira = linkedJira;
	}

	public IncidentModel() {
		super();
	}

	public IncidentModel(String incidentNumber, String applicationName, String acknowledgedBy, String assigneeName,
			String issueDate, String currentStatus, String issueSummary, String issueDescription,
			String commentsOnResolution, String timeSpent, String linkedJira) {
		super();
		this.incidentNumber = incidentNumber;
		this.applicationName = applicationName;
		this.acknowledgedBy = acknowledgedBy;
		this.assigneeName = assigneeName;
		this.issueDate = issueDate;
		this.currentStatus = currentStatus;
		this.issueSummary = issueSummary;
		this.issueDescription = issueDescription;
		this.commentsOnResolution = commentsOnResolution;
		this.timeSpent = timeSpent;
		this.linkedJira = linkedJira;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acknowledgedBy == null) ? 0 : acknowledgedBy.hashCode());
		result = prime * result + ((applicationName == null) ? 0 : applicationName.hashCode());
		result = prime * result + ((assigneeName == null) ? 0 : assigneeName.hashCode());
		result = prime * result + ((commentsOnResolution == null) ? 0 : commentsOnResolution.hashCode());
		result = prime * result + ((currentStatus == null) ? 0 : currentStatus.hashCode());
		result = prime * result + (int) (incidentId ^ (incidentId >>> 32));
		result = prime * result + ((incidentNumber == null) ? 0 : incidentNumber.hashCode());
		result = prime * result + ((issueDate == null) ? 0 : issueDate.hashCode());
		result = prime * result + ((issueDescription == null) ? 0 : issueDescription.hashCode());
		result = prime * result + ((issueSummary == null) ? 0 : issueSummary.hashCode());
		result = prime * result + ((linkedJira == null) ? 0 : linkedJira.hashCode());
		result = prime * result + ((timeSpent == null) ? 0 : timeSpent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IncidentModel other = (IncidentModel) obj;
		if (acknowledgedBy == null) {
			if (other.acknowledgedBy != null)
				return false;
		} else if (!acknowledgedBy.equals(other.acknowledgedBy))
			return false;
		if (applicationName == null) {
			if (other.applicationName != null)
				return false;
		} else if (!applicationName.equals(other.applicationName))
			return false;
		if (assigneeName == null) {
			if (other.assigneeName != null)
				return false;
		} else if (!assigneeName.equals(other.assigneeName))
			return false;
		if (commentsOnResolution == null) {
			if (other.commentsOnResolution != null)
				return false;
		} else if (!commentsOnResolution.equals(other.commentsOnResolution))
			return false;
		if (currentStatus == null) {
			if (other.currentStatus != null)
				return false;
		} else if (!currentStatus.equals(other.currentStatus))
			return false;
		if (incidentId != other.incidentId)
			return false;
		if (incidentNumber == null) {
			if (other.incidentNumber != null)
				return false;
		} else if (!incidentNumber.equals(other.incidentNumber))
			return false;
		if (issueDate == null) {
			if (other.issueDate != null)
				return false;
		} else if (!issueDate.equals(other.issueDate))
			return false;
		if (issueDescription == null) {
			if (other.issueDescription != null)
				return false;
		} else if (!issueDescription.equals(other.issueDescription))
			return false;
		if (issueSummary == null) {
			if (other.issueSummary != null)
				return false;
		} else if (!issueSummary.equals(other.issueSummary))
			return false;
		if (linkedJira == null) {
			if (other.linkedJira != null)
				return false;
		} else if (!linkedJira.equals(other.linkedJira))
			return false;
		if (timeSpent == null) {
			if (other.timeSpent != null)
				return false;
		} else if (!timeSpent.equals(other.timeSpent))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IncidentModel [incidentId=" + incidentId + ", incidentNumber=" + incidentNumber + ", applicationName="
				+ applicationName + ", acknowledgedBy=" + acknowledgedBy + ", assigneeName=" + assigneeName
				+ ", issueDate=" + issueDate + ", currentStatus=" + currentStatus + ", issueSummary=" + issueSummary
				+ ", issueDescription=" + issueDescription + ", commentsOnResolution=" + commentsOnResolution
				+ ", timeSpent=" + timeSpent + ", linkedJira=" + linkedJira + "]";
	}
}
