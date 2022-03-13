package com.tweetapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;

@Service
public interface TweetAppActiveService {
	
	public void createTweet(Tweet tweet);
	public void viewMyTweets();
	public void viewAllTweets();
	public void viewAllUsers();
	public void resetPassword(String emailId);
	public void logoutUser(String emailId); //will set loggedIn to false for emailId
}
