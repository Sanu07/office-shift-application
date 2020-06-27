package com.test.shiftapplication.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.test.shiftapplication.model.LastLoginModel;
import com.test.shiftapplication.model.UserModel;
import com.test.shiftapplication.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ModelAndView saveUser(@RequestParam("form-first-name") String name,
			@RequestParam("form-cognizant-id") String cognizantId, @RequestParam("form-email") String email,
			@RequestParam("phone") String phone, @RequestParam("password") String password) {
		UserModel userModel = new UserModel();
		userModel.setName(name);
		userModel.setEmail(email);
		userModel.setPhone(phone);
		userModel.setPassword(password);
		userModel.setCognizantId(cognizantId);
		try {
			userService.saveUser(userModel);
		} catch (DataIntegrityViolationException e) {
			return new ModelAndView("register-login-form", "registerErrorMessage", "Could not be registered!! Email Already exists");
		}catch (Exception e) {
			return new ModelAndView("register-login-form", "registerErrorMessage", "Could not be registered!! Some problem in Database");
		}
		return new ModelAndView("register-login-form");
	}

	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public @ResponseBody Object loginUser(@RequestParam(value = "form-email") String email,
			@RequestParam("form-password") String password, /*
															 * @RequestParam(value="g-recaptcha-response" , required =
															 * false) String reCaptcha,
															 */ HttpSession session, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		// System.out.println(reCaptcha);
		String loginResponse = null;
		if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
			loginResponse = "Email or password is empty";
		} else {
			UserModel userModel = userService.findByEmail(email);
			if (userModel != null && null != userModel.getPassword() && userModel.getPassword().equals(password)) {
				session.setAttribute("user-profile", userModel);
				userService.updateLastloginTime(getLastLoggedInTime(), userModel.getUserId());
				try {
					response.sendRedirect("landing-page");
					return null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
				// return new ModelAndView("landing-page",null);
			} else {
				loginResponse = "Invalid Credentials";
				return new ModelAndView("register-login-form", "errorMessage", loginResponse);
			}
		}
		return modelAndView;
	}

	@RequestMapping(value = "/socialLogin", method = RequestMethod.GET)
	public @ResponseBody Object socialLogin(@RequestParam String email, @RequestParam String name,
			@RequestParam String imageURL, @RequestParam String cognizantId, @RequestParam String phone,
			HttpSession session) {
		UserModel userModel = new UserModel();
		userModel.setCognizantId(cognizantId);
		userModel.setEmail(email);
		userModel.setName(name);
		userModel.setPhone(phone);
		userModel.setImageURL(imageURL);
		userService.saveUser(userModel);
		session.setAttribute("user-profile", userModel);
		userService.updateLastloginTime(getLastLoggedInTime(), userModel.getUserId());
		return "success";
	}

	@PutMapping("/saveUser")
	public @ResponseBody Object updateUser(@RequestBody UserModel userModel) {
		return userService.updateUser(userModel);
	}

	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable("id") long userId) {
		userService.deleteUser(userId);
	}

	@GetMapping("/user/email/{email:.+}")
	public UserModel findByEmail(@PathVariable("email") String email) {
		return userService.findByEmail(email);
	}

	@GetMapping("/user/userId/{userId}")
	public UserModel findByUserId(@PathVariable("userId") long userId) {
		return userService.findByUserId(userId);
	}

	@GetMapping("/user/cognizantId/{cognizantId}")
	public UserModel findByCognizantId(@PathVariable("cognizantId") String cognizantId) {
		return userService.findByCognizantId(cognizantId);
	}

	@GetMapping("/user/name/{name}")
	public UserModel findByName(@PathVariable("name") String name) {
		return userService.findByName(name);
	}

	@GetMapping("/users")
	public @ResponseBody Object findAll() {
		return userService.findAll();
	}

	@GetMapping("/getLoggedInUser")
	public @ResponseBody Object getLoggedInUser(HttpSession session) {
		UserModel userModel = (UserModel) session.getAttribute("user-profile");
		userModel.setPassword("**********");
		return userModel;
	}

	@GetMapping("/lastLoggedIn/{cognizantId}")
	public @ResponseBody Object getLastLoggedIn(HttpSession session) {
		LastLoginModel lastLoginModel = (LastLoginModel) session.getAttribute("last-loggedIn");
		return lastLoginModel;
	}

	private String getLastLoggedInTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd hh:mm a");
		String loginTime = dateFormat.format(new Date());
		return loginTime;
	}

	@GetMapping("/checkRegistration/{email}")
	public @ResponseBody Object checkRegistration(@PathVariable("email") String email, HttpSession session) {
		email = email + ".com";
		UserModel model = userService.findByEmail(email);
		if (model != null) {
			session.setAttribute("user-profile", model);
			userService.updateLastloginTime(getLastLoggedInTime(), model.getUserId());
			return "registered";
		} else {
			return "not-registered";
		}
	}
}
