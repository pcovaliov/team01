package com.endava.aminternship;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	private UserService userService;

//	@RequestMapping(value="/getUsers", method = RequestMethod.POST ,headers="")
//	public @ResponseBody List<?> getUsers() {
//		System.out.println("------------------ " + logger.getClass() + " ------------------");
//
//		return userService.getUsersList();[{"name":"sfsdfs","email":"dfsdfsd2sasd"},{"name":"sfsdfs","email":"dfsdfsd2sasd"},{"name":"sfsdfs","email":"dfsdfsd2sasd"}];
//		
//	}
//	@RequestMapping("/getUsersbyName")
//	public @ResponseBody User getUsers(Map<String, Object> map) {
//		System.out.println("------------------ " + logger.getClass() + " ------------------");
//
//		map.put("user", new User());
//		return "/register-user";
//	}
	
	@RequestMapping(value = "/register-user", method = RequestMethod.GET)
	public String registerUserForm(Map<String, Object> map) {
		map.put("user", new User());
		return "/register-user";
	}

	@RequestMapping(value = "/register-user", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user,
			BindingResult result,
			Map<String, Object> map ) {
		System.out.println(user);
		userService.addUser(user);

		return "redirect:/view-users";

	}
	
	@RequestMapping("/admin/view-users")
	public String viewUsers(Map<String, Object> map) {
		System.out.println("------------------ " + logger.getClass() + " ------------------");
		map.put("usersList", userService.listUser());
		return "/view-users";
	}
	
	
	
	@RequestMapping(value="/admin/delete-user/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") int id) {
		userService.removeUser(id);
		
		return "redirect:/admin/view-users";
	}
	
	
	@RequestMapping(value="/admin/edit-user/{id}", method = RequestMethod.GET)
	public String editUser(Map<String, Object> map ,@PathVariable("id") int id) {
		User u = userService.findUserById(id);
		if(u != null){
			System.out.println(u);
			map.put("user", u);
			return "/edit-user";
		} else {
			map.put("errorMessage", "User not found in the db");
			return "/exception";
		}
	}
	
	@RequestMapping(value="/admin/edit-user/{id}", method = RequestMethod.POST)
	public String editUserAction(@ModelAttribute("user") User user, BindingResult bindingResult, Map<String, Object> map) {
		
		if(userService.updateUser(user) == true){
			return "redirect:/admin/view-users";
		} else {
			map.put("errorMessage", "User not found in the db");
			return "/exception";
		}
	}
	

}
