package com.tweetapp.model;

public class PasswordResetData {
	
	private String secretQuestion;
	private String answer;
	
	public PasswordResetData(String secretQuestion, String answer) {
		super();
		this.secretQuestion = secretQuestion;
		this.answer = answer;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}	
	
	

}
