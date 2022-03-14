package com.tweetapp.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.TweetUserDao;
import com.tweetapp.exception.AnswerInvalidException;
import com.tweetapp.exception.PasswordInvalidException;
import com.tweetapp.exception.UsernameInvalidException;
import com.tweetapp.model.PasswordResetData;
import com.tweetapp.model.User;

@Service
public class TweetAppInactiveServiceImpl implements TweetAppInactiveService {

	@Autowired
	TweetUserDao tweetUserDao;

	private static Scanner sc = new Scanner(System.in);

	@Override
	public boolean isUserLoggedIn(String email_login) {
		Optional<User> user = tweetUserDao.findByEmail(email_login);
		
		if (user.isPresent()) {
			if (user.get().isLoggedIn()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public void registerUser() {

		System.out.println("Enter name");
		String name = sc.nextLine();
		System.out.println("Enter Email-Id");
		String email = sc.nextLine();
		System.out.println("Enter Mobile Number");
		String mobile = sc.nextLine();
		System.out.println("Choose Password");
		String password = sc.nextLine();
		System.out.println("Choose secret question");
		String secretQuestion = sc.nextLine();
		System.out.println("Answer for secret question");
		String answer = sc.nextLine();
		PasswordResetData passwordResetData = new PasswordResetData(secretQuestion, answer);
		User user = new User(name, email, mobile, password, false, passwordResetData);

		System.out.println("Started Registration");
		tweetUserDao.save(user);
		System.out.println("Registered successfully");
	}

	@Override
	public void validateCredentials(String email_login) throws PasswordInvalidException, UsernameInvalidException {

		System.out.println("Enter Password");
		String password_login = sc.nextLine();

		Optional<User> user = tweetUserDao.findByEmail(email_login);
		if (user.isPresent()) {
			if (user.get().getPassword().equals(password_login)) {
				user.get().setLoggedIn(true);
				tweetUserDao.save(user.get());
				System.out.println("Successfully logged in");
			} else {
				throw new PasswordInvalidException(email_login);
			}
		} else {
			throw new UsernameInvalidException(email_login);
		}
			
	}

	@Override
	public void resetPassword(String email_reset) throws AnswerInvalidException, UsernameInvalidException {

		if(email_reset.isEmpty()) {
			System.out.println("Enter Username");
			email_reset = sc.nextLine();
		}

		Optional<User> user = tweetUserDao.findByEmail(email_reset);
		if (user.isPresent()) {
			PasswordResetData passwordResetData = user.get().getPasswordResetData();
			System.out.println(
					"Answer Security Question: \n Question: " + passwordResetData.getSecretQuestion() + "\n Answer: ");
			String answer = sc.nextLine();
			if (answer.equals(passwordResetData.getAnswer())) {
				System.out.println("Enter new Password: ");
				String newPassword = sc.nextLine();
				user.get().setPassword(newPassword);
				tweetUserDao.save(user.get());
				System.out.println("Password reset successful");
			} else {
				throw new AnswerInvalidException(passwordResetData.getSecretQuestion());
			}
		} else
			throw new UsernameInvalidException(email_reset);
	}

}
