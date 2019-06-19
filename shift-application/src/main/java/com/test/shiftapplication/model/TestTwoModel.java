package com.test.shiftapplication.model;

public class TestTwoModel {
	private long userId;
	private String test;

	public long getUSerId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	@Override
	public String toString() {
		return "TestTwoModel [UserId=" + userId + ", test=" + test + "]";
	}
}
