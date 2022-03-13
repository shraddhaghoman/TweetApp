package com.tweetapp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
public class User {
	
	@Id
	private String id; 
	private String name;
	private String email; //unique
	private String mobile;
	private String password;
	private boolean loggedIn;
	private PasswordResetData passwordResetData;
	private List<String> tweets;
	
	
	public User(String id, String name, String email, String mobile, String password, boolean loggedIn,
			PasswordResetData passwordResetData, List<String> tweets) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.loggedIn = loggedIn;
		this.passwordResetData = passwordResetData;
		this.tweets = tweets;
	}
	public PasswordResetData getPasswordResetData() {
		return passwordResetData;
	}
	public void setPasswordResetData(PasswordResetData passwordResetData) {
		this.passwordResetData = passwordResetData;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getTweets() {
		return tweets;
	}
	public void setTweets(List<String> tweets) {
		this.tweets = tweets;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
}
