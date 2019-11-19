package br.com.poli.enumeradores;

public enum Resultado {
	
	EMPATE(0), COMVENCEDOR(1);
	
	private int valor;
	
	private Resultado(int valorResultado){
		this.valor = valorResultado;
	}
	
	public int getValor(){
		return this.valor;
	}

}
