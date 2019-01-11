package com.twitter.analytics.core.connection;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterConnection {

	@SuppressWarnings("static-access")
	public Twitter openConnection() {
		Twitter twitter = null;
		try {
			TwitterFactory factory = new TwitterFactory();
			AccessToken accessToken = carregarToken();
			
			twitter = factory.getSingleton();
			twitter.setOAuthConsumer("", "");
			twitter.setOAuthAccessToken(accessToken);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return twitter;
	}
	
	private static AccessToken carregarToken() {
		
		String token = "";
		String tokenSecret = "";
		return new AccessToken(token, tokenSecret);

}

}
