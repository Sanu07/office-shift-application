package com.test.shiftapplication.model;

import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.hibernate.annotations.SortNatural;

@Entity
@Table(name = "SHIFT_DETAILS")
public class ShiftModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SHIFT_ID")
	private long shiftId;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "USER_ID")
	private String userId;
	@Column(name = "SHIFT_ALLOWANCE")
	private double shiftAllowance;
	@Column(name = "MONTH")
	private String month;
	@ElementCollection
	@MapKeyColumn(name = "SHIFT_DATE")
	@Column(name = "SHIFT_TYPE")
	@SortNatural
	Map<String, String> shiftDateAndType = new TreeMap<>();

	public long getShiftId() {
		return shiftId;
	}

	public void setShiftId(long shiftId) {
		this.shiftId = shiftId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getShiftAllowance() {
		return shiftAllowance;
	}

	public void setShiftAllowance(double shiftAllowance) {
		this.shiftAllowance = shiftAllowance;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Map<String, String> getShiftDateAndType() {
		return shiftDateAndType;
	}

	public void setShiftDateAndType(Map<String, String> shiftDateAndType) {
		this.shiftDateAndType = shiftDateAndType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		long temp;
		temp = Double.doubleToLongBits(shiftAllowance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((shiftDateAndType == null) ? 0 : shiftDateAndType.hashCode());
		result = prime * result + (int) (shiftId ^ (shiftId >>> 32));
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		ShiftModel other = (ShiftModel) obj;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (Double.doubleToLongBits(shiftAllowance) != Double.doubleToLongBits(other.shiftAllowance))
			return false;
		if (shiftDateAndType == null) {
			if (other.shiftDateAndType != null)
				return false;
		} else if (!shiftDateAndType.equals(other.shiftDateAndType))
			return false;
		if (shiftId != other.shiftId)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public ShiftModel(String userName, String userId, double shiftAllowance, String month,
			Map<String, String> shiftDateAndType) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.shiftAllowance = shiftAllowance;
		this.month = month;
		this.shiftDateAndType = shiftDateAndType;
	}

	public ShiftModel() {
		super();
	}

	@Override
	public String toString() {
		return "ShiftModel [shiftId=" + shiftId + ", userName=" + userName + ", userId=" + userId + ", shiftAllowance="
				+ shiftAllowance + ", month=" + month + ", shiftDateAndType=" + shiftDateAndType + "]";
	}
}
