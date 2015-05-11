package com.endava.aminternship.testing.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;

import com.endava.aminternship.entity.Tweet;
import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.TwitterService;
import com.endava.aminternship.service.interfaces.UserService;
import com.endava.aminternship.testing.configuration.Registry;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTwitterServiceClass {
	
	final static ApplicationContext appContext = Registry.getContext("test-context.xml");
	//services
	static UserService userService;
	static TwitterService twitterService;
	
	//variables
	static User testUser;
	static Tweet testTweet;
	
	@BeforeClass
	static public void contextIntialization() {
		userService = (UserService) appContext.getBean("userService");
		twitterService = (TwitterService) appContext.getBean("twitterService");
		
		//generating a dummy user to work on
		testUser = new User();
		
		//used to generate user data, avoids db conflicts
		Random r = new Random();
		int randomNumber = r.nextInt(99999 + 1); 
		
		testUser.setEmail("testTweet" + randomNumber + "@mail.com");
		testUser.setFirstname("TestTweet");
		testUser.setLastname("InsertNewUser");
		
		userService.addUser(testUser);
		//end user generation
		
		//generating a tweet and linking it with the user
		testTweet = new Tweet();
		testTweet.setUser(testUser);
		
	}
	 @AfterClass
	 static public void contextCleaning() {
		 assertNotNull(testUser);
		 userService.removeUser(testUser.getId());
	 }
	 
	 //todo: refactor
	// static List<User> userForTweetTest = new ArrayList<User>();
	// static List<Tweet> tweetForTest = new ArrayList<Tweet>();
	
	
	@Test
	public void shouldAddTweet() {
		testTweet.setId(null); //to make sure it's sent as null to insert
		testTweet.setTweet("TEST Tweet ");
		twitterService.addTweet(testTweet);
		assertNotNull(testTweet.getId());
	}
	

	@Test
	public void shouldGetTweetsForUser() {
		//creating the tweets for the user
		int insertedTweets = 0;
		
		for (int i = 0; i < 10; i++) {
			testTweet.setId(null); //to make sure it's sent as null to insert
			testTweet.setTweet("TEST Tweet " + i);
			twitterService.addTweet(testTweet);
			assertNotNull(testTweet.getId()); //just to check it not fails ...
			// todo: comment later
			insertedTweets++;
		}
		
		
		List<Tweet> listOfTweets = (List<Tweet>) twitterService.getTweetsForUser(testUser, insertedTweets, 0);
		assertTrue(listOfTweets.size() == insertedTweets);
	}
}
