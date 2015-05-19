package com.endava.aminternship;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.endava.aminternship.entity.SecurityUser;
import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.ImageProcessorService;
import com.endava.aminternship.service.interfaces.UserService;

import org.apache.log4j.Logger;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

@Controller
public class UserController {

	final static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private ImageProcessorService imageProcessor;

	@RequestMapping(value = "/register-user", method = RequestMethod.GET)
	public String registerUserForm(Map<String, Object> map) {
		map.put("user", new User());
		return "/register-user";
	}

	@RequestMapping(value = "/register-user", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") User user,BindingResult result,
			@RequestParam MultipartFile image,
		 Map<String, Object> map) {

		if (result.hasErrors()) {
			logger.debug("error at add user " +user);
			return "/register-user";
		}
		System.out.println("getting image");
		if (!image.isEmpty()) {
			if(imageProcessor.isValidImage(image)){
				String uploadedFilePath = imageProcessor.uploadImage(image);
				user.setImageUrl(uploadedFilePath);
			} else {
				System.out.println("not valid image");
			}
		} 
		
		logger.info("user was inserted " + user);
		userService.addUser(user);
		SecurityUser secUser = new SecurityUser(user);
		Authentication auth = new UsernamePasswordAuthenticationToken(secUser,
				null, secUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		return "redirect:/tweet-page";
	}
	
	@RequestMapping("/view-users")
	public String viewUsers(Map<String, Object> map) {
		map.put("usersList", userService.listUser());
		return "/view-users";
	}
	
	@RequestMapping(value = "/follow/{id}", method = RequestMethod.GET) //todo: change to post
	public @ResponseBody String followUser(
			@PathVariable("id") int id,
			Map<String, Object> map
			) {
		String response = "SUCCESS";
		boolean failFlag = false;
		
		User currentLoggedInUser;
		try {
			SecurityUser secUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			currentLoggedInUser = secUser.getUserObject();
		} catch (Exception e) {
			currentLoggedInUser = null; //annonimous
			response ="NOT LOGGED IN";
			failFlag = true;
		}
		
		if(!failFlag){
			User user = userService.findUserById(id);
			if(user == null){ //the id is not present in the db
				response = "User not found";
				return response;
			} else {
				if(userService.isFollowing(user,currentLoggedInUser)){
					user.removeFollower(currentLoggedInUser);
					response = "already following, following removed";
				} else {
					user.addFollower(currentLoggedInUser);
					response = "was not following, now following";
				}
				userService.updateUser(user);
			}
		}
		
		return response;
	}
}
