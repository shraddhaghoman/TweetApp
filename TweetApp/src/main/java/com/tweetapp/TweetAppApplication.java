package com.tweetapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tweetapp.model.PasswordResetData;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.service.TweetAppActiveService;
import com.tweetapp.service.TweetAppActiveServiceImpl;
import com.tweetapp.service.TweetAppInactiveService;
import com.tweetapp.service.TweetAppInactiveServiceImpl;

@SpringBootApplication
public class TweetAppApplication {

	private static Scanner sc;
	
	private static TweetAppInactiveService tweetAppInactiveService= new TweetAppInactiveServiceImpl();
	private static TweetAppActiveService tweetAppActiveService= new TweetAppActiveServiceImpl();
	
	private static String loggedInUser;
	public static void main(String[] args) {
		//SpringApplication.run(TweetAppApplication.class, args);
		try {
			sc = new Scanner(System.in);
			boolean isLoggedIn = tweetAppInactiveService.isUserLoggedIn();
			while(true) {
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
						Tweet tweet  = new Tweet("id", tweetText, loggedInUser, currentDate);
						System.out.println(tweet.getDatePosted());
						tweetAppActiveService.createTweet(tweet);
						break;
					case 2:
						System.out.println("View my tweets");
						List<Tweet> myTweets = tweetAppActiveService.viewMyTweets();
						myTweets.forEach(myTweet -> {
							System.out.println("Tweet: "+myTweet.getText());
							System.out.println("Date posted: "+myTweet.getDatePosted());
						});
						break;
					case 3: 
						System.out.println("View all tweets");
						List<Tweet> allTweets = tweetAppActiveService.viewMyTweets();
						allTweets.forEach(currentTweet -> {
							System.out.println("Tweet: "+currentTweet.getText());
							System.out.println("Posted by: "+currentTweet.getUserEmail());
							System.out.println("Date posted: "+currentTweet.getDatePosted());
						});
						break;
					case 4:
						System.out.println("View All Users");
						List<User> usersList = tweetAppActiveService.viewAllUsers();
						usersList.forEach(user -> {
							System.out.println("Name: "+user.getName());
							System.out.println("Email Id: "+user.getEmail());
							System.out.println("Mobile No.: "+user.getMobile());
							System.out.println("Tweets posted"+ user.getTweets().size());
						});
						break;
					case 5:
						System.out.println("Reset Password");
						break;
					case 6:
						System.out.println("Logout");
						isLoggedIn = false;
						break;
					default:
						System.out.println("Choose correct option");
					}				
					
				}else {
					System.out.println("You are not logged In \nChoose the action"
							+ "\n 1.Register \n 2.Login \n 3.Password Reset \n 4.Exit");
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
						User user = new User("1", name, email, mobile, password,
								false,passwordResetData,new ArrayList<String>());//generate auto id
						tweetAppInactiveService.registerUser(user);
						break;
					case 2:
						System.out.println("Login");
						System.out.println("Enter Email-Id");
						String email_login= sc.nextLine();
						System.out.println("Enter Password");
						String password_login=sc.nextLine();
						if(tweetAppInactiveService.validateCredentials(email_login,password_login)) {
							isLoggedIn = true;
							loggedInUser = email_login;
							System.out.println("Successfully logged in");
						}else {
							break;
						}
						break;
					case 3: 
						System.out.println("Password reset");
						
						System.out.println();
						break;
					case 4:
						System.out.println("Operation Ended");
						return;
					default:
						System.out.println("Choose correct option");
					}				
					
				}
				
			}
			
		}catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
		}
	}

}
