package com.blacktierental.virtualbook.model;

import java.io.Serializable;
import java.time.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="tbl_persistent_logins")
public class PersistentLogin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String series;
	
	@Column(name="username",unique=true,nullable=false)
	private String username;
	
	@Column(name="token",unique=true, nullable=false)
	private String token;
	
	private LocalDate last_used;

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDate getLast_used() {
		return last_used;
	}

	public void setLast_used(LocalDate last_used) {
		this.last_used = last_used;
	}
	
	
}
