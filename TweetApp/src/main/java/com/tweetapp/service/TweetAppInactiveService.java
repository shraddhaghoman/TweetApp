package com.tweetapp.service;

import org.springframework.stereotype.Service;

import com.tweetapp.model.User;

@Service
public interface TweetAppInactiveService {

	public boolean isUserLoggedIn();
	public void registerUser(User user);
//	public void updateLoginStatus(String id, boolean status);
	public boolean validateCredentials(String email_login, String password_login);
}
