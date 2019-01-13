package com.twitter.analytics.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.twitter.analytics.to.Tweet;

public class Utils {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void ordenarPorNome(List<Tweet> lista) {
		Collections.sort(lista, new Comparator() {	

			@Override
			public int compare(Object o1, Object o2) {
				
				return ((Tweet) o1).getNome().compareToIgnoreCase(((Tweet) o2).getNome());
			}
		});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void ordenarPorData(List<Tweet> lista) {
		Collections.sort(lista, new Comparator() {	

			@Override
			public int compare(Object o1, Object o2) {
				
				return ((Tweet) o1).getDataCriacao().compareTo(((Tweet) o2).getDataCriacao());
			}
		});
	}
	
}
