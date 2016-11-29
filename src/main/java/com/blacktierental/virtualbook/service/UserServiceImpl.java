package com.blacktierental.virtualbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blacktierental.virtualbook.dao.UserDao;
import com.blacktierental.virtualbook.model.User;
 

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User findById(int id) {
		return dao.findById(id);
	}

	public User findByUsername(String username) {
		return dao.findByUsername(username);
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}

	/*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends. 
     */
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setAddress(user.getAddress());
			entity.setEmail(user.getEmail());
			entity.setName(user.getName());
			entity.setPassword(user.getPassword());
			entity.setPhoneNumber(user.getPhoneNumber());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	public void deleteUserByUsername(String username) {
		dao.deleteByUsername(username);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public boolean isUserUsernameUnique(Integer id, String username) {
		User user = findByUsername(username);
		return (user == null || ((id != null )&& (user.getId() == id)));
	}
}

