package com.blacktierental.virtualbook.model;

public enum UserProfileType {
	USER("USER"),
	ADMIN("ADMIN"),
	SUPERVISOR("SUPERVISOR"),
	DBA("DBA");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
}
