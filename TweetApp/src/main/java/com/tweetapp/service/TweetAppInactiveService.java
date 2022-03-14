package com.tweetapp.service;

import org.springframework.stereotype.Service;

import com.tweetapp.exception.AnswerInvalidException;
import com.tweetapp.exception.PasswordInvalidException;
import com.tweetapp.exception.UsernameInvalidException;

@Service
public interface TweetAppInactiveService {

	public boolean isUserLoggedIn(String email);

	public void registerUser();

	public void resetPassword(String loggedInUser) throws AnswerInvalidException, UsernameInvalidException;

	public void validateCredentials(String email_login) throws PasswordInvalidException, UsernameInvalidException;
}
