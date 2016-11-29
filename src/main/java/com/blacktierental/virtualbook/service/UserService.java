package com.blacktierental.virtualbook.service;

import java.util.List;
import com.blacktierental.virtualbook.model.User;

public interface UserService {
	User findById(int id);
	User findByUsername(String username);
	
	void saveUser(User user);
	void updateUser(User user);
	void deleteUserByUsername(String username);
	
	List<User> findAllUsers();
	boolean isUserUsernameUnique(Integer id,String username);
}
