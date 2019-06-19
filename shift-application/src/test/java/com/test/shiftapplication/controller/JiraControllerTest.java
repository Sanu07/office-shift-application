package com.test.shiftapplication.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.test.shiftapplication.ShiftApplication;
import com.test.shiftapplication.model.JiraModel;
import com.test.shiftapplication.service.JiraService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShiftApplication.class)
public class JiraControllerTest {

	private static JiraModel jiraModel1;
	private static JiraModel jiraModel2;

	@Autowired
	private WebApplicationContext webAppContext;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	@MockBean
	private JiraService jiraService;

	@Test
	public void findAll_ShouldAddJiraEntriesToModelAndRenderJiraListView() throws Exception {
		jiraModel1 = new JiraModel(4L, "EDU-1099", "DIGITAL-529", "Reporter name", "assigneeName", new Date(),
				new Date(), new Date(), "Open",
				"issue summary issue summary issue summary issue summary issue summary ", "comments ", "8", "Sanu",
				"04 May Thu 19:20", "05/16/2017 6:22 PM", "Sanu");
		jiraModel2 = new JiraModel(6L, "EDU-1020", "DIGITAL-529", "Reporter name", "assigneeName", new Date(),
				new Date(), new Date(), "Open",
				"issue summary issue summary issue summary issue summary issue summary ", "comments ", "8", "Sanu",
				"04 May Thu 19:20", "05/16/2017 6:22 PM", "Sanu");
		when(jiraService.findAll()).thenReturn(Arrays.asList(jiraModel1, jiraModel2));

		mockMvc.perform(get("/jiras")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].jiraId", is(4))).andExpect(jsonPath("$[0].jiraNumber", is("EDU-1099")))
				.andExpect(jsonPath("$[0].createdBy", is("Sanu")));

		verify(jiraService, times(1)).findAll();
		verifyNoMoreInteractions(jiraService);
	}
}