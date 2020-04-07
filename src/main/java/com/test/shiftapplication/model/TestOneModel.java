package com.test.shiftapplication.model;

public class TestOneModel {
	private long shiftId;
	private String testOne;
	private TestTwoModel testTwoModel;

	public long getShiftId() {
		return shiftId;
	}

	public void setShiftId(long shiftId) {
		this.shiftId = shiftId;
	}

	public String getTestOne() {
		return testOne;
	}

	public void setTestOne(String testOne) {
		this.testOne = testOne;
	}

	public TestTwoModel getTestTwoModel() {
		return testTwoModel;
	}

	public void setTestTwoModel(TestTwoModel testTwoModel) {
		this.testTwoModel = testTwoModel;
	}

	@Override
	public String toString() {
		return "TestOneModel [shiftId=" + shiftId + ", testOne=" + testOne + ", testTwoModel=" + testTwoModel + "]";
	}
}
