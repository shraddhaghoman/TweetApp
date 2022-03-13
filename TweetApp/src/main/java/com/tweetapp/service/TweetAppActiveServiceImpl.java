package com.tweetapp.service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.TweetAppDao;
import com.tweetapp.dao.TweetUserDao;
import com.tweetapp.model.PasswordResetData;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;

@Service
public class TweetAppActiveServiceImpl implements TweetAppActiveService{

	
	@Autowired
	private TweetAppDao tweetAppDao;
	
	@Autowired
	private TweetUserDao tweetUserDao;
	
	Scanner sc = new Scanner(System.in);
	@Override
	public void createTweet(Tweet tweet) {
		 System.out.println("Data creation started...");
	     tweetAppDao.save(tweet);
	     System.out.println("Data creation complete...");
	}

	@Override
	public void viewMyTweets(String userId) {
		List<Tweet> myTweets = tweetAppDao.findByUserId(userId);
		if(myTweets.isEmpty()) {
			System.out.println("***No tweets available***");
		}
		else
			myTweets.forEach(tweet -> getTweetDetails(tweet));
	}

	@Override
	public void viewAllTweets() {
		List<Tweet> tweets = tweetAppDao.findAll();
		if(tweets.isEmpty()) {
			System.out.println("***No tweets available***");
		}
		else
		tweets.forEach(tweet -> getTweetDetails(tweet));
	}
	
	public void getTweetDetails(Tweet tweet) {

		System.out.println("Tweet: "+tweet.getText());
		System.out.println("Posted by: "+tweet.getUserId());
		System.out.println("Date posted: "+tweet.getDatePosted()+"\n");
        
    }
	
	public void getUserDetails(User user) {

		System.out.println("Name: "+user.getName());
		System.out.println("Email Id: "+user.getEmail());
		System.out.println("Mobile No.: "+user.getMobile()+"\n");
        
    }
	
	@Override
	public void viewAllUsers() {
		tweetUserDao.findAll().forEach(user -> getUserDetails(user));
	}

//	@Override
//	public String resetPassword(String emailId) {
//		Optional<User> user = tweetUserDao.findByEmail(emailId);
//		if(user.isPresent()) {
//			PasswordResetData passwordResetData = user.get().getPasswordResetData(); 
//			System.out.println("Answer Security Question: \n Question: "+ passwordResetData.getSecretQuestion()+"\n Answer: ");
//			String answer = sc.nextLine();
//			if(answer.equals(passwordResetData.getAnswer())) {
//				System.out.println("Enter new Password: ");
//				String newPassword = sc.nextLine();
//				user.get().setPassword(newPassword);
//				tweetUserDao.save(user.get());
//				return "success";
//			}else {
//				return "invalidAnswer";
//			}
//		}
//		return "invalidUser";
//	}

	@Override
	public String logoutUser(String emailId) {
		Optional<User> user = tweetUserDao.findByEmail(emailId);
		if(user.isPresent()) {
			user.get().setLoggedIn(false);
			tweetUserDao.save(user.get());
			return "success";
		}else {
			return "invalid";
		}
	}

}
