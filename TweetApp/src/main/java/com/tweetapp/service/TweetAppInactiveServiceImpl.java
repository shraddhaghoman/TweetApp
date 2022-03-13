package com.tweetapp.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.TweetUserDao;
import com.tweetapp.model.PasswordResetData;
import com.tweetapp.model.User;

@Service
public class TweetAppInactiveServiceImpl implements TweetAppInactiveService{

	@Autowired
	TweetUserDao tweetUserDao;
	
	private static Scanner sc = new Scanner(System.in);
	
	@Override
	public boolean isUserLoggedIn(String email_login) {
		Optional<User> user = tweetUserDao.findByEmail(email_login);
		if(user.isPresent()) {
			if(user.get().isLoggedIn()) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	@Override
	public void registerUser(User user) {
		System.out.println("Started Registration");
		tweetUserDao.save(user);
		System.out.println("Registered successfully");
	}

	@Override
	public String validateCredentials(String email_login, String password_login) {
		Optional<User> user = tweetUserDao.findByEmail(email_login);
		if(user.isPresent()) {
			if(user.get().getPassword().equals(password_login)) {
				user.get().setLoggedIn(true);
				tweetUserDao.save(user.get());
				return "validUser";
			}else {
				return "invalidPassword";
			}
		}
		return "userNotFound";
	}

	@Override
	public String resetPassword(String email_reset) {
		Optional<User> user = tweetUserDao.findByEmail(email_reset);
		if(user.isPresent()) {
			PasswordResetData passwordResetData = user.get().getPasswordResetData(); 
			System.out.println("Answer Security Question: \n Question: "+ passwordResetData.getSecretQuestion()+"\n Answer: ");
			String answer = sc.nextLine();
			if(answer.equals(passwordResetData.getAnswer())) {
				System.out.println("Enter new Password: ");
				String newPassword = sc.nextLine();
				user.get().setPassword(newPassword);
				tweetUserDao.save(user.get());
				return "success";
			}else {
				return "invalidAnswer";
			}
		}
		return "invalidUser";
	}

}
