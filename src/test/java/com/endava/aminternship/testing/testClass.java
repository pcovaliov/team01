package com.endava.aminternship.testing;

import static org.junit.Assert.*;

import java.util.Random;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.endava.aminternship.entity.Tweet;
import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.TwitterService;
import com.endava.aminternship.service.interfaces.UserService;

public class testClass {

	final ApplicationContext appContext = new ClassPathXmlApplicationContext(
			"file:src/main/webapp/WEB-INF/spring/root-context.xml");

	@Test
	public void shouldFindUserById() {

		UserService service = (UserService) appContext.getBean("userService");
		User user = service.findUserById(2);
		assertTrue(user != null);
		assertTrue(user.getId() == 2);
	}

	@Test
	public void shouldFindUserByEmail() {

		UserService service = (UserService) appContext.getBean("userService");
		User user = service.findUserByEmail("ion@ion");
		assertTrue(user != null);
		// assertTrue(user.getId() == 2);
	}

	@Ignore
	@Test
	@Transactional
	public void shouldInsertUser() {
		UserService service = (UserService) appContext.getBean("userService");
		User user1 = new User();
		int min = 0;
		int max = 100;

		Random r = new Random();
		int i1 = r.nextInt(max - min + 1) + min;

		user1.setEmail("test" + i1 + "@mail.com");
		user1.setFirstname("Test");
		user1.setLastname("InsertnewUser");

		service.addUser(user1);
		assertTrue(user1 != null);

	}

	@Test
	@Transactional
	public void shouldUpdateUser() {

		UserService service = (UserService) appContext.getBean("userService");
		User user = service.findUserById(2);

		String newLastName = user.getLastname() + "EDIT";
		String newFirstName = user.getFirstname() + "Edit";

		user.setLastname(newLastName);
		user.setFirstname(newFirstName);
		service.updateUser(user);

		// retrieving new name from database
		user = service.findUserById(2);
		assertEquals(user.getLastname(), newLastName);
	}

	@Test
	public void shouldAddTweet() {
		UserService service = (UserService) appContext.getBean("userService");
		User user = service.findUserById(2);

		TwitterService twitterservice = (TwitterService) appContext
				.getBean("tweetService");
		Tweet tweet = new Tweet();
		tweet.setUser(user);
		tweet.setTweet("TEST Tweet");
		twitterservice.addTweet(tweet);
		assertTrue(tweet.getId() != null);
		

	}

}
