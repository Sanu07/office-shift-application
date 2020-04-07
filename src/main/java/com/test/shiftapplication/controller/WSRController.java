package com.test.shiftapplication.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.shiftapplication.model.WSRModel;
import com.test.shiftapplication.service.WSRService;

@RestController
public class WSRController {
	@Autowired
	WSRService wsrService;

	@GetMapping("/wsrDetails")
	public @ResponseBody Object getAllWSRDetails() {
		return wsrService.getAllWSRDetails();
	}

	@GetMapping("/wsrDetails/{wsrDate}/check")
	public @ResponseBody Object getWSRByDates(@PathVariable("wsrDate") String wsrDate) {
		return wsrService.getWSRByDate(wsrDate);
	}

	@PostMapping("/wsrDetails")
	public @ResponseBody Object saveWSRDetails(@RequestBody WSRModel wsrModel) {
		Date curDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		wsrModel.setWSRDate(dateFormat.format(curDate));
		return wsrService.saveWSRDEtails(wsrModel);
	}

	@GetMapping("/wsrDetails/afterDate/{afterDate}/{cognizantId}")
	public List<WSRModel> getWSRListAfterDate(@PathVariable("afterDate") String afterDate,
			@PathVariable("cognizantId") String cognizantId) {
		List<WSRModel> listOfWSR = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date receivedDate = null;
		try {
			receivedDate = dateFormat.parse(afterDate);
			listOfWSR = getListOfWSR(receivedDate, cognizantId);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return listOfWSR;
	}

	private List<WSRModel> getListOfWSR(Date receivedDate, String cognizantId) {
		List<WSRModel> listOfWSR = (List<WSRModel>) wsrService.findAll();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date getDate = null;
		List<WSRModel> getListOfWSRResponse = new ArrayList<>();
		for (int i = 0; i < listOfWSR.size(); i++) {
			try {
				getDate = dateFormat.parse(listOfWSR.get(i).getWSRDate());
				if (receivedDate.before(getDate) && cognizantId.equalsIgnoreCase(listOfWSR.get(i).getCognizantId())) {
					getListOfWSRResponse.add(listOfWSR.get(i));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return getListOfWSRResponse;
	}
}
