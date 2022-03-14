package com.tweetapp.exception;

public class AnswerInvalidException extends Exception {

	public AnswerInvalidException(String question) {
		super("Invalid Answer to security Question: " + question);
	}
}
