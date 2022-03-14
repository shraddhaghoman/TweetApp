package com.tweetapp.exception;

public class UsernameInvalidException extends Exception{

	public UsernameInvalidException(String email_id) {
		super("Username is Invalid: "+email_id);
	}
}
