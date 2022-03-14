package com.tweetapp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.TweetAppDao;
import com.tweetapp.dao.TweetUserDao;
import com.tweetapp.exception.UsernameInvalidException;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;

@Service
public class TweetAppActiveServiceImpl implements TweetAppActiveService {

	@Autowired
	private TweetAppDao tweetAppDao;

	@Autowired
	private TweetUserDao tweetUserDao;

	Scanner sc = new Scanner(System.in);

	@Override
	public void createTweet(String userId) {

		boolean isValid = false;
		String tweetText = "";
		do {
			System.out.println("Write a tweet: ");
			tweetText = sc.nextLine();
			if (Pattern.matches("[A-Za-z]{3}", tweetText)) {
				isValid = true;
			} else {
				System.out.println("Tweet must contain atleast 3 characters(A-Z/a-z)");
				isValid = false;
			}
		} while (!isValid);

		Date currentDate = new Date();
		Tweet tweet = new Tweet(tweetText, userId, currentDate);
		System.out.println(tweet.getDatePosted());
		System.out.println("Data creation started...");
		tweetAppDao.save(tweet);
		System.out.println("Data creation complete...");
	}

	@Override
	public void viewMyTweets(String userId) {
		List<Tweet> myTweets = tweetAppDao.findByUserId(userId);
		if (myTweets.isEmpty()) {
			System.out.println("***No tweets available***");
		} else
			myTweets.forEach(tweet -> getTweetDetails(tweet));
	}

	@Override
	public void viewAllTweets() {
		List<Tweet> tweets = tweetAppDao.findAll();
		if (tweets.isEmpty()) {
			System.out.println("***No tweets available***");
		} else
			tweets.forEach(tweet -> getTweetDetails(tweet));
	}

	public void getTweetDetails(Tweet tweet) {

		System.out.println("Tweet: " + tweet.getText());
		System.out.println("Posted by: " + tweet.getUserId());
		System.out.println("Date posted: " + tweet.getDatePosted() + "\n");

	}

	public void getUserDetails(User user) {

		System.out.println("Name: " + user.getName());
		System.out.println("Email Id: " + user.getEmail());
		System.out.println("Mobile No.: " + user.getMobile() + "\n");

	}

	@Override
	public void viewAllUsers() {
		tweetUserDao.findAll().forEach(user -> getUserDetails(user));
	}

	@Override
	public void logoutUser(String emailId) throws UsernameInvalidException {
		Optional<User> user = tweetUserDao.findByEmail(emailId);
		if (user.isPresent()) {
			user.get().setLoggedIn(false);
			tweetUserDao.save(user.get());
			System.out.println("Logged out Successfully");
		} else {
			throw new UsernameInvalidException(emailId);
		}
	}

}
