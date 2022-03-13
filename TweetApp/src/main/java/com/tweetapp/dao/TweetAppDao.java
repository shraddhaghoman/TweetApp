package com.tweetapp.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.Tweet;

@Repository
public interface TweetAppDao extends MongoRepository<Tweet, String>{

//	public PasswordResetData getUserSecretQA(String email);
//	public boolean updatePassword(String email, String newPassword);
	
}
