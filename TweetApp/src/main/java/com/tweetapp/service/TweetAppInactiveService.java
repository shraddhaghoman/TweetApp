package com.tweetapp.service;

import com.tweetapp.model.User;

public interface TweetAppInactiveService {

	public boolean isUserLoggedIn();
	public void registerUser(User user);
//	public void updateLoginStatus(String id, boolean status);
	public boolean validateCredentials(String email_login, String password_login);
}
