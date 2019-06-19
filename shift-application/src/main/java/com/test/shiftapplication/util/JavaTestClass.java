package com.test.shiftapplication.util;

public class JavaTestClass {
	public static void main(String[] args) {
		String str = "hackerhappy";
		String match = "hackerrank";
		int length = str.length() > match.length() ? match.length() : str.length();
		int k =9;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			if (str.charAt(i) == match.charAt(i)) {
				buffer.append(match.charAt(i));
			}
		}
		int totalOp = (match.length() + str.length()) - buffer.length();
		if (totalOp <= k) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}
