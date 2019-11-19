package br.com.poli.dama;

import br.com.poli.enumeradores.CorPeca;

public class Dama extends Peca {

	public Dama(CorPeca cor, Jogador j, boolean dama) {
		super(cor, j, dama);

	}

	public Dama() {

	}

	public Dama(CorPeca cor, boolean dama) {
		super(cor, dama);

	}

}