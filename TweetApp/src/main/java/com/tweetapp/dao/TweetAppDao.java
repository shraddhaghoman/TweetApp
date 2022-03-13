package com.tweetapp.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.Tweet;

@Repository
public interface TweetAppDao extends MongoRepository<Tweet, String>{

	List<Tweet> findByUserId(String userId);
	
}
