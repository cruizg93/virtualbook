package com.blacktierental.virtualbook.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tbl_location")
public class Location {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@Column(name="location")
	private String location;
	
	@Column(name="building_name")
	private String buildingName;
	
	@NotEmpty
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="state",nullable=false)
	private String state=State.ACTIVE.getState();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getState() {
		return state.toUpperCase();
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public int hashCode(){
		final int prime = 34;
		int result = 1;
		result = prime * result + ((id==null)?0:id.hashCode());
		result = prime * result + ((location==null)?0:location.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if(this==obj)
			return true;
		if(obj==null)
			return false;
		if(!(obj instanceof Location))
			return false;
		Location other = (Location)obj;
		if(id==null){
			if(other.id!=null)
				return false;
		}else if(!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "Location [id="+id+",location="+location+",BuildingName="+buildingName
				+",phone="+phoneNumber+",state="+state+"]";
	}
	
	/**
	 * This will be call from client select element in event registration form
	 * @return
	 */
	public String getSelectDescription(){
		return buildingName;
	}
}
