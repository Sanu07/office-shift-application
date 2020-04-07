package com.test.shiftapplication.dao;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.shiftapplication.model.JiraModel;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JiraDaoTest {

	private final String CURRENT_STATUS = "Open";

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private DefaultJiraDao jiraDao;

	JiraModel testJira = new JiraModel("EDU-1020", "DIGITAL-529", "Reporter name", "assigneeName", new Date(),
			new Date(), new Date(), "Open", "issue summary issue summary issue summary issue summary issue summary ",
			"comments ", "8", "Sanu", "04 May Thu 19:20", "05/16/2017 6:22 PM", "Sanu");

	@Test
	public void whenFindByName_thenReturnJiraWithGivenName() {
		entityManager.persistAndFlush(testJira);
		JiraModel jira = jiraDao.findByJiraNumber(testJira.getJiraNumber());
		assertEquals(jira.getJiraNumber(), testJira.getJiraNumber());
	}

	@Test
	public void givenJiraInDBWhenFindOneByStatus_ThenReturnJiraList() throws Exception {
		jiraDao.save(testJira);

		List<JiraModel> jiraByStatus = jiraDao.findByCurrentStatus(CURRENT_STATUS);

		assertThat(jiraByStatus.size(), is(1));
	}

	@After
	public void cleanUp() {
		jiraDao.deleteAll();
	}
}
