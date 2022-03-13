package com.tweetapp.service;

import java.util.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.model.PasswordResetData;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;

@Service
public class TweetAppService {

	@Autowired
	private TweetAppInactiveService tweetAppInactiveService;
	
	@Autowired
	private TweetAppActiveService tweetAppActiveService;
	
	private static Scanner sc;
	
	private static String loggedInUser;
	
	public void runApplication() {
		sc = new Scanner(System.in);
		
		while(true) {
			boolean isLoggedIn = tweetAppInactiveService.isUserLoggedIn(loggedInUser);
			if(isLoggedIn) {
				System.out.println("\nChoose the action"
						+ "\n 1.Post a tweet" +"\n " + 
						"2.View my tweets" +"\n " + 
						"3.View all tweets" +"\n " + 
						"4.View All Users" +"\n " + 
						"5.Reset Password" +"\n " + 
						"6.Logout");
				int option = sc.nextInt();
				sc.nextLine();
				switch(option) {
				case 1:
					System.out.println("Action: Post a tweet");
					System.out.println("Write a tweet: ");
					String tweetText = sc.nextLine();
					Date currentDate =  new Date();
					Tweet tweet  = new Tweet(tweetText,loggedInUser, currentDate);
					System.out.println(tweet.getDatePosted());
					tweetAppActiveService.createTweet(tweet);
					break;
				case 2:
					System.out.println("View my tweets");
					tweetAppActiveService.viewMyTweets(loggedInUser);
					break;
				case 3: 
					System.out.println("View all tweets");
					tweetAppActiveService.viewAllTweets();
					break;
				case 4:
					System.out.println("View All Users");
					tweetAppActiveService.viewAllUsers();
					break;
				case 5:
					System.out.println("Reset Password");
					String status_reset = tweetAppInactiveService.resetPassword(loggedInUser);
					if("success".equals(status_reset)) {
						System.out.println("Password reset successful");
					}else if("invalidAnswer".equals(status_reset)) {
						System.out.println("Invalid Answer to security Question");
					}else {
						System.out.println("Invalid Session");
					}
					break;
				case 6:
					String status =  tweetAppActiveService.logoutUser(loggedInUser);
					if("success".equals(status)) {
						System.out.println("Logged out Successfully");
						isLoggedIn = false;
					}else {
						System.out.println("Invalid Session");
					}
					break;
				default:
					System.out.println("Choose correct option");
				}				
				
			}else {
				System.out.println("You are not logged In \nChoose the action"
						+ "\n 1.Register \n 2.Login \n 3.Reset Password \n 4.Exit");
				int option = sc.nextInt();
				sc.nextLine();
				switch(option) {
				case 1:
					System.out.println("***Registration***");
					System.out.println("Enter name");
					String name= sc.nextLine();
					System.out.println("Enter Email-Id");
					String email= sc.nextLine();
					System.out.println("Enter Mobile Number");
					String mobile= sc.nextLine();
					System.out.println("Choose Password");
					String password= sc.nextLine();
					System.out.println("Choose secret question");
					String secretQuestion =  sc.nextLine();
					System.out.println("Answer for secret question");
					String answer = sc.nextLine();
					PasswordResetData passwordResetData = new PasswordResetData(secretQuestion, answer);
					User user = new User(name, email, mobile, password,
							false,passwordResetData);
					tweetAppInactiveService.registerUser(user);
					break;
				case 2:
					System.out.println("Login");
					System.out.println("Enter Email-Id");
					String email_login= sc.nextLine();
					System.out.println("Enter Password");
					String password_login=sc.nextLine();
					String status = tweetAppInactiveService.validateCredentials(email_login,password_login);
					if("validUser".equals(status)) {
						isLoggedIn = true;
						loggedInUser = email_login;
						System.out.println("Successfully logged in");
					}else if("userNotFound".equals(status)){
						System.out.println("Invalid Username");
						break;
					}else {
						System.out.println("Invalid Password");
						break;
					}
					break;
				case 3: 
					System.out.println("Reset Password");
					System.out.println("Enter Email-ID");
					String email_reset =  sc.nextLine();
					String status_reset = tweetAppInactiveService.resetPassword(email_reset);
					if("success".equals(status_reset)) {
						System.out.println("Password reset successful");
					}else if("invalidAnswer".equals(status_reset)) {
						System.out.println("Invalid Answer to security Question");
					}else {
						System.out.println("Invalid Email-Id");
					}
					break;
				case 4:
					System.out.println("Operation Ended");
					return;
				default:
					System.out.println("Choose correct option");
				}				
				
			}
			
		}
	}
}
