package com.test.shiftapplication.model;

public class Shift {
	private String userId;

	private String name;

	private String dayOfTheWeek;

	private int date;

	private String shift;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public void setDayOfTheWeek(String dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	@Override
	public String toString() {
		return "Shift [userId=" + userId + ", name=" + name + ", dayOfTheWeek=" + dayOfTheWeek + ", date=" + date
				+ ", shift=" + shift + "]";
	}
}
