package com.test.shiftapplication.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.shiftapplication.model.TestOneModel;

public class TestController {
	@PostMapping(value = "/testURL")
	public void getTestValues(@ModelAttribute TestOneModel testOneModel) {
		System.out.println(testOneModel.toString());
	}
}
