package com.endava.aminternship;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/admin/delete-user/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") int id) {
		
		userService.removeUser(id);
		return "redirect:/view-users";
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
	public String editUserAction(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Map<String, Object> map,@PathVariable("id") int id) {
		if(bindingResult.hasErrors()){
			System.out.println(bindingResult.getFieldError("email").getDefaultMessage().equals("Such email already exists!"));
			System.out.println(id == user.getId());
			if(bindingResult.getFieldError("email").getDefaultMessage().equals("Such email already exists!")){
				String insertedEmail = (String) bindingResult.getFieldValue("email");
				User dbUser = userService.findUserByEmail(insertedEmail);
				if(dbUser != null && dbUser.getId() != user.getId()){
					return "/edit-user";
				}
			} else {
				System.out.println("vrr");
			}
		}
		if(userService.updateUser(user) == true){
			return "redirect:/view-users";
		} else {
			map.put("errorMessage", "User not found in the db");
			return "/exception";
		}
	}
}
