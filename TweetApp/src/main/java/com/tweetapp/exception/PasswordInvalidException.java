package com.tweetapp.exception;

public class PasswordInvalidException extends Exception {

	public PasswordInvalidException(String email_login) {
		super("Password is Invalid for username: " + email_login);
	}
}
