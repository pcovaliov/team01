package com.endava.aminternship.testing.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserServiceClass {
	final ApplicationContext appContext = Registry.getContext("test-context.xml");
	
	static List <User> userForTest = new ArrayList<User>() ;
	
	@Test
	@Transactional
	public void ashouldAddUser() {
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
		userForTest.add(user1);
		

	}
	
	@Test
	public void bshouldFindUserById() {

		UserService service = (UserService) appContext.getBean("userService");
		User user = service.findUserById(userForTest.get(0).getId());
		
		assertTrue(user != null);
		
	}

	@Test
	public void cshouldFindUserByEmail() {

		UserService service = (UserService) appContext.getBean("userService");
		User user = service.findUserByEmail(userForTest.get(0).getEmail());
		
		assertTrue(user != null);
		
	}

	

	@Test
	@Transactional
	public void dshouldUpdateUser() {

		UserService service = (UserService) appContext.getBean("userService");
		User user = service.findUserById(userForTest.get(0).getId());
		System.out.println(user);
		String newLastName = user.getLastname() + "EDIT";
		String newFirstName = user.getFirstname() + "EDIT";
		
		user.setLastname(newLastName);
		user.setFirstname(newFirstName);
		System.out.println(service.updateUser(user));

		// retrieving new name from database
		user = service.findUserById(userForTest.get(0).getId());
		System.out.println(user);
		assertEquals(user.getLastname(), newLastName);
	}
	
	
	@Test
	@Transactional
	public void eshouldDeleteUser() {

		UserService service = (UserService) appContext.getBean("userService");
		User user = service.findUserById(userForTest.get(0).getId());
		
		service.removeUser(userForTest.get(0).getId());
		
		assertNotNull(user);
		
	}
	
}
