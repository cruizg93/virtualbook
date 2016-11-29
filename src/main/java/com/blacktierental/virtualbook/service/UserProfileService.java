package com.blacktierental.virtualbook.service;

import java.util.List;

import com.blacktierental.virtualbook.model.UserProfile;

public interface UserProfileService {

	UserProfile findById(int id);
	UserProfile findByType(String type);
	List<UserProfile> findAll();
}
