package com.test.shiftapplication.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.shiftapplication.model.JiraModel;

@RunWith(SpringRunner.class)
public class JiraServiceTest {
	private static JiraService mockedJiraService;
	private static JiraModel jiraModel1;
	private static JiraModel jiraModel2;

	@BeforeClass
	public static void setUp() {
		mockedJiraService = mock(JiraService.class);
		jiraModel1 = new JiraModel(4L, "EDU-1099", "DIGITAL-529", "Reporter name", "assigneeName", new Date(),
				new Date(), new Date(), "Open",
				"issue summary issue summary issue summary issue summary issue summary ", "comments ", "8", "Sanu",
				"04 May Thu 19:20", "05/16/2017 6:22 PM", "Sanu");
		jiraModel2 = new JiraModel(6L, "EDU-1020", "DIGITAL-529", "Reporter name", "assigneeName", new Date(),
				new Date(), new Date(), "Open",
				"issue summary issue summary issue summary issue summary issue summary ", "comments ", "8", "Sanu",
				"04 May Thu 19:20", "05/16/2017 6:22 PM", "Sanu");

		when(mockedJiraService.findAll()).thenReturn(Arrays.asList(jiraModel1, jiraModel1));
		when(mockedJiraService.findByJiraNumber("EDU-1099")).thenReturn(jiraModel1);
		when(mockedJiraService.saveJira(jiraModel1)).thenReturn(jiraModel1);
		when(mockedJiraService.updateJira(jiraModel2)).thenReturn(jiraModel2);
	}

	@Test
	public void saveJiraWithDetails_andReturnJiraDetails() {
		JiraModel jiraModel = mockedJiraService.saveJira(jiraModel1);
		assertNotNull(jiraModel);
		assertEquals(jiraModel.getJiraNumber(), jiraModel1.getJiraNumber());
	}

	@Test
	public void updateJiraWithDetails_andReturnJiraDetails() {
		jiraModel2.setJiraNumber("EDU-1100");
		JiraModel jiraModel = mockedJiraService.updateJira(jiraModel2);
		assertNotNull(jiraModel);
		assertEquals(jiraModel2.getJiraNumber(), jiraModel.getJiraNumber());
	}

	@Test
	public void getJiraWhenJiraID_IsPassed() {

		String jiraNumber = "EDU-1099";

		JiraModel jiraModel = mockedJiraService.findByJiraNumber(jiraNumber);

		assertNotNull(jiraModel);
		assertEquals(jiraModel1.getApplicationName(), jiraModel.getApplicationName());
		assertEquals("comments ", jiraModel.getComments());
		assertEquals(jiraModel1.getTimeSpent(), jiraModel.getTimeSpent());
	}

	@Test
	public void getAllJiras_andVerify() throws Exception {

		List<JiraModel> allJiras = (List<JiraModel>) mockedJiraService.findAll();
		assertEquals(2, allJiras.size());
		JiraModel jiraModel = allJiras.get(0);
		assertEquals(4L, jiraModel.getJiraId());
		assertEquals("comments ", jiraModel.getComments());
		assertEquals("Reporter name", jiraModel.getReporterName());
		assertEquals("8", jiraModel.getTimeSpent());
	}
}