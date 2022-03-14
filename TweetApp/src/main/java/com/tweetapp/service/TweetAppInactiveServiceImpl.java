package com.tweetapp.service;

import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

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

		boolean isValid = false;
		String name = "";
		do {
			System.out.println("Enter name");
			name = sc.nextLine();
			if (Pattern.matches("[A-Za-z]{3}", name)) {
				isValid = true;
			} else {
				System.out.println("Name must contain atleast 3 characters(A-Z/a-z)");
				isValid = false;
			}
		} while (!isValid);

		String email = "";
		do {
			System.out.println("Enter Email-Id");
			email = sc.nextLine();
			Optional<User> user = tweetUserDao.findByEmail(email);

			if (Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email) && !user.isPresent()) {
				isValid = true;
			} else {
				System.out.println("Email-id Invalid pattern or email already exist");
				isValid = false;
			}
		} while (!isValid);

		String mobile = "";
		do {
			System.out.println("Enter Mobile Number");
			mobile = sc.nextLine();
			if (Pattern.matches("[0-9]{10}", mobile)) {
				isValid = true;
			} else {
				System.out.println("Mobile number size required atleast 10 (0-9)");
				isValid = false;
			}
		} while (!isValid);
		String password = "";
		do {
			System.out.println("Choose Password");
			password = sc.nextLine();

			if (Pattern.matches("[A-Za-z0-9]{3}", password)) {
				isValid = true;
			} else {
				System.out.println("Password must contain atleast 3 characters(A-Za-z0-9)");
				isValid = false;
			}
		} while (!isValid);

		String secretQuestion = "";
		do {
			System.out.println("Choose secret question");
			secretQuestion = sc.nextLine();
			if (Pattern.matches("[A-Za-z]{3}", secretQuestion)) {
				isValid = true;
			} else {
				System.out.println("Secret question must contain atleast 3 characters(A-Z/a-z)");
				isValid = false;
			}
		} while (!isValid);

		String answer = "";
		do {
			System.out.println("Answer for secret question");
			answer = sc.nextLine();
			if (Pattern.matches("[A-Za-z]{3}", answer)) {
				isValid = true;
			} else {
				System.out.println("Secret answer must contain atleast 3 characters(A-Z/a-z)");
				isValid = false;
			}
		} while (!isValid);

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

		if (email_reset.isEmpty()) {
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
				String newPassword = "";
				boolean isValid = false;
				do {
					System.out.println("Enter new Password: ");
					newPassword = sc.nextLine();
					if (Pattern.matches("[A-Za-z0-9]{3}", newPassword)) {
						isValid = true;
					} else {
						System.out.println("Password must contain atleast 3 characters(A-Z/a-z/0-9)");
						isValid = false;
					}
				} while (!isValid);

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
