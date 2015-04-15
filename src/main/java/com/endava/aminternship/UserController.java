package com.endava.aminternship;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/register-user")
	public String registerUser(Map<String, Object> map) {
		System.out.println("------------------ " + logger.getClass() + " ------------------");

		map.put("user", new User());
		return "/register-user";
	}

	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user,
			BindingResult result) {

		userService.addUser(user);

		return "redirect:/register-user";

	}
	
	@RequestMapping("/view-users")
	public String viewUsers(Map<String, Object> map) {
		System.out.println("------------------ " + logger.getClass() + " ------------------");

		map.put("user", new User());
		return "/view-users";
	}
}
