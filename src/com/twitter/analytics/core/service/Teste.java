package com.twitter.analytics.core.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Teste {

	public static void main(String[] args) {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		System.out.println(date.format(formatter));
		
		System.out.println(date.plusDays(-1).format(formatter));
		
		System.out.println(date.plusDays(-1).format(formatter));
		
		
		
		
//		System.out.println("Usu�rio: " + status.getUser().getScreenName());
//		System.out.println("Mensagem: " + status.getText());
//		System.out.println("-----------------------------------------");
//		System.out.println("Data de Cria��o: " + status.getCreatedAt());
//		System.out.println("-----------------------------------------");
//		System.out.println("N�mero de Favoritos: " + status.getFavoriteCount());
//		System.out.println("Geolocaliza��o: " + status.getGeoLocation());
//		System.out.println("Lugar: " + status.getPlace());
//		System.out.println("Idioma: " + status.getLang());
//		System.out.println("N�mero de Retweets: " + status.getRetweetCount());
//		System.out.println("-----------------------------------------");
		
		
	}
	
}
