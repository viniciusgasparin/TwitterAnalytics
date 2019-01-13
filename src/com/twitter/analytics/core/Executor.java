package com.twitter.analytics.core;

import com.twitter.analytics.core.service.TwitterServices;

import twitter4j.TwitterException;

public class Executor {

	private static final String HASH_TAG = "#java8";
	
	public static void main(String[] args) {

		try {

			new TwitterServices().buscarTweetsPorHashtag(HASH_TAG);
			
			new TwitterServices().enviarTweet("Projeto de conclusão da matéria JAVA executado com sucesso!  @michelpf");
	
		} catch (TwitterException e) {
			
			e.printStackTrace();
		}
	}
	
}
