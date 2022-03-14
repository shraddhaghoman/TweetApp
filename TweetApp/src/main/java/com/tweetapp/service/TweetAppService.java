package com.tweetapp.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.exception.AnswerInvalidException;
import com.tweetapp.exception.PasswordInvalidException;
import com.tweetapp.exception.UsernameInvalidException;

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

		while (true) {
			boolean isLoggedIn = tweetAppInactiveService.isUserLoggedIn(loggedInUser);
			if (isLoggedIn) {
				System.out.println("\nChoose the action" + "\n 1.Post a tweet" + "\n " + "2.View my tweets" + "\n "
						+ "3.View all tweets" + "\n " + "4.View All Users" + "\n " + "5.Reset Password" + "\n "
						+ "6.Logout");
				int option = 0;
				if (!sc.hasNextInt()) {
					option = 7;
				} else {
					option = sc.nextInt();
				}
				sc.nextLine();
				switch (option) {
				case 1:
					System.out.println("Action: Post a tweet");
					tweetAppActiveService.createTweet(loggedInUser);
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

					try {
						tweetAppInactiveService.resetPassword(loggedInUser);
					} catch (AnswerInvalidException answerInvalidException) {
						System.out.println("Error: " + answerInvalidException.getMessage());
					} catch (UsernameInvalidException usernameInvalidException) {
						System.out.println("Error: " + usernameInvalidException.getMessage());
					}

					break;
				case 6:
					try {
						tweetAppActiveService.logoutUser(loggedInUser);
						isLoggedIn = false;
						loggedInUser = "";
					} catch (UsernameInvalidException usernameInvalidException) {
						System.out.println("Error: " + usernameInvalidException.getMessage());
					}
					break;
				default:
					System.out.println("Choose correct option");
				}

			} else {
				System.out.println("You are not logged In \nChoose the action"
						+ "\n 1.Register \n 2.Login \n 3.Reset Password \n 4.Exit");
				int option = 0;
				if (!sc.hasNextInt()) {
					option = 7;
				} else {
					option = sc.nextInt();
				}
				sc.nextLine();
				switch (option) {
				case 1:
					System.out.println("***Registration***");
					tweetAppInactiveService.registerUser();
					break;
				case 2:
					System.out.println("***Login***");
					try {
						System.out.println("Enter Email-Id");
						String email_login = sc.nextLine();
						tweetAppInactiveService.validateCredentials(email_login);
						isLoggedIn = true;
						loggedInUser = email_login;
					} catch (PasswordInvalidException passwordInvalidException) {
						System.out.println("Error: " + passwordInvalidException.getMessage());
					} catch (UsernameInvalidException usernameInvalidException) {
						System.out.println("Error: " + usernameInvalidException.getMessage());
					}
					break;
				case 3:
					System.out.println("***Reset Password***");

					try {
						tweetAppInactiveService.resetPassword("");
					} catch (AnswerInvalidException answerInvalidException) {
						System.out.println("Error: " + answerInvalidException.getMessage());
					} catch (UsernameInvalidException usernameInvalidException) {
						System.out.println("Error: " + usernameInvalidException.getMessage());
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
