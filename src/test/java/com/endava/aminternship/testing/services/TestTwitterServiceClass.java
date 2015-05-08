package com.endava.aminternship.testing.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.endava.aminternship.entity.Tweet;
import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.TwitterService;
import com.endava.aminternship.service.interfaces.UserService;
import com.endava.aminternship.testing.configuration.Registry;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTwitterServiceClass {
	final ApplicationContext appContext = Registry
			.getContext("test-context.xml");

	static List<User> userForTweetTest = new ArrayList<User>();
	static List<Tweet> tweetForTest = new ArrayList<Tweet>();
	static int sizeTweetList;

	@Before
	public void ashouldAddUser() {
		UserService service = (UserService) appContext.getBean("userService");
		User user1 = new User();
		int min = 0;
		int max = 100;

		Random r = new Random();
		int i1 = r.nextInt(max - min + 1) + min;

		user1.setEmail("testTweet" + i1 + "@mail.com");
		user1.setFirstname("TestTweet");
		user1.setLastname("InsertnewUser");

		service.addUser(user1);
		userForTweetTest.add(user1);

	}

	@Test
	public void shouldAddTweet() {
		UserService service = (UserService) appContext.getBean("userService");
		User user = service.findUserById(userForTweetTest.get(0).getId());

		TwitterService twitterservice = (TwitterService) appContext
				.getBean("twitterService");
		Tweet tweet = new Tweet();

		tweet.setUser(user);
		for (int i = 0; i < 10; i++) {
			tweet.setTweet("TEST Tweet " + i);
			twitterservice.addTweet(tweet);
			tweetForTest.add(tweet);
			// for checking number of tweets inserted
			sizeTweetList++;
		}

		assertTrue(tweet.getId() != null);

	}

	@Test
	public void shouldGetTweetsForUser() {
		UserService service = (UserService) appContext.getBean("userService");
		User user = service.findUserById(userForTweetTest.get(0).getId());

		TwitterService twitterservice = (TwitterService) appContext
				.getBean("twitterService");

		List<Tweet> listOfTweet = (List<Tweet>) twitterservice
				.getTweetsForUser(user, 10, 0);

		assertTrue(listOfTweet.size() == sizeTweetList);

	}

}
