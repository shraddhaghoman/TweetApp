package com.tweetapp;

import java.util.Scanner;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TweetAppApplication {

	private static Scanner sc;

	public static void main(String[] args) {
		//SpringApplication.run(TweetAppApplication.class, args);
		try {
			sc = new Scanner(System.in);
			boolean isLoggedIn = true;
			while(true) {
				if(isLoggedIn) {
					System.out.println("You are not logged In \nChoose the action"
							+ "\n 1.Post a tweet" +"\n " + 
							"2.View my tweets" +"\n " + 
							"3.View all tweets" +"\n " + 
							"4.View All Users" +"\n " + 
							"5.Reset Password" +"\n " + 
							"6.Logout");
					int option = sc.nextInt();
					switch(option) {
					case 1:
						System.out.println("Post a tweet");
						break;
					case 2:
						System.out.println("View my tweets");
						break;
					case 3: 
						System.out.println("View all tweets");
						break;
					case 4:
						System.out.println("View All Users");
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
					switch(option) {
					case 1:
						System.out.println("Reg");
						break;
					case 2:
						System.out.println("Login");
						break;
					case 3: 
						System.out.println("Password reset");
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
