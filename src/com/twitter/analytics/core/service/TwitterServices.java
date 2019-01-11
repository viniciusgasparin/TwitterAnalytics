package com.twitter.analytics.core.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.twitter.analytics.core.connection.TwitterConnection;
import com.twitter.analytics.to.Tweet;

import twitter4j.DirectMessage;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterServices {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void buscarTweetsPorHashtag(String hashtag) throws TwitterException {

		Twitter twitter = new TwitterConnection().openConnection();

		Query query = new Query(hashtag);
		
		LocalDateTime dataInicio = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		dataInicio = dataInicio.plusDays(-6);

		QueryResult result;

		while (LocalDateTime.now().isAfter(dataInicio)) {
			
			int qtdeRetweets = 0;
			int qtdeFavoritacoes = 0;
		
			query.setSince(dataInicio.format(formatter));
			dataInicio = dataInicio.plusDays(1);
			query.setUntil(dataInicio.format(formatter));
			
			result = twitter.search(query);
			
			List<Tweet> listaTweets = new ArrayList();
			
			if (result != null && !result.getTweets().isEmpty()) {
				
				for (Status status : result.getTweets()) {

					listaTweets.add(new Tweet( status.getUser().getScreenName(), status.getText(), status.getCreatedAt() ));
					qtdeRetweets += status.getRetweetCount();
					qtdeFavoritacoes += status.getFavoriteCount();
				}
				
				System.out.println("Tweets dia " + dataInicio.plusDays(-1).format(formatter) + ": " + result.getTweets().size());
				System.out.println("Retweets dia " + dataInicio.plusDays(-1).format(formatter) + ": " + qtdeRetweets);
				System.out.println("Favoritações dia " + dataInicio.plusDays(-1).format(formatter) + ": " + qtdeFavoritacoes);
				System.out.println("---------------------------------------------");
				

			}
		}
	}

	public void enviarTweet(String mensagemTweet) throws TwitterException {

		Twitter twitter = new TwitterConnection().openConnection();

		Status status = twitter.updateStatus(mensagemTweet);
		System.out.println("Tweet postado com sucesso! [" + status.getText() + "].");

	}

	public void enviarMensagemDireta() throws TwitterException {

		Twitter twitter = new TwitterConnection().openConnection();

		DirectMessage message = twitter.sendDirectMessage("@JNarezzi", "Teste de DM do artigo do Twitter4J.");
		System.out.println("Sent: " + message.getText() + " to @" + message.getRecipientScreenName());
	}

}
