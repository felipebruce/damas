package br.com.poli.interfaces;

import br.com.poli.dama.Jogo;
import br.com.poli.dama.Jogador;

public interface AutoPlayer {

	public boolean jogarAuto(Jogo jogo);
	public Jogador vencedor();
	
}
