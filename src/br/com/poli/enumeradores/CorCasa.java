package br.com.poli.enumeradores;

//Enumerador responsavel por conter os valores das cores das casas, sendo branco e preto
public enum CorCasa {
	BRANCO(0), PRETO(1);

	private int valor;

	private CorCasa(int valorCor) {
		this.valor = valorCor;
	}

	public int getValor() {
		return this.valor;
	}

}
