package com.tweetapp.dao;

import com.tweetapp.model.PasswordResetData;

public interface TweetAppDao {

	public PasswordResetData getUserSecretQA(String email);
	public boolean updatePassword(String email, String newPassword);
}
