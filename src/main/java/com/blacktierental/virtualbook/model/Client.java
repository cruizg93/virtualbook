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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tbl_client")
public class Client {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@Column(name="name")
	private String name;
	
	@NotEmpty
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="email")
	@Pattern(regexp="\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b")
	private String email;
	
	@NotEmpty
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="state",nullable=false)
	private String state=State.ACTIVE.getState();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public int hashCode(){
		final int prime = 33;
		int result = 1;
		result = prime * result + ((id==null)?0:id.hashCode());
		result = prime * result + ((name==null)?0: name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if(this== obj)
			return true;
		if(obj==null)
			return false;
		if(!(obj instanceof Client))
			return false;
		Client other = (Client) obj;
		if( id == null){
			if(other.id!= null)
				return false;
		}else if(!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "Client [id="+id+",name="+name+",CompanyName="+companyName
				+",PhoneNumber="+phoneNumber+",state="+state+"]";
	}
}
