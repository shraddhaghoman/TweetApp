package com.tweetapp.model;

import java.util.Date;


public class Tweet {

	private String tweetId;
	private String text;
//	private String user;
	private Date datePosted;
	
	
	public Tweet(String tweetId, String text, Date datePosted) {
		super();
		this.tweetId = tweetId;
		this.text = text;
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
	public Date getDatePosted() {
		return datePosted;
	}
	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}
	
	
}
