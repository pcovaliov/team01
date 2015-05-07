package com.endava.aminternship.testing.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.endava.aminternship.entity.Tweet;
import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.TwitterService;
import com.endava.aminternship.service.interfaces.UserService;
import com.endava.aminternship.testing.configuration.Registry;

public class TestUserServiceClass {
	final ApplicationContext appContext = Registry.getContext("test-context.xml");
	
	@Ignore
	@Test
	public void shouldFindUserById() {

		UserService service = (UserService) appContext.getBean("userService");
		User user = service.findUserById(2);
		assertTrue(user != null);
		assertTrue(user.getId() == 2);
	}
	
	@Ignore
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

	@Ignore
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

}
