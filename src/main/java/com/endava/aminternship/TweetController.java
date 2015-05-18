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
			Map<String, Object> map, 
			@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit
		) {
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<Tweet> tweetList = twiterService.getTweetsForUser(user,limit,offset);
		map.put("tweetObject",new Tweet());            
		map.put("tweetList", tweetList);
		
		if(offset < tweetList.size()){
		String nextTweetsLink = ServletUriComponentsBuilder.fromCurrentContextPath().path("/tweet-page?offset="+(offset+limit)).build().toUriString();
		map.put("nextTweetsLink", nextTweetsLink);
		}
		
		if(offset >= 10){
			String prevTweetsLink = ServletUriComponentsBuilder.fromCurrentContextPath().path("/tweet-page?offset="+(offset-limit)).build().toUriString();
			map.put("prevTweetsLink", prevTweetsLink);
		}
			   
		    
		return "/tweet-page";
	}
	
	@RequestMapping(value = "/tweet-page" , method = RequestMethod.POST)
	public String addTweet(@Valid @ModelAttribute("tweetObject") Tweet insertedTweet, BindingResult bindingResult,Map<String, Object> map) {
		if(bindingResult.hasErrors()){
			System.out.println("error found");
			return "/tweet-page";	
		}
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(insertedTweet);
		insertedTweet.setUser(currentUser);
		twiterService.addTweet(insertedTweet);
		
		return "redirect:/tweet-page";
	}
	
	@RequestMapping(value = "/tweet-page/{id}", method = RequestMethod.GET)
	public String externalTweetsPage(
			Map<String, Object> map, 
			@PathVariable("id") int id,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
			@RequestParam(value = "limit", required = false, defaultValue = "25") int limit
		) {
		
		User user =userService.findUserById(id);
		if(user == null){ //the id is not present in the db
			return "exception";
		}
		
		User currentLoggedInUser;
		try {
			currentLoggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			currentLoggedInUser = null; //annonimous
		}
		
		map.put("currentLoggedInUser", currentLoggedInUser);
		map.put("isFollowing", userService.isFollowing(user,currentLoggedInUser));
		
		Collection<Tweet> tweetList = twiterService.getTweetsForUser(user,limit,offset);
		map.put("tweetList", tweetList);
		map.put("currentUser", user);
		
		if(offset < tweetList.size()){
			String nextTweetsLink = ServletUriComponentsBuilder.fromCurrentContextPath().path("/tweet-page/"+user.getId()+"?offset="+(offset+limit)).build().toUriString();
			map.put("nextTweetsLink", nextTweetsLink);
		}
		
		if(offset >= 10){
			String prevTweetsLink = ServletUriComponentsBuilder.fromCurrentContextPath().path("/tweet-page/"+user.getId()+"?offset="+(offset-limit)).build().toUriString();
			map.put("prevTweetsLink", prevTweetsLink);
		}
		    
		return "/external-tweet-page";
	}
	
	
	@RequestMapping(value = "/news-feed", method = RequestMethod.GET)
	public String newsFeedPage(
			Map<String, Object> map, 
			@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit
		) {
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//Set<User> usersFollowed = user.getFollowing();
		//System.out.println(usersFollowed.size());
		//Collection<Tweet> tweetList = twiterService.getTweetsForUser(user,limit,offset);
		//map.put("tweetList", tweetList);
		    
		return "/news-feed";
	}
}
