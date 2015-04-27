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
public class TweetController {
	private static final Logger logger = LoggerFactory
			.getLogger(TweetController.class);

	@RequestMapping(value = "/tweet-page", method = RequestMethod.GET)
	public String registerUserForm(Map<String, Object> map) {
		//map.put("user", new User());
		return "/tweet-page";
	}

	
	

}
