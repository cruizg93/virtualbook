package com.blacktierental.virtualbook.service;

import java.util.List;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.User;

public interface UserService {
	User findById(int id) throws ObjectNotFoundException;
	User findByUsername(String username) throws ObjectNotFoundException;
	
	void saveUser(User user);
	void updateUser(User user) throws ObjectNotFoundException;
	void deleteUserByUsername(String username);
	
	List<User> findAllUsers();
	boolean isUserUsernameUnique(Integer id,String username) throws ObjectNotFoundException;
}
