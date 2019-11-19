package br.com.poli.dama;

import br.com.poli.enumeradores.CorCasa;

//Classe responsável por conter os dados referentes as posições do jogo 
public class Casa {

	private CorCasa corCasa;
	private boolean ocupada;
	private Peca peca;

	public Casa(CorCasa c) {
		this.corCasa = c;
	}

	public Casa(CorCasa c, boolean ocupada, Peca p) {
		this.corCasa = c;
		this.ocupada = ocupada;
		this.peca = p;
	}

	public CorCasa getCorCasa() {
		return corCasa;
	}

	public boolean getOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca p) {
		this.peca = p;
	}
}
