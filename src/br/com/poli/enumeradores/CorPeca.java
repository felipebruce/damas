package br.com.poli.enumeradores;

//Enumerador responsavel por conter os valores das peças, nas cores clara e escura
public enum CorPeca {

	CLARA(0), ESCURA(1);

	private int valor;

	private CorPeca(int valorCor) {

		this.valor = valorCor;
	}

	public int getValor() {
		return this.valor;
	}

}
