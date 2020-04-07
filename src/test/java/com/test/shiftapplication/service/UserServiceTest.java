package com.test.shiftapplication.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.shiftapplication.ShiftApplication;
import com.test.shiftapplication.dao.DefaultUserDao;
import com.test.shiftapplication.model.UserModel;
import com.test.shiftapplication.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShiftApplication.class)
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@MockBean
	private DefaultUserDao userDao;

	@Test
	public void getAllUsersAndVerifyTheListSize_whenPassedWithRequiredParameters() {
		when(userDao.findAll()).thenReturn(Stream.of(
				new UserModel(1L, "user-1", "504001", "ss@gmail.com", "123456789", "pass", "src:images", "test time"),
				new UserModel(1L, "user-1", "504001", "ss@gmail.com", "123456789", "pass", "src:images", "test time"))
				.collect(Collectors.toList()));
		assertEquals(2, ((List<UserModel>) userService.findAll()).size());
	}

	@Test
	public void saveAndVerifyUserWhenAllParametersArePassed() {
		UserModel user = new UserModel(1L, "user-1", "504001", "ss@gmail.com", "123456789", "pass", "src:images",
				"test time");
		when(userDao.save(user)).thenReturn(user);
		assertEquals(user, userService.saveUser(user));
	}

	@Test
	public void deleteUserWithParticularID() {
		userService.deleteUser(1L);
		verify(userDao, times(1)).deleteById(1L);
	}
}
