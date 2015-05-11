package com.endava.aminternship.testing.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;




import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.UserService;
import com.endava.aminternship.testing.configuration.Registry;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserServiceClass {

	final static ApplicationContext appContext = Registry
			.getContext("test-context.xml");
	// services
	static UserService userService;

	// variables
	static User testUser;

	@Before
	public void contextIntialization() {
		userService = (UserService) appContext.getBean("userService");

		// generating a dummy user to work on
		testUser = new User();

		// used to generate user data, avoids db conflicts
		Random r = new Random();
		int randomNumber = r.nextInt(99999 + 1);

		testUser.setEmail("testTweet" + randomNumber + "@mail.com");
		testUser.setFirstname("TestTweet");
		testUser.setLastname("InsertNewUser");

		userService.addUser(testUser);
		// end user generation

	}

	@AfterClass
	static public void contextCleaning() {
		assertNotNull(testUser);
		userService.removeUser(testUser.getId());
	}

	@Test
	@Transactional
	public void shouldAddUser() {

		User user = new User();
		Random r = new Random();
		int randomNumber = r.nextInt(99999 + 1);

		testUser.setEmail("testTweet" + randomNumber + "@mail.com");
		user.setEmail("test" + randomNumber + "@mail.com");
		user.setFirstname("Test");
		user.setLastname("InsertnewUser");

		userService.addUser(user);

	}

	@Test
	public void shouldFindUserById() {

		User user = userService.findUserById(testUser.getId());

		assertTrue(user != null);

	}

	@Test
	public void shouldFindUserByEmail() {

		User user = userService.findUserByEmail(testUser.getEmail());

		assertTrue(user != null);

	}

	@Test
	@Transactional
	public void shouldUpdateUser() {

		User user = userService.findUserById(testUser.getId());
		System.out.println(user);
		String newLastName = user.getLastname() + "EDIT";
		String newFirstName = user.getFirstname() + "EDIT";

		user.setLastname(newLastName);
		user.setFirstname(newFirstName);
		System.out.println(userService.updateUser(user));

		// retrieving new name from database
		user = userService.findUserById(testUser.getId());
		System.out.println(user);
		assertEquals(user.getLastname(), newLastName);
	}

	
	@Test
	@Transactional
	public void shouldDeleteUser() {

		User user = userService.findUserById(testUser.getId());

		userService.removeUser(testUser.getId());
		
		assertNotNull(user);

	}

}
