package com.blacktierental.virtualbook.dao;

import java.util.List;

import com.blacktierental.virtualbook.exceptions.ObjectNotFoundException;
import com.blacktierental.virtualbook.model.User;

public interface UserDao {
	
	User findById(int id) throws ObjectNotFoundException;
	User findByUsername(String username) throws ObjectNotFoundException;
	void save(User user);
	void deleteByUsername(String username);
	List<User> findAllUsers();
}
