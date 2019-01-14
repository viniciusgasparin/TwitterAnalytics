package com.twitter.analytics.to;

import java.util.Date;

public class Tweet {

	private String nome;
	

	private String mensagem;
	

	private Date dataCriacao;


	public Tweet(String nome, String mensagem, Date dataCriacao) {

		this.nome = nome;
		this.mensagem = mensagem;
		this.dataCriacao = dataCriacao;
	}

	@Override
	public String toString() {
		return "Tweet [Nome = " + nome + ", Data de Criação=" + dataCriacao + "]";
	}
	

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getMensagem() {
		return mensagem;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


	public Date getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}



	

	
	
	
}
