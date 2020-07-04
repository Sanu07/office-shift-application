package com.test.shiftapplication.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class UserModelTest {

	UserModel userModel = new UserModel(1L, "user", "504001", "test@email.com", "1234567890", "pass", "pass", "11:11:11");

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void createWhenUserNameIsNullShouldThrowException() throws Exception {
		Assert.hasLength(userModel.getName(), "Username must not be empty");
		Assert.notNull(userModel.getName(), "Name must not be null");
	}
}
