package com.example.demo.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "User")
public class User {
	
	@Id
	private String userid;
	
	private String email;
	
	private String firstname;
	
	private String lastname;
	
	private String username;
	
	private String password;
	
	private Instant dob;
	
	private Instant joiningdate;
	
	private int pincode;

	private boolean isflagged;
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public Instant getDob() {
		return dob;
	}

	public void setDob(Instant dob) {
		this.dob = dob;
	}

	public Instant getJoiningdate() {
		return joiningdate;
	}

	public void setJoiningdate(Instant joiningdate) {
		this.joiningdate = joiningdate;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public boolean isIsflagged() {
		return isflagged;
	}

	public void setIsflagged(boolean isflagged) {
		this.isflagged = isflagged;
	}

	
}
