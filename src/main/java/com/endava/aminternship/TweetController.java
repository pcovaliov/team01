package com.endava.aminternship;


import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;






import org.springframework.web.bind.annotation.ResponseBody;

import com.endava.aminternship.entity.Tweet;
import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.TwitterService;
import com.endava.aminternship.service.interfaces.UserService;


@Controller
public class TweetController {
	private static final Logger logger = LoggerFactory
			.getLogger(TweetController.class);
	
	@Autowired
	private TwitterService twiterService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/tweet-page", method = RequestMethod.GET)
	public String registerUserForm(Map<String, Object> map) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection tweetList = twiterService.getTweetsForUser(user);
		map.put("tweetObject",new Tweet());            
		map.put("tweetList", tweetList);     
		return "/tweet-page";
	}
	
	@RequestMapping(value = "/tweet-page", headers = "Accept=application/json; charset=UTF-8" , method = RequestMethod.POST)
	public @ResponseBody Tweet addTweet(@RequestBody Tweet insertedTweet) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		currentUser = userService.findUserById(currentUser.getId());
		System.out.println(currentUser);
		insertedTweet.setUser(currentUser);
		twiterService.addTweet(insertedTweet);
		return insertedTweet;
	}
}
