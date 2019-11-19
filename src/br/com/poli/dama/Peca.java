package br.com.poli.dama;

import br.com.poli.enumeradores.CorPeca;

//Classe responsável por conter os dados referentes as pedras do jogo, como a cor pertencente a cada jogador
public class Peca {

	private CorPeca corPeca;
	private Jogador jogador;
	private boolean dama;

	public Peca(CorPeca cor, Jogador j, boolean dama) {
		this.corPeca = cor;
		this.jogador = j;
		this.dama = dama;
	}

	public Peca() {

	}

	public Peca(CorPeca cor, boolean dama) {
		this.corPeca = cor;
		this.dama = dama;
	}

	public CorPeca getCorPeca() {
		return this.corPeca;
	}

	public Jogador getJogador() {
		return this.jogador;
	}

	public boolean getDama() {
		return this.dama;
	}

	public void setDama(boolean d) {
		this.dama = d;
	}

}
