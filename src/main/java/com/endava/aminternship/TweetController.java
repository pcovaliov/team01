package com.endava.aminternship;


import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.endava.aminternship.entity.FollowRelationship;
import com.endava.aminternship.entity.SecurityUser;
import com.endava.aminternship.entity.Tweet;
import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.TwitterService;
import com.endava.aminternship.service.interfaces.UserService;


@Controller
public class TweetController {
	@Autowired
	private TwitterService twiterService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/tweet-page", method = RequestMethod.GET)
	public String registerUserForm(
			Map<String, Object> map
		) {
		
		User user = null;
		try {
			SecurityUser secUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			user = secUser.getUserObject();
		} catch (Exception e) { //ClassCastException String to User/SecurityUser -> invalid session
			return "redirect:/login";
		}
		
		Collection<Tweet> tweetList = twiterService.getAllTweetsForUser(user);
		map.put("tweetObject",new Tweet());            
		map.put("tweetList", tweetList);
		    
		return "/tweet-page";
	}
	
	@RequestMapping(value = "/tweet-page" , method = RequestMethod.POST)
	public String addTweet(@Valid @ModelAttribute("tweetObject") Tweet insertedTweet, BindingResult bindingResult,Map<String, Object> map) {
		if(bindingResult.hasErrors()){
			return "/tweet-page";	
		}
		User currentUser = null;
		try {
			SecurityUser secUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			currentUser = secUser.getUserObject();
		} catch (Exception e) { //ClassCastException String to User/SecurityUser -> invalid session
			return "redirect:/login";
		}
		
		insertedTweet.setUser(currentUser);
		twiterService.addTweet(insertedTweet);
		
		return "redirect:/tweet-page";
	}
	
	@RequestMapping(value = "/tweet-page/{id}", method = RequestMethod.GET)
	public String externalTweetsPage(
			Map<String, Object> map, 
			@PathVariable("id") int id
		) {
		
		User user = userService.findUserById(id);
		if(user == null){ //the id is not present in the db
			return "exception";
		}
		
		User currentLoggedInUser;
		try {
			SecurityUser secUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			currentLoggedInUser = secUser.getUserObject();
		} catch (Exception e) {
			currentLoggedInUser = null; //annonimous
		}
		
		map.put("currentLoggedInUser", currentLoggedInUser);
		FollowRelationship fr = userService.isFollowing(user,currentLoggedInUser);
		map.put("isFollowing", (fr != null ? true : false));
		
		Collection<Tweet> tweetList = twiterService.getAllTweetsForUser(user);
		map.put("tweetList", tweetList);
		map.put("currentUser", user);
		    
		return "/external-tweet-page";
	}
	
	
	@RequestMapping(value = "/news-feed", method = RequestMethod.GET)
	public String newsFeedPage(
			Map<String, Object> map
		) {
		
		User user = null;
		try {
			SecurityUser secUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			user = secUser.getUserObject();
		} catch (Exception e) { //ClassCastException String to User/SecurityUser -> invalid session
			return "redirect:/login";
		}
		
		
		Collection<Tweet> tweetList = twiterService.getNewsFeedForUser(user);
		map.put("tweetList", tweetList);
		    
		return "/news-feed";
	}
}
