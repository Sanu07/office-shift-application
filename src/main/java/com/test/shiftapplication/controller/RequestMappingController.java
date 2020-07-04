package com.test.shiftapplication.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.shiftapplication.model.MonthlyShiftModel;
import com.test.shiftapplication.model.UserModel;
import com.test.shiftapplication.model.WSRModel;
import com.test.shiftapplication.service.ShiftService;
import com.test.shiftapplication.service.UserService;
import com.test.shiftapplication.service.WSRService;
import com.test.shiftapplication.util.GenerateExcel;
import com.test.shiftapplication.util.GeneratePPTForWSR;

@Controller
public class RequestMappingController {
	@Autowired
	ShiftService shiftService;

	@Autowired
	WSRService wsrService;

	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String getRegisterLoginForm() {
		return "register-login-form";
	}

	@RequestMapping(value = "/socialLogintest", method = RequestMethod.POST)
	public String landToWelcomePage() {
		System.out.println("working");
		return "success";
	}

	@RequestMapping("/success")
	public String getSuccessPage() {
		return "success";
	}

	@RequestMapping("/errorMessage")
	public String errorPage() {
		return "error-page";
	}

	@GetMapping("/shift-plan-generator")
	public String getShiftPlanGeneratorPage() {
		return "shift-plan-generator";
	}

	@GetMapping("/landing-page")
	public String getLandingPage() {
		return "landing-page";
	}

	@GetMapping("/create-jiras")
	public String getCreateJirasPage() {
		return "create-jiras";
	}

	@GetMapping("/create-incidents")
	public String getCreateIncidentsPage() {
		return "create-incidents";
	}

	@GetMapping("/create-leaves")
	public String getCreateILeavesPage() {
		return "create-leaves";
	}

	@GetMapping("/list-all-jiras")
	public String getAllJirasPage() {
		return "list-all-jiras";
	}

	@GetMapping("/list-all-shifts")
	public String getAllShiftsPage() {
		return "list-all-shifts";
	}

	@GetMapping("/list-all-incidents")
	public String getAllIncidentsPage() {
		return "list-all-incidents";
	}

	@GetMapping("/list-all-leaves")
	public String getAllLeavesPage() {
		return "list-all-leaves";
	}

	@GetMapping("/list-my-leaves")
	public String getMyLeavesPage() {
		return "list-my-leaves";
	}

	@GetMapping("/profile")
	public String getProfile() {
		return "profile";
	}

	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		UserModel userModel = (UserModel) session.getAttribute("user-profile");
		if (userModel != null) {
			session.invalidate();
		}
		return "register-login-form";
	}

	@GetMapping("/create-wsr")
	public String getCreateWSRPage() {
		return "create-wsr";
	}

	@GetMapping("/list-wsr")
	public String getListWSRPage() {
		return "list-wsr";
	}

	@GetMapping(value = "/shifts/generateExcel/{month}/{year}")
	public void generateExcel(@PathVariable("month") String month, @PathVariable("year") String year,
			HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=MyExcel.xls");
		response.reset();
		List<MonthlyShiftModel> list = (List<MonthlyShiftModel>) shiftService.getAllShiftsByMonth(month, year);
		Workbook workbook = new GenerateExcel().convertToJSON(list);
		OutputStream responseOutputStream = response.getOutputStream();
		workbook.write(responseOutputStream);
		responseOutputStream.close();
	}

	@GetMapping(value = "/wsrDetails/{wsrDate}")
	public void generatePPT(@PathVariable("wsrDate") String date, HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.ms-powerpoint");
		response.setHeader("Content-Disposition", "attachment; filename=MyFile.ppt");
		List<WSRModel> list = (List<WSRModel>) wsrService.getWSRByDate(date);
		Map<String, List<String>> map = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getUserName(), list.get(i).getListOfJiras());
		}
		XMLSlideShow wsrPPT = new GeneratePPTForWSR().generatePPt(map);
		OutputStream responseOutputStream = response.getOutputStream();
		wsrPPT.write(responseOutputStream);
		responseOutputStream.close();
	}
	
	@GetMapping("test")
	public @ResponseBody Object test(){
		return "Test Works fine!!!";
	}
}
