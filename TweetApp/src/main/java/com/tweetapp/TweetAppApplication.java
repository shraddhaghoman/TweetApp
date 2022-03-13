package com.tweetapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tweetapp.service.TweetAppService;

@SpringBootApplication
public class TweetAppApplication implements CommandLineRunner{

	@Autowired
	TweetAppService tweetAppService;
	public static void main(String[] args) {
		SpringApplication.run(TweetAppApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			tweetAppService.runApplication();
			
		}catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
		}		
	}
	
	

}
