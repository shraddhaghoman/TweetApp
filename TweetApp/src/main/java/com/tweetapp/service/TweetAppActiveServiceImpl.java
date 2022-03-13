package com.tweetapp.service;

import java.util.List;
import java.util.Scanner;

import com.tweetapp.dao.TweetAppDao;
import com.tweetapp.model.PasswordResetData;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;

public class TweetAppActiveServiceImpl implements TweetAppActiveService{

	TweetAppDao tweetAppDao;
	Scanner sc = new Scanner(System.in);
	@Override
	public void createTweet(Tweet tweet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tweet> viewMyTweets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tweet> viewAllTweets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> viewAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetPassword(String emailId) {
		//find question and answer for emailid from database
		PasswordResetData passwordResetData = tweetAppDao.getUserSecretQA(emailId);
		System.out.println("Answer the Security Question: \n "+passwordResetData.getSecretQuestion());
		String answer =  sc.nextLine();
		if(answer.equals(passwordResetData.getAnswer())) {
			System.out.println("Insert new password: ");
			String password =  sc.nextLine();
			if(tweetAppDao.updatePassword(emailId, password)) {
				System.out.println("password updated successfully");
			}else {
				System.out.println("Error while updating password");
			}
		}else {
			System.out.println("Wrong answer to security question");
		}
		
	}

	@Override
	public void logoutUser(String emailId) {
		// TODO Auto-generated method stub
		
	}

}
