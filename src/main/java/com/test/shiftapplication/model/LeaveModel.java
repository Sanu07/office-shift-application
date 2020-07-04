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
@Table(name = "LEAVE_DETAILS")
public class LeaveModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LEAVE_ID")
	private long leaveId;
	@Column(name = "USER_NAME")
	private String name;
	@Column(name = "COGNIZANT_ID")
	private String cognizantId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "LEAVE_START_DATE")
	private Date leaveStartDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "LEAVE_END_DATE")
	private Date leaveEndDate;
	@Column(name = "NO_OF_LEAVE_DAYS")
	private float noOfLeaveDays;
	@Column(name = "LEAVE_TYPE")
	private String leaveType;
	@Column(name = "APPROVAL_STATUS")
	private boolean approvalStatus;
	@Column(name = "APPROVAL_MESSAGE")
	private String approvalMessage;

	public String getApprovalMessage() {
		return approvalMessage;
	}

	public void setApprovalMessage(String approvalMessage) {
		this.approvalMessage = approvalMessage;
	}

	public String getCognizantId() {
		return cognizantId;
	}

	public void setCognizantId(String cognizantId) {
		this.cognizantId = cognizantId;
	}

	public long getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(long leaveId) {
		this.leaveId = leaveId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLeaveStartDate() {
		return leaveStartDate;
	}

	public void setLeaveStartDate(Date leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	public Date getLeaveEndDate() {
		return leaveEndDate;
	}

	public void setLeaveEndDate(Date leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	public float getNoOfLeaveDays() {
		return noOfLeaveDays;
	}

	public void setNoOfLeaveDays(float noOfLeaveDays) {
		this.noOfLeaveDays = noOfLeaveDays;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public boolean isApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(boolean approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public LeaveModel(String name, Date leaveStartDate, Date leaveEndDate, float noOfLeaveDays, String leaveType,
			boolean approvalStatus) {
		super();
		this.name = name;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.noOfLeaveDays = noOfLeaveDays;
		this.leaveType = leaveType;
		this.approvalStatus = approvalStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (approvalStatus ? 1231 : 1237);
		result = prime * result + ((leaveEndDate == null) ? 0 : leaveEndDate.hashCode());
		result = prime * result + (int) (leaveId ^ (leaveId >>> 32));
		result = prime * result + ((leaveStartDate == null) ? 0 : leaveStartDate.hashCode());
		result = prime * result + ((leaveType == null) ? 0 : leaveType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(noOfLeaveDays);
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
		LeaveModel other = (LeaveModel) obj;
		if (approvalStatus != other.approvalStatus)
			return false;
		if (leaveEndDate == null) {
			if (other.leaveEndDate != null)
				return false;
		} else if (!leaveEndDate.equals(other.leaveEndDate))
			return false;
		if (leaveId != other.leaveId)
			return false;
		if (leaveStartDate == null) {
			if (other.leaveStartDate != null)
				return false;
		} else if (!leaveStartDate.equals(other.leaveStartDate))
			return false;
		if (leaveType == null) {
			if (other.leaveType != null)
				return false;
		} else if (!leaveType.equals(other.leaveType))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(noOfLeaveDays) != Float.floatToIntBits(other.noOfLeaveDays))
			return false;
		return true;
	}

	public LeaveModel() {
		super();
	}

	@Override
	public String toString() {
		return "LeaveModel [leaveId=" + leaveId + ", name=" + name + ", leaveStartDate=" + leaveStartDate
				+ ", leaveEndDate=" + leaveEndDate + ", noOfLeaveDays=" + noOfLeaveDays + ", leaveType=" + leaveType
				+ ", approvalStatus=" + approvalStatus + "]";
	}
}
