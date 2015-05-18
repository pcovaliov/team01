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
	public String editUserAction(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Map<String, Object> map) {
		if(bindingResult.hasErrors()){
			return "/edit-user";			
		}
		if(userService.updateUser(user) == true){
			return "redirect:/admin/view-users";
		} else {
			map.put("errorMessage", "User not found in the db");
			return "/exception";
		}
	}
}
