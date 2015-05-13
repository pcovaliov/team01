package com.endava.aminternship;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.UserService;

import org.apache.log4j.Logger;

@Controller
public class UserController {

	final static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register-user", method = RequestMethod.GET)
	public String registerUserForm(Map<String, Object> map) {
		map.put("user", new User());
		return "/register-user";
	}

	@RequestMapping(value = "/register-user", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") User user,
			BindingResult result, Map<String, Object> map) {

		if (result.hasErrors()) {
			logger.debug("error at add user " +user);
			return "/register-user";
		}
		logger.info("user was inserted " + user);
		userService.addUser(user);

		Authentication auth = new UsernamePasswordAuthenticationToken(user,
				null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		return "redirect:/tweet-page";
	}
	
	@RequestMapping(value = "/follow/{id}", method = RequestMethod.GET)
	public @ResponseBody String followUser(
			@PathVariable("id") int id,
			Map<String, Object> map
			) {
		String response = "SUCCESS";
		boolean failFlag = false;
		
		User currentLoggedInUser;
		try {
			currentLoggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			currentLoggedInUser = null; //annonimous
			response ="NOT LOGGED IN";
			failFlag = true;
		}
		
		if(!failFlag){
			User user =userService.findUserById(id);
			if(user == null){ //the id is not present in the db
				response = "User not found";
				return response;
			} else {
				if(userService.isFollowing(user,currentLoggedInUser)){
					response = "already following";
				} else {
					response = "not following";
				}
//				user.addFollower(currentLoggedInUser);
//				userService.updateUser(user);
			}
		}
		
		return response;
	}
}
