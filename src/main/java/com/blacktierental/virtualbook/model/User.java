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
@Table(name="tbl_user")
public class User {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@Column(name="username")
	private String username;

	@NotEmpty
	@Column(name="password")
	private String password;
	
	@NotEmpty
	@Column(name="name")
	private String name;
		
	@NotEmpty
	@Column(name="phone_number")
	private String phoneNumber;
	
	@NotEmpty
	@Column(name="email")
	private String email;

	@Column(name="state",nullable = false)
	private String state=State.ACTIVE.getState();
	
	@NotEmpty
	@Column(name="address")
	private String address;

	@NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_user_user_profile", 
             joinColumns = { @JoinColumn(name = "USER_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}
	
	
	
	 public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((id == null) ? 0 : id.hashCode());
	        result = prime * result + ((username== null) ? 0 : username.hashCode());
	        return result;
	    }
	 
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (!(obj instanceof User))
	            return false;
	        User other = (User) obj;
	        if (id == null) {
	            if (other.id != null)
	                return false;
	        } else if (!id.equals(other.id))
	            return false;
	        if (username == null) {
	            if (other.username != null)
	                return false;
	        } else if (!username.equals(other.username))
	            return false;
	        return true;
	    }
	 
	    @Override
	    public String toString() {
	        return "User [id=" + id + ", username=" + username + ", password=" + password
	                + ", name=" + name + ", phoneNumber=" + phoneNumber
	                + ", email=" + email + ", state="+state+"]";
	    }
}
