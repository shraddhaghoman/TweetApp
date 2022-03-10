package com.tweetapp.service;

import com.tweetapp.model.User;

public class TweetAppInactiveServiceImpl implements TweetAppInactiveService{

	@Override
	public boolean isUserLoggedIn() {
		return false;
	}

	@Override
	public void registerUser(User user) {
		System.out.println("Registered successfully");
	}

	@Override
	public boolean validateCredentials(String email_login, String password_login) {
		//if valid set loggedIn as true
		return true;
	}

}
