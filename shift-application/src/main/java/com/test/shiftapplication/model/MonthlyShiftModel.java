package com.test.shiftapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class MonthlyShiftModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long shiftId;
	private int userId;
	private String name;
	private String month;
	private String year;
	@Type(type="text")
	private String individualShiftModel;
	public long getShiftId() {
	 return shiftId;
	}
	public void setShiftId(long shiftId) {
	 this.shiftId = shiftId;
	}
	public int getUserId() {
	 return userId;
	}
	public void setUserId(int userId) {
	 this.userId = userId;
	}
	public String getName() {
	 return name;
	}
	public void setName(String name) {
	 this.name = name.trim();
	}
	public String getMonth() {
	 return month;
	}
	public void setMonth(String month) {
	 this.month = month.trim();
	}
	public String getYear() {
	 return year;
	}
	public void setYear(String year) {
	 this.year = year.trim();
	}
	public String getIndividualShiftModel() {
	 return individualShiftModel;
	}
	public void setIndividualShiftModel(String individualShiftModel) {
	 this.individualShiftModel = individualShiftModel;
	}
	@Override
	public String toString() {
	 return "MonthlyShiftModel [shiftId=" + shiftId + ", userId=" + userId + ", name=" + name + ", month=" + month
	   + ", year=" + year + ", individualShiftModel=" + individualShiftModel + "]";
	}
}
