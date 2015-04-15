package com.endava.aminternship.service.interfaces;

import java.util.List;

import com.endava.aminternship.entity.User;

public interface UserService {

	public void addUser(User user);

	public List<User> listUser();

	public void removeUser(Integer id);
}
