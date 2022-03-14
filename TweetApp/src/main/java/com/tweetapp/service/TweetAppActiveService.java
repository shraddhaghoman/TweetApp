package com.tweetapp.service;

import org.springframework.stereotype.Service;

import com.tweetapp.exception.UsernameInvalidException;

@Service
public interface TweetAppActiveService {
	
	public void createTweet(String userId);
	public void viewMyTweets(String userId);
	public void viewAllTweets();
	public void viewAllUsers();
	public void logoutUser(String emailId) throws UsernameInvalidException; 
}
