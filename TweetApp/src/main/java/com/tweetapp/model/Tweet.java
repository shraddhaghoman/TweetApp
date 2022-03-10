package com.tweetapp.model;

import java.util.Date;

public class Tweet {

	private String tweetId; //unique
	private String text;
	private String user;
	private Date datePosted;
	
	
	public Tweet(String tweetId, String text, String user, Date datePosted) {
		super();
		this.tweetId = tweetId;
		this.text = text;
		this.user = user;
		this.datePosted = datePosted;
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
	public String getUserEmail() {
		return user;
	}
	public void setUserEmail(String user) {
		this.user = user;
	}
	public Date getDatePosted() {
		return datePosted;
	}
	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}
	
	
}
