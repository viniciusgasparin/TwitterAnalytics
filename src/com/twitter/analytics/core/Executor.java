package com.twitter.analytics.core;

import com.twitter.analytics.core.service.TwitterServices;

import twitter4j.TwitterException;

public class Executor {

	private static final String HASH_TAG = "#java8";
	
	public static void main(String[] args) {

		try {

			new TwitterServices().buscarTweetsPorHashtag(HASH_TAG);
	
		} catch (TwitterException e) {
			
			e.printStackTrace();
		}
	}
	
}
