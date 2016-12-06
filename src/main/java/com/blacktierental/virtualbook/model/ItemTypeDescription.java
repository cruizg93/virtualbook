package com.blacktierental.virtualbook.model;

public enum ItemTypeDescription {
	MAIN("MAIN"),
	ATTACHMENT("ATTACHMENT");
	
	String itemTypeDescription;
	
	private ItemTypeDescription(String itemTypeDescription){
		this.itemTypeDescription = itemTypeDescription;
	}
	
	public String getItemTypeDescription(){
		return itemTypeDescription;
	}
}
