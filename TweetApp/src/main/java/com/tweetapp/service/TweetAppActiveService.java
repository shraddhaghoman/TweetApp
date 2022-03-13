package com.tweetapp.service;

import org.springframework.stereotype.Service;

import com.tweetapp.model.Tweet;

@Service
public interface TweetAppActiveService {
	
	public void createTweet(Tweet tweet);
	public void viewMyTweets(String userId);
	public void viewAllTweets();
	public void viewAllUsers();
//	public String resetPassword(String emailId);
	public String logoutUser(String emailId); 
}
