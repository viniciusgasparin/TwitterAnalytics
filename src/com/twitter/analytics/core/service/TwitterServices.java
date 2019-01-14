package com.twitter.analytics.core.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.twitter.analytics.core.Utils;
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

		this.enviarTweet("Projeto de conclusão da matéria JAVA executado com sucesso! Ínicio do envio de dados #java8");
		
		Twitter twitter = new TwitterConnection().openConnection();

		Query query = new Query(hashtag);
		
		LocalDateTime dataInicio = LocalDateTime.now();
		DateTimeFormatter dataIngles = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter dataPortugues = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dataInicio = dataInicio.plusDays(-6);

		QueryResult result;

		List<Tweet> listaTweets = new ArrayList();
		
		while (LocalDateTime.now().isAfter(dataInicio)) {
			
			StringBuffer sBuffer = new StringBuffer();
			
			int qtdeRetweets = 0;
			int qtdeFavoritacoes = 0;
		
			query.setSince(dataInicio.format(dataIngles));
			dataInicio = dataInicio.plusDays(1);
			query.setUntil(dataInicio.format(dataIngles));
			
			result = twitter.search(query);
		
			if (result != null && !result.getTweets().isEmpty()) {
				
				for (Status status : result.getTweets()) {

					listaTweets.add(new Tweet( status.getUser().getScreenName(), status.getText(), status.getCreatedAt() ));
					qtdeRetweets += status.getRetweetCount();
					qtdeFavoritacoes += status.getFavoriteCount();
				}
			
				System.out.println("---------------------------------------------");
				System.out.println("Tweets dia " + dataInicio.plusDays(-1).format(dataPortugues) + ": " + result.getTweets().size());
				System.out.println("Retweets dia " + dataInicio.plusDays(-1).format(dataPortugues) + ": " + qtdeRetweets);
				System.out.println("Favoritações dia " + dataInicio.plusDays(-1).format(dataPortugues) + ": " + qtdeFavoritacoes);
				System.out.println("---------------------------------------------");
				
				sBuffer.append("Tweets dia " + dataInicio.plusDays(-1).format(dataPortugues) + ": " + result.getTweets().size() + "\n");
				sBuffer.append("Retweets dia " + dataInicio.plusDays(-1).format(dataPortugues) + ": " + qtdeRetweets + "\n");
				sBuffer.append("Favoritações dia " + dataInicio.plusDays(-1).format(dataPortugues) + ": " + qtdeFavoritacoes + "\n");
				
				this.enviarTweet(sBuffer.toString());
			}			

		}
		
		this.imprimirItensOrdenados(listaTweets);

	}

	
	
	public void enviarTweet(String mensagemTweet) throws TwitterException {

		Twitter twitter = new TwitterConnection().openConnection();

		Status status = twitter.updateStatus(mensagemTweet.concat(" @michelpf"));
		System.out.println("Tweet postado com sucesso! [" + status.getText() + "].");

	}

	@SuppressWarnings("deprecation")
	public void enviarMensagemDireta() throws TwitterException {

		Twitter twitter = new TwitterConnection().openConnection();

		DirectMessage message = twitter.sendDirectMessage("@JNarezzi", "Teste de DM do artigo do Twitter4J.");
		System.out.println("Sent: " + message.getText() + " to @" + message.getRecipientScreenName());
	}

	private void imprimirItensOrdenados(List<Tweet> listaTweets) throws TwitterException{
		
		Utils.ordenarPorNome(listaTweets);
		
		System.out.println("--- Lista Ordenada por Nome ---");
		System.out.println("    Primeiro tweet: " + listaTweets.stream().findFirst().get());
		System.out.println("    ---------------------------------------------");
		
		System.out.println("    Ultimo tweet: " + listaTweets.get(listaTweets.size() - 1));
		System.out.println("    ---------------------------------------------");
		
		StringBuffer sBuffer = new StringBuffer();
		
		sBuffer.append("Lista por Nome = ");
		sBuffer.append("1º:" + listaTweets.stream().findFirst().get().getNome());
		sBuffer.append("Ultimo: " + listaTweets.get(listaTweets.size() - 1).getNome() + "\n");
		
		
		Utils.ordenarPorData(listaTweets);
		
		System.out.println("--- Lista Ordenada por Data ---");
		System.out.println("    Primeiro tweet: " + listaTweets.stream().findFirst().get());
		System.out.println("    ---------------------------------------------");
		
		System.out.println("    Ultimo tweet: " + listaTweets.get(listaTweets.size() - 1));
		System.out.println("---------------------------------------------");
		
		sBuffer.append("Lista por Data = ");
		sBuffer.append("1º:" + listaTweets.stream().findFirst().get().getDataCriacao());
		sBuffer.append("Ultimo: " + listaTweets.get(listaTweets.size() - 1).getDataCriacao() + "\n");
		
		this.enviarTweet(sBuffer.toString());
	}
	
}
