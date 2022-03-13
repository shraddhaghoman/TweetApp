package com.tweetapp.service;

import java.util.List;

import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;

public interface TweetAppActiveService {
	
	public void createTweet(Tweet tweet);
	public List<Tweet> viewMyTweets();
	public List<Tweet> viewAllTweets();
	public List<User> viewAllUsers();
	public void resetPassword(String emailId);
	public void logoutUser(String emailId); //will set loggedIn to false for emailId
}
