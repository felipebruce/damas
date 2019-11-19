package br.com.poli.dama;

//Classe responsavel por conter os dados referentes a cada jogador, como nome, idade e sua vez de jogada
public class Jogador {

	private String nome;
	private String idade;

	private boolean jogadorAutonomo;

	public Jogador(String nome, String idade) {
		this.nome = nome;
		this.idade = idade;

	}

	public Jogador() {
	}

	public String getNome() {
		return this.nome;
	}

	public String getIdade() {
		return this.idade;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public boolean getJogadorAutonomo() {
		return this.jogadorAutonomo;
	}

	public void setJogadorAutonomo(boolean jogadorAutonomo) {
		this.jogadorAutonomo = jogadorAutonomo;
	}

}