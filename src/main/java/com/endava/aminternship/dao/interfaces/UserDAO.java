package com.endava.aminternship.dao.interfaces;

import java.util.List;

import com.endava.aminternship.entity.User;

public interface UserDAO {

	public void addUser(User user);

	public List<User> listUser();

	public void removeUser(Integer id);
	public User findUserById(Integer id);
}
