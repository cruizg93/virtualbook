package com.blacktierental.virtualbook.dao;

import java.util.List;
import com.blacktierental.virtualbook.model.UserProfile;

public interface UserProfileDao {
	List<UserProfile> findAll();
	UserProfile findByType(String type);
	UserProfile findById(int id);
}
