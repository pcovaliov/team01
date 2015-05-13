package com.endava.aminternship.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.UserService;


@Controller
@RequestMapping(value = "/rest/user" )
public class RESTUserController {
	@Autowired
	private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	Map<String, Object> dataMap = new HashMap<String, Object>();
    	List users = userService.listUser(9999999,0); //first param is limit
    	dataMap.put("users", users);
    	dataMap.put("links", new Date());
    	
        return dataMap;
    }
}