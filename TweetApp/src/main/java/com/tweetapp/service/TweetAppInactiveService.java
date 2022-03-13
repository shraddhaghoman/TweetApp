package com.tweetapp.service;

import org.springframework.stereotype.Service;

import com.tweetapp.model.User;

@Service
public interface TweetAppInactiveService {

	public boolean isUserLoggedIn(String email);
	public void registerUser(User user);
	public String resetPassword(String email);
	public String validateCredentials(String email_login, String password_login);
}
