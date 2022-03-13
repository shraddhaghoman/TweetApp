package com.tweetapp.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.TweetAppDao;
import com.tweetapp.model.PasswordResetData;
import com.tweetapp.model.Tweet;

@Service
public class TweetAppActiveServiceImpl implements TweetAppActiveService{

	
	@Autowired
	private TweetAppDao tweetAppDao;
	
	Scanner sc = new Scanner(System.in);
	@Override
	public void createTweet(Tweet tweet) {
		 System.out.println("Data creation started...");
	     tweetAppDao.save(tweet);
	     System.out.println("Data creation complete...");
	}

	@Override
	public void viewMyTweets() {
		
	}

	@Override
	public void viewAllTweets() {
		System.out.println("yo");
		System.out.println("val"+ tweetAppDao);
		tweetAppDao.findAll().forEach(tweet -> getTweetDetails(tweet));
	}
	
	public void getTweetDetails(Tweet tweet) {

		System.out.println("Tweet: "+tweet.getText());
		System.out.println("Posted by: "+tweet.getUserId());
		System.out.println("Date posted: "+tweet.getDatePosted());
        
    }
	@Override
	public void viewAllUsers() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resetPassword(String emailId) {
		//find question and answer for emailid from database
//		PasswordResetData passwordResetData = tweetAppDao.getUserSecretQA(emailId);
//		System.out.println("Answer the Security Question: \n "+passwordResetData.getSecretQuestion());
//		String answer =  sc.nextLine();
//		if(answer.equals(passwordResetData.getAnswer())) {
//			System.out.println("Insert new password: ");
//			String password =  sc.nextLine();
//			if(tweetAppDao.updatePassword(emailId, password)) {
//				System.out.println("password updated successfully");
//			}else {
//				System.out.println("Error while updating password");
//			}
//		}else {
//			System.out.println("Wrong answer to security question");
//		}
		
	}

	@Override
	public void logoutUser(String emailId) {
		// TODO Auto-generated method stub
		
	}

}
