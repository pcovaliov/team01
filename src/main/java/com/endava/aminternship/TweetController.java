package com.endava.aminternship;


import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;






import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.TwitterService;


@Controller
public class TweetController {
	private static final Logger logger = LoggerFactory
			.getLogger(TweetController.class);
	
	@Autowired
	private TwitterService tweeterService;

	@RequestMapping(value = "/tweet-page", method = RequestMethod.GET)
	public String registerUserForm(Map<String, Object> map) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection tweetList = tweeterService.getTweetsForUser(user);
		
		map.put("tweetList", tweetList);
		return "/tweet-page";
	}

	
	

}
