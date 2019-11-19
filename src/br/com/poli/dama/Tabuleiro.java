package br.com.poli.dama;

import br.com.poli.enumeradores.*;

//Classe respons�vel por conter os dados referentes as jogadas e as pe�as
public class Tabuleiro {

	private Casa[][] grid = new Casa[8][8];

	public Casa[][] getGrid() {
		return this.grid;
	}

	// M�todo usado para gerar um tabuleiro na condi��es iniciais de jogo
	protected void gerarTabuleiro(Jogador jogador1, Jogador jogador2) {

		Peca a[] = new Peca[12];
		Peca b[] = new Peca[12];

		int k = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if ((i + j) % 2 == 0) {
					grid[i][j] = new Casa(CorCasa.BRANCO);
				} else {
					grid[i][j] = new Casa(CorCasa.PRETO);
					if (i >= 0 && i < 3) {
						a[k] = new Peca(CorPeca.ESCURA, jogador2, false);
						grid[i][j].setPeca(a[k]);
						grid[i][j].setOcupada(true);

					} else if (i >= 5 && i < grid.length) {
						b[k] = new Peca(CorPeca.CLARA, jogador1, false);
						grid[i][j].setPeca(b[k]);
						grid[i][j].setOcupada(true);

					} else {
						grid[i][j].setOcupada(false);
					}

				}
			}
			k++;
		}

	}

	// M�todo usado para verificar se as posi��es das pe�as n�o ultrapasssam o
	// limite
	// do tabuleiro assim como se a dist�ncia entre a origem e o destino �
	// coerente

	protected int verifica(int origemX, int origemY, int destinoX, int destinoY) {

		if ((origemX - destinoX == 1 || origemX - destinoX == -1)
				&& ((origemY - destinoY == 1 || origemY - destinoY == -1))) {
			if ((grid[origemX][origemY].getPeca().getCorPeca().getValor() == 0) && (destinoX - origemX < 0)
					|| (grid[origemX][origemY].getPeca().getCorPeca().getValor() == 1) && destinoX - origemX > 0) {
				return 1;
			}
		} else if ((origemX - destinoX == 2 || origemX - destinoX == -2)
				&& ((origemY - destinoY == 2 || origemY - destinoY == -2))) {
			return 2;

		}

		return 0;
	}

	// M�todo que executa o movimento das pe�as utilizando o m�todo "verifica"
	protected void executarMovimento(int origemX, int origemY, int destinoX, int destinoY) {

		if (grid[origemX][origemY].getOcupada() && !grid[destinoX][destinoY].getOcupada()) {
			if (!grid[origemX][origemY].getPeca().getDama()) {
				if (verifica(origemX, origemY, destinoX, destinoY) == 1) {

					Peca peca = grid[origemX][origemY].getPeca();
					grid[destinoX][destinoY].setPeca(peca);
					grid[destinoX][destinoY].setOcupada(true);
					grid[origemX][origemY].setOcupada(false);
					grid[origemX][origemY].setPeca(null);

				}
			} else if (grid[origemX][origemY].getPeca().getDama()) {
				if ((Math.abs(origemX - destinoX)) == (Math.abs(origemY - destinoY))) {

					if (destinoX - origemX < 0 && destinoY - origemY < 0) {

						Peca peca = grid[origemX][origemY].getPeca();
						grid[destinoX][destinoY].setPeca(peca);
						grid[destinoX][destinoY].setOcupada(true);
						grid[origemX][origemY].setOcupada(false);
						grid[origemX][origemY].setPeca(null);

					}

					if (destinoX - origemX < 0 && destinoY - origemY > 0) {

						Peca peca = grid[origemX][origemY].getPeca();
						grid[destinoX][destinoY].setPeca(peca);
						grid[destinoX][destinoY].setOcupada(true);
						grid[origemX][origemY].setOcupada(false);
						grid[origemX][origemY].setPeca(null);

					}

					if (destinoX - origemX > 0 && destinoY - origemY < 0) {

						Peca peca = grid[origemX][origemY].getPeca();
						grid[destinoX][destinoY].setPeca(peca);
						grid[destinoX][destinoY].setOcupada(true);
						grid[origemX][origemY].setOcupada(false);
						grid[origemX][origemY].setPeca(null);

					}

					if (destinoX - origemX > 0 && destinoY - origemY > 0) {

						Peca peca = grid[origemX][origemY].getPeca();
						grid[destinoX][destinoY].setPeca(peca);
						grid[destinoX][destinoY].setOcupada(true);
						grid[origemX][origemY].setOcupada(false);
						grid[origemX][origemY].setPeca(null);

					}
				}
			}
		}
	}

	protected void imprimeTabuleiro() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[i][j].getOcupada()) {
					if (grid[i][j].getPeca().getDama() && grid[i][j].getPeca().getCorPeca().getValor() == 0) {
						System.out.print("C ");

					} else if (grid[i][j].getPeca().getDama() && grid[i][j].getPeca().getCorPeca().getValor() == 1) {
						System.out.print("E ");
					} else if (grid[i][j].getPeca().getCorPeca().getValor() == 0) {
						System.out.print("c ");

					} else if (grid[i][j].getPeca().getCorPeca().getValor() == 1) {
						System.out.print("e ");
					}
				} else {
					System.out.print("- ");
				}
			}
			System.out.println();
		}

	}

}