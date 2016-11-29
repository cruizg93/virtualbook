package com.blacktierental.virtualbook.dao;

import java.util.List;
import com.blacktierental.virtualbook.model.User;

public interface UserDao {
	
	User findById(int id);
	User findByUsername(String username);
	void save(User user);
	void deleteByUsername(String username);
	List<User> findAllUsers();
}
