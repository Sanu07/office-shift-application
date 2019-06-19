package com.test.shiftapplication.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class IndividualShiftModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long individualShiftId;
	private int date;
	private String dayOfTheWeek;
	private String shift;
	@ManyToOne
	@JoinColumn(name = "shiftId")
	private MonthlyShiftModel monthlyShiftModel;

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public void setDayOfTheWeek(String dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public MonthlyShiftModel getMonthlyShiftModel() {
		return monthlyShiftModel;
	}

	public void setMonthlyShiftModel(MonthlyShiftModel monthlyShiftModel) {
		this.monthlyShiftModel = monthlyShiftModel;
	}
}
