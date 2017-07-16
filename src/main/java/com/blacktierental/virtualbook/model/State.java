package com.blacktierental.virtualbook.model;

public enum State {

	ACTIVE("ACTIVE"),
	INACTIVE("INACTIVE"),
	DELETED("DELETED"),
	LOCKED("LOCKED");
	
	private String state;
	
	private State(final String state){
		this.state = state;
	}
	
	public String getState(){
		return this.state;
	}
	@Override
	public String toString(){
		return this.state;
	}
	
	public String getName(){
		return this.getName();
	}

}
