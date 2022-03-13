package com.tweetapp.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.model.User;

public interface TweetUserDao extends MongoRepository<User, String>{

}
