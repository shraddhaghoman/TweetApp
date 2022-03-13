package com.tweetapp.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tweets")
public class Tweet {

	@Id
	private String tweetId;
	private String text;
	private String userId;
	private Date datePosted;
	
	
	public Tweet(String text, String userId, Date datePosted) {
		super();
//		this.tweetId = tweetId;
		this.text = text;
		this.userId = userId;
		this.datePosted = datePosted;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTweetId() {
		return tweetId;
	}
	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDatePosted() {
		return datePosted;
	}
	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}
	
	
}
