package com.endava.aminternship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.endava.aminternship.dao.interfaces.UserDAO;
import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	public void addUser(User user) {
		userDAO.addUser(user);
	}

	public List<User> listUser(int limit, int offset) {

		return userDAO.listUser(limit, offset);
	}

	public void removeUser(Integer id) {
		userDAO.removeUser(id);
	}
	
	public User findUserById(Integer id) {
		return userDAO.findUserById(id);
	}

	@Override
	public boolean updateUser(User user) {
		return userDAO.updateUser(user);
		
	}

	@Override
	public User findUserByEmail(String email) {
		return userDAO.findUserByEmail(email);
	}
}
