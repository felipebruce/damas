package br.com.poli.dama;

import br.com.poli.enumeradores.*;
import br.com.poli.exceptions.*;
import java.util.Date;

public class Jogo {

	private Jogador jogador1;
	private Jogador jogador2;
	private Jogador vencedor;
	private Tabuleiro tabuleiro;
	private Resultado resultado;
	private Date tempo;
	private int contadorJogadas = 0;
	private int vezAtual = 0;
	private int linha;
	private int coluna;
	public boolean combo;

	public Jogador jogadorAtual() {
		if (this.vezAtual == 0) {
			return this.jogador1;
		} else
			return this.jogador2;
	}

	public Jogo() {
	}

	public Tabuleiro getTabuleiro() {
		return this.tabuleiro;
	}

	public int getContadorJogadas() {
		return this.contadorJogadas;
	}

	public void setContadorJogadas(int c) {
		this.contadorJogadas = c;
	}

	public Jogador getJogador1() {
		return this.jogador1;
	}

	public Jogador getJogador2() {
		return this.jogador2;
	}

	// ordena a vez dos jogadores
	public void setVezAtual() {
		if (this.vezAtual == 0) {
			this.vezAtual = 1;
		} else if (this.vezAtual == 1) {
			this.vezAtual = 0;
		}
	}

	public int getVezAtual() {
		return this.vezAtual;
	}

	public Resultado getResultado() {
		return this.resultado;
	}

	public void setResultado(Resultado r) {
		this.resultado = r;
	}

	public Jogador getVencedor() {
		return this.vencedor;
	}

	public void setVencedor(Jogador v) {
		this.vencedor = v;
	}

	public Date getTempo() {
		return this.tempo;
	}

	public void iniciarPartida(String nome1, String idade1, String nome2, String idade2) {

		this.jogador1 = new Jogador(nome1, idade1);

		this.jogador2 = new Jogador(nome2, idade2);

		this.tabuleiro = new Tabuleiro();
		this.tabuleiro.gerarTabuleiro(jogador1, jogador2);

		System.out.println(this.jogador1.getNome() + ", inicie o jogo!");
		this.tabuleiro.imprimeTabuleiro();

	}

	private void combar(int origemX, int origemY, int destinoX, int destinoY) throws CapturaObrigatoriaException {
		if (linha == origemX && coluna == origemY) {
			if (!tabuleiro.getGrid()[origemX][origemY].getPeca().getDama()) {
				if (verificarExistenciaPeca(origemX, origemY)
						&& this.tabuleiro.verifica(origemX, origemY, destinoX, destinoY) == 2) {

					capturar(origemX, origemY, destinoX, destinoY);
					this.contadorJogadas = 0;

					if (verificarExistenciaPeca(destinoX, destinoY)) {
						combo = true;
						linha = destinoX;
						coluna = destinoY;
					}

					else if (!verificarExistenciaPeca(destinoX, destinoY)) {

						combo = false;
						transformarDama();
						setVezAtual();

					}

				} else {
					throw new CapturaObrigatoriaException("Captura Obrigatória!");

				}
			} else if (tabuleiro.getGrid()[origemX][origemY].getPeca().getDama()) {
				if (intencaoCapturaDama(origemX, origemY, destinoX, destinoY) == true) {

					capturar(origemX, origemY, destinoX, destinoY);
					this.contadorJogadas = 0;

					if (verificaCapturaObrigatoriaDama(destinoX, destinoY)) {
						combo = true;
						linha = destinoX;
						coluna = destinoY;
					} else if (!verificaCapturaObrigatoriaDama(destinoX, destinoY)) {
						combo = false;
						setVezAtual();
					}
				} else {
					throw new CapturaObrigatoriaException("Captura Obrigatória!");

				}

			}
		} else {
			throw new CapturaObrigatoriaException("Captura Obrigatória!");
		}
		if ((!tabuleiro.getGrid()[origemX][origemY].getOcupada())) {

			tabuleiro.imprimeTabuleiro();

		}
	}

	public void jogar(int origemX, int origemY, int destinoX, int destinoY)
			throws MovimentoInvalidoException, CapturaObrigatoriaException {

		if (isFimDeJogo() == false) {

			if (combo == true) {

				combar(origemX, origemY, destinoX, destinoY);

			} else {
				if (!verificaLimiteTabuleiro(origemX, origemY, destinoX, destinoY)) {
					throw new MovimentoInvalidoException("Movimento Inválido!");
				} else {
					if (tabuleiro.getGrid()[origemX][origemY].getOcupada()) {
						if (tabuleiro.getGrid()[origemX][origemY].getPeca().getCorPeca().getValor() == this.vezAtual) {

							if (intervaloValido(origemX, origemY, destinoX, destinoY) == true) {
								if (tabuleiro.getGrid()[destinoX][destinoY].getOcupada()) {

									throw new MovimentoInvalidoException("Casa ocupada!");

								} else {
									if (isCapturaObrigatoria() == true) {
										if (tabuleiro.getGrid()[origemX][origemY].getPeca().getDama() == false) {

											if (this.tabuleiro.verifica(origemX, origemY, destinoX, destinoY) == 2
													&& verificarExistenciaPeca(origemX, origemY)) {

												capturar(origemX, origemY, destinoX, destinoY);
												this.contadorJogadas = 0;

												if (verificarExistenciaPeca(destinoX, destinoY)) {
													combo = true;
													linha = destinoX;
													coluna = destinoY;

												}

												else if (!verificarExistenciaPeca(destinoX, destinoY)) {
													combo = false;
													transformarDama();

													setVezAtual();
												}

											} else {
												throw new CapturaObrigatoriaException("Captura Obrigatória!");
											}
										} else if (tabuleiro.getGrid()[origemX][origemY].getPeca().getDama() == true) {
											if (intencaoCapturaDama(origemX, origemY, destinoX, destinoY) == true) {
												capturar(origemX, origemY, destinoX, destinoY);
												this.contadorJogadas = 0;

												if (verificaCapturaObrigatoriaDama(destinoX, destinoY)) {
													combo = true;
													linha = destinoX;
													coluna = destinoY;

												} else if (!verificaCapturaObrigatoriaDama(destinoX, destinoY)) {
													combo = false;
													setVezAtual();
												}
											} else {
												throw new CapturaObrigatoriaException("Captura Obrigatória!");
											}

										}

									} else if (isCapturaObrigatoria() == false) {

										if ((tabuleiro.getGrid()[origemX][origemY].getOcupada()
												&& tabuleiro.getGrid()[origemX][origemY].getPeca().getDama()
												&& !tabuleiro.getGrid()[destinoX][destinoY].getOcupada()
												&& tabuleiro.getGrid()[origemX][origemY].getPeca().getCorPeca()
														.getValor() == this.vezAtual)) {
											if (destinoX - origemX < 0 && destinoY - origemY < 0) {
												if ((existePecaEsqCima(origemX, origemY, destinoX,
														destinoY) == false)) {

													this.tabuleiro.executarMovimento(origemX, origemY, destinoX,
															destinoY);

												}
											}
											if (destinoX - origemX < 0 && destinoY - origemY > 0) {

												if (existePecaDirCima(origemX, origemY, destinoX, destinoY) == false) {

													tabuleiro.executarMovimento(origemX, origemY, destinoX, destinoY);
												}
											}
											if (destinoX - origemX > 0 && destinoY - origemY < 0) {
												if (existePecaDirBaixo(origemX, origemY, destinoX, destinoY) == false) {

													tabuleiro.executarMovimento(origemX, origemY, destinoX, destinoY);
												}

											}

											if (destinoX - origemX > 0 && destinoY - origemY > 0) {
												if (existePecaEsqBaixo(origemX, origemY, destinoX, destinoY) == false) {

													tabuleiro.executarMovimento(origemX, origemY, destinoX, destinoY);
												}

											}

											if ((!tabuleiro.getGrid()[origemX][origemY].getOcupada())) {

												setVezAtual();
												setContadorJogadas(getContadorJogadas() + 1);

											}

										}

										if (tabuleiro.getGrid()[origemX][origemY].getOcupada()
												&& tabuleiro.getGrid()[origemX][origemY].getPeca().getDama() == false) {

											if ((tabuleiro.verifica(origemX, origemY, destinoX, destinoY) == 1)) {
												tabuleiro.executarMovimento(origemX, origemY, destinoX, destinoY);
												transformarDama();
												setVezAtual();
												setContadorJogadas(getContadorJogadas() + 1);

											}

										}

									}
									if ((!tabuleiro.getGrid()[origemX][origemY].getOcupada())) {

										tabuleiro.imprimeTabuleiro();

									}

									if (isFimDeJogo() == true) {
										if (this.getContadorJogadas() == 20) {
											System.out.println("Empate!");
										} else
											System.out.println("Vencedor:" + this.getVencedor().getNome());
									}

								}
							} else {
								throw new MovimentoInvalidoException("Movimento Inválido!");
							}

						} else {
							throw new MovimentoInvalidoException("Selecione sua peça!");
						}

					}

				}
			}

		} else if (isFimDeJogo()) {
			System.out.println("Fim de jogo!");
		}
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public boolean isCombo() {
		return combo;
	}

	public void setCombo(boolean combo) {
		this.combo = combo;
	}

	protected void setJogador1(Jogador jogador1) {
		this.jogador1 = jogador1;
	}

	protected void setJogador2(Jogador jogador2) {
		this.jogador2 = jogador2;
	}

	protected void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	protected void setTempo(Date tempo) {
		this.tempo = tempo;
	}

	protected void setVezAtual(int vezAtual) {
		this.vezAtual = vezAtual;
	}

	private boolean verificaLimiteTabuleiro(int origemX, int origemY, int destinoX, int destinoY) {

		boolean r = false;

		if (origemX >= 0 && origemX < this.tabuleiro.getGrid().length && origemY >= 0
				&& origemY < this.tabuleiro.getGrid().length && destinoX >= 0
				&& destinoX < this.tabuleiro.getGrid().length && destinoX >= 0
				&& destinoX < this.tabuleiro.getGrid().length) {
			r = true;
		}

		return r;

	}

	// de acordo com as condições, permite que uma peca capture outra
	protected void capturar(int origemX, int origemY, int destinoX, int destinoY) {

		if (this.tabuleiro.getGrid()[origemX][origemY].getOcupada()
				&& !this.tabuleiro.getGrid()[destinoX][destinoY].getOcupada()
				&& verificarExistenciaPeca(origemX, origemY) == true) {
			if (this.tabuleiro.getGrid()[origemX][origemY].getPeca().getDama() == false) {

				if ((destinoX - origemX) < 0 && (destinoY - origemY > 0)
						&& this.tabuleiro.getGrid()[destinoX + 1][destinoY - 1].getPeca().getCorPeca()
								.getValor() != this.vezAtual) {
					Peca peca = tabuleiro.getGrid()[origemX][origemY].getPeca();
					tabuleiro.getGrid()[destinoX][destinoY].setPeca(peca);
					tabuleiro.getGrid()[destinoX][destinoY].setOcupada(true);
					tabuleiro.getGrid()[destinoX + 1][destinoY - 1].setPeca(null);
					tabuleiro.getGrid()[destinoX + 1][destinoY - 1].setOcupada(false);
					tabuleiro.getGrid()[origemX][origemY].setOcupada(false);
					tabuleiro.getGrid()[origemX][origemY].setPeca(null);
				}
				if ((destinoX - origemX) < 0 && (destinoY - origemY < 0)
						&& this.tabuleiro.getGrid()[destinoX + 1][destinoY + 1].getPeca().getCorPeca()
								.getValor() != this.vezAtual) {
					Peca peca = tabuleiro.getGrid()[origemX][origemY].getPeca();
					tabuleiro.getGrid()[destinoX][destinoY].setPeca(peca);
					tabuleiro.getGrid()[destinoX][destinoY].setOcupada(true);
					tabuleiro.getGrid()[destinoX + 1][destinoY + 1].setPeca(null);
					tabuleiro.getGrid()[destinoX + 1][destinoY + 1].setOcupada(false);
					tabuleiro.getGrid()[origemX][origemY].setOcupada(false);
					tabuleiro.getGrid()[origemX][origemY].setPeca(null);
				}
				if ((destinoX - origemX) > 0 && (destinoY - origemY < 0)
						&& this.tabuleiro.getGrid()[destinoX - 1][destinoY + 1].getPeca().getCorPeca()
								.getValor() != this.vezAtual) {
					Peca peca = tabuleiro.getGrid()[origemX][origemY].getPeca();
					tabuleiro.getGrid()[destinoX][destinoY].setPeca(peca);
					tabuleiro.getGrid()[destinoX][destinoY].setOcupada(true);
					tabuleiro.getGrid()[destinoX - 1][destinoY + 1].setPeca(null);
					tabuleiro.getGrid()[destinoX - 1][destinoY + 1].setOcupada(false);
					tabuleiro.getGrid()[origemX][origemY].setOcupada(false);
					tabuleiro.getGrid()[origemX][origemY].setPeca(null);
				}
				if ((destinoX - origemX) > 0 && (destinoY - origemY > 0)
						&& this.tabuleiro.getGrid()[destinoX - 1][destinoY - 1].getPeca().getCorPeca()
								.getValor() != this.vezAtual) {
					Peca peca = tabuleiro.getGrid()[origemX][origemY].getPeca();
					tabuleiro.getGrid()[destinoX][destinoY].setPeca(peca);
					tabuleiro.getGrid()[destinoX][destinoY].setOcupada(true);
					tabuleiro.getGrid()[destinoX - 1][destinoY - 1].setPeca(null);
					tabuleiro.getGrid()[destinoX - 1][destinoY - 1].setOcupada(false);
					tabuleiro.getGrid()[origemX][origemY].setOcupada(false);
					tabuleiro.getGrid()[origemX][origemY].setPeca(null);
				}

			}

		} else if (this.tabuleiro.getGrid()[origemX][origemY].getOcupada()
				&& !this.tabuleiro.getGrid()[destinoX][destinoY].getOcupada()
				&& this.tabuleiro.getGrid()[origemX][origemY].getPeca().getDama() == true) {

			capturaDama(origemX, origemY, destinoX, destinoY);

		}

	}

	private void capturaDamaEsqBaixo(int origemX, int origemY, int destinoX, int destinoY) {
		if (this.tabuleiro.getGrid()[origemX][origemY].getOcupada()
				&& !this.tabuleiro.getGrid()[destinoX][destinoY].getOcupada()
				&& this.tabuleiro.getGrid()[origemX][origemY].getPeca().getDama() == true) {

			int i = 1;
			while (i < Math.abs(destinoX - origemX)) {
				if (origemX + i < this.tabuleiro.getGrid().length && origemY + i < this.tabuleiro.getGrid().length) {
					if (existePecaEsqBaixo(origemX, origemY, destinoX, destinoY)) {
						tabuleiro.getGrid()[origemX + i][origemY + i].setOcupada(false);
						tabuleiro.getGrid()[origemX + i][origemY + i].setPeca(null);

					}
				}
				i++;
			}
			mudarPosicaoDama(origemX, origemY, destinoX, destinoY);

		}

	}

	private void capturaDamaDirCima(int origemX, int origemY, int destinoX, int destinoY) {
		if (this.tabuleiro.getGrid()[origemX][origemY].getOcupada()
				&& !this.tabuleiro.getGrid()[destinoX][destinoY].getOcupada()
				&& this.tabuleiro.getGrid()[origemX][origemY].getPeca().getDama() == true) {

			int i = 1;
			while (i < Math.abs(destinoX - origemX)) {
				if (origemX - i > 0 && origemY + i < this.tabuleiro.getGrid().length) {
					if (existePecaDirCima(origemX, origemY, destinoX, destinoY)) {

						tabuleiro.getGrid()[origemX - i][origemY + i].setOcupada(false);
						tabuleiro.getGrid()[origemX - i][origemY + i].setPeca(null);

					}
				}

				i++;
			}
			mudarPosicaoDama(origemX, origemY, destinoX, destinoY);

		}

	}

	private void capturaDamaDirBaixo(int origemX, int origemY, int destinoX, int destinoY) {
		if (this.tabuleiro.getGrid()[origemX][origemY].getOcupada()
				&& !this.tabuleiro.getGrid()[destinoX][destinoY].getOcupada()
				&& this.tabuleiro.getGrid()[origemX][origemY].getPeca().getDama() == true) {

			int i = 1;
			while (i < Math.abs(destinoX - origemX)) {
				if (origemX + i < this.tabuleiro.getGrid().length && origemY - i > 0) {
					if (existePecaDirBaixo(origemX, origemY, destinoX, destinoY)) {

						tabuleiro.getGrid()[origemX + i][origemY - i].setOcupada(false);
						tabuleiro.getGrid()[origemX + i][origemY - i].setPeca(null);

					}

					i++;
				}
			}

			mudarPosicaoDama(origemX, origemY, destinoX, destinoY);

		}

	}

	private void capturaDamaEsqCima(int origemX, int origemY, int destinoX, int destinoY) {
		if (this.tabuleiro.getGrid()[origemX][origemY].getOcupada()
				&& !this.tabuleiro.getGrid()[destinoX][destinoY].getOcupada()
				&& this.tabuleiro.getGrid()[origemX][origemY].getPeca().getDama() == true) {

			int i = 1;
			while (i < Math.abs(destinoX - origemX)) {
				if (origemX - i > 0 && origemY - i > 0) {
					if (existePecaEsqCima(origemX, origemY, destinoX, destinoY)) {

						tabuleiro.getGrid()[origemX - i][origemY - i].setOcupada(false);
						tabuleiro.getGrid()[origemX - i][origemY - i].setPeca(null);

					}
				}
				i++;
			}
			mudarPosicaoDama(origemX, origemY, destinoX, destinoY);

		}

	}

	private void capturaDama(int origemX, int origemY, int destinoX, int destinoY) {

		if (destinoX - origemX > 0 && destinoY - origemY > 0) {
			capturaDamaEsqBaixo(origemX, origemY, destinoX, destinoY);
		}
		if (destinoX - origemX < 0 && destinoY - origemY < 0) {

			capturaDamaEsqCima(origemX, origemY, destinoX, destinoY);
		}

		if (destinoX - origemX < 0 && destinoY - origemY > 0) {
			capturaDamaDirCima(origemX, origemY, destinoX, destinoY);

		}
		if (destinoX - origemX > 0 && destinoY - origemY < 0) {
			capturaDamaDirBaixo(origemX, origemY, destinoX, destinoY);
		}

	}

	private boolean isCapturaObrigatoria() {
		boolean r = false;

		for (int i = 0; i < this.tabuleiro.getGrid().length; i++) {
			for (int j = 0; j < this.tabuleiro.getGrid().length; j++) {
				if (this.tabuleiro.getGrid()[i][j].getOcupada()
						&& this.tabuleiro.getGrid()[i][j].getPeca().getDama() == false) {

					if (this.tabuleiro.getGrid()[i][j].getPeca().getCorPeca().getValor() == this.vezAtual) {

						if (i + 2 < this.tabuleiro.getGrid().length && j + 2 < this.tabuleiro.getGrid().length) {

							if (this.tabuleiro.getGrid()[i + 1][j + 1].getOcupada()
									&& this.tabuleiro.getGrid()[i + 1][j + 1].getPeca().getCorPeca()
											.getValor() != this.vezAtual) {
								if (!this.tabuleiro.getGrid()[i + 2][j + 2].getOcupada()) {
									r = true;

								}

							}
						}
						if (i + 2 < this.tabuleiro.getGrid().length && j - 2 >= 0) {

							if (this.tabuleiro.getGrid()[i + 1][j - 1].getOcupada()
									&& this.tabuleiro.getGrid()[i + 1][j - 1].getPeca().getCorPeca()
											.getValor() != this.vezAtual) {

								if (!this.tabuleiro.getGrid()[i + 2][j - 2].getOcupada()) {

									r = true;

								}

							}
						}
						if (i - 2 >= 0 && j + 2 < this.tabuleiro.getGrid().length) {

							if (this.tabuleiro.getGrid()[i - 1][j + 1].getOcupada()
									&& this.tabuleiro.getGrid()[i - 1][j + 1].getPeca().getCorPeca()
											.getValor() != this.vezAtual) {
								if (!this.tabuleiro.getGrid()[i - 2][j + 2].getOcupada()) {
									r = true;

								}

							}
						}
						if (i - 2 >= 0 && j - 2 >= 0) {

							if (this.tabuleiro.getGrid()[i - 1][j - 1].getOcupada()
									&& this.tabuleiro.getGrid()[i - 1][j - 1].getPeca().getCorPeca()
											.getValor() != this.vezAtual) {
								if (!this.tabuleiro.getGrid()[i - 2][j - 2].getOcupada()) {
									r = true;

								}

							}
						}

					}

				} else if (this.tabuleiro.getGrid()[i][j].getOcupada()
						&& this.tabuleiro.getGrid()[i][j].getPeca().getDama() == true) {
					if (verificaCapturaObrigatoriaDama(i, j) == true) {

						r = true;

					}
				}

			}
		}
		return r;
	}

	private boolean verificaCapturaObrigatoriaDama(int origemX, int origemY) {
		boolean r = false;
		int i = 1;
		int a = 0;
		if (this.tabuleiro.getGrid()[origemX][origemY].getPeca().getCorPeca().getValor() == this.vezAtual) {
			while (r == false) {

				if (a == 0) {

					if (origemX - i - 1 >= 0 && origemY + i + 1 < tabuleiro.getGrid().length) {
						if (!tabuleiro.getGrid()[origemX - i][origemY + i].getOcupada()) {
							i++;
						} else if (this.tabuleiro.getGrid()[origemX - i][origemY + i].getOcupada()) {
							if (this.tabuleiro.getGrid()[origemX - i][origemY + i].getPeca().getCorPeca()
									.getValor() != this.vezAtual
									&& !this.tabuleiro.getGrid()[origemX - i - 1][origemY + i + 1].getOcupada()) {
								r = true;

							} else if (this.tabuleiro.getGrid()[origemX - i - 1][origemY + i + 1].getOcupada()) {
								a = 1;
								i = 1;

							} else if (this.tabuleiro.getGrid()[origemX - i][origemY + i].getPeca().getCorPeca()
									.getValor() == this.vezAtual) {
								a = 1;
								i = 1;

							}
						}

					} else {
						a = 1;
						i = 1;

					}
				}
				if (a == 1) {

					if (origemX + i + 1 < tabuleiro.getGrid().length && origemY + i + 1 < tabuleiro.getGrid().length) {
						if (!tabuleiro.getGrid()[origemX + i][origemY + i].getOcupada()) {
							i++;

						} else if (this.tabuleiro.getGrid()[origemX + i][origemY + i].getOcupada()) {
							if (this.tabuleiro.getGrid()[origemX + i][origemY + i].getPeca().getCorPeca()
									.getValor() != this.vezAtual
									&& !this.tabuleiro.getGrid()[origemX + i + 1][origemY + i + 1].getOcupada()) {
								r = true;

							} else if (this.tabuleiro.getGrid()[origemX + i + 1][origemY + i + 1].getOcupada()) {
								a = 2;
								i = 1;

							} else if (this.tabuleiro.getGrid()[origemX + i][origemY + i].getPeca().getCorPeca()
									.getValor() == this.vezAtual) {
								a = 2;
								i = 1;

							}
						}
					} else {
						a = 2;
						i = 1;

					}

				}
				if (a == 2) {

					if (origemX + i + 1 < tabuleiro.getGrid().length && origemY - i - 1 >= 0) {
						if (!tabuleiro.getGrid()[origemX + i][origemY - i].getOcupada()) {
							i++;

						} else if (this.tabuleiro.getGrid()[origemX + i][origemY - i].getOcupada()) {
							if (this.tabuleiro.getGrid()[origemX + i][origemY - i].getPeca().getCorPeca()
									.getValor() != this.vezAtual
									&& !this.tabuleiro.getGrid()[origemX + i + 1][origemY - i - 1].getOcupada()) {
								r = true;

							} else if (this.tabuleiro.getGrid()[origemX + i + 1][origemY - i - 1].getOcupada()) {

								a = 3;
								i = 1;

							} else if (this.tabuleiro.getGrid()[origemX + i][origemY - i].getPeca().getCorPeca()
									.getValor() == this.vezAtual) {
								a = 3;
								i = 1;

							}
						}
					} else {
						a = 3;
						i = 1;

					}

				}
				if (a == 3) {
					if (origemX - i - 1 >= 0 && origemY - i - 1 >= 0) {
						if (!tabuleiro.getGrid()[origemX - i][origemY - i].getOcupada()) {
							i++;

						} else if (this.tabuleiro.getGrid()[origemX - i][origemY - i].getOcupada()) {
							if (this.tabuleiro.getGrid()[origemX - i][origemY - i].getPeca().getCorPeca()
									.getValor() != this.vezAtual
									&& !this.tabuleiro.getGrid()[origemX - i - 1][origemY - i - 1].getOcupada()) {
								r = true;

							} else if (this.tabuleiro.getGrid()[origemX - i - 1][origemY - i - 1].getOcupada()) {
								break;
							} else if (this.tabuleiro.getGrid()[origemX - i][origemY - i].getPeca().getCorPeca()
									.getValor() == this.vezAtual) {
								break;
							}
						}
					} else {
						break;
					}
				}
			}
		}
		// System.out.println(i);
		return r;

	}

	// vefirica se existe alguma peca entre o movimento de partida e o de
	// chegada
	// durante a vez de um jogador para fazer uma captura
	private boolean verificarExistenciaPeca(int origemX, int origemY) {
		boolean r = false;
		if (this.tabuleiro.getGrid()[origemX][origemY].getPeca().getDama() == false) {

			if (origemX + 2 < this.tabuleiro.getGrid().length && origemY + 2 < this.tabuleiro.getGrid().length) {
				if (this.tabuleiro.getGrid()[origemX + 1][origemY + 1].getOcupada()) {
					if (this.tabuleiro.getGrid()[origemX + 1][origemY + 1].getPeca().getCorPeca()
							.getValor() != this.vezAtual) {
						if (!this.tabuleiro.getGrid()[origemX + 2][origemY + 2].getOcupada()) {

							r = true;
						}

					}
				}
			}
			if (origemX + 2 < this.tabuleiro.getGrid().length && origemY - 2 >= 0) {
				if (this.tabuleiro.getGrid()[origemX + 1][origemY - 1].getOcupada()) {
					if (this.tabuleiro.getGrid()[origemX + 1][origemY - 1].getPeca().getCorPeca()
							.getValor() != this.vezAtual) {
						if (!this.tabuleiro.getGrid()[origemX + 2][origemY - 2].getOcupada()) {

							r = true;
						}

					}
				}
			}
			if (origemX - 2 >= 0 && origemY + 2 < this.tabuleiro.getGrid().length) {
				if (this.tabuleiro.getGrid()[origemX - 1][origemY + 1].getOcupada()
						&& this.tabuleiro.getGrid()[origemX - 1][origemY + 1].getPeca().getCorPeca()
								.getValor() != this.vezAtual) {
					if (!this.tabuleiro.getGrid()[origemX - 2][origemY + 2].getOcupada()) {

						r = true;
					}
				}
			}
			if (origemX - 2 >= 0 && origemY - 2 >= 0) {
				if (this.tabuleiro.getGrid()[origemX - 1][origemY - 1].getOcupada()) {
					if (this.tabuleiro.getGrid()[origemX - 1][origemY - 1].getPeca().getCorPeca()
							.getValor() != this.vezAtual) {
						if (!this.tabuleiro.getGrid()[origemX - 2][origemY - 2].getOcupada()) {

							r = true;
						}
					}
				}
			}
		}

		return r;

	}

	// metodo usado para auxiliar na verificação de uma dama para se movimentar,
	// neste caso ele vefirica no sentido esquerdo e para cima se tem peça ou
	// não
	// para a dama poder se mexer

	private boolean existePecaEsqCima(int origemX, int origemY, int destinoX, int destinoY) {
		boolean r = false;
		int j = origemY - 1;
		for (int i = origemX - 1; i > destinoX; i--) {
			if (j > 0 && i > 0) {
				if (tabuleiro.getGrid()[i][j].getOcupada()) {
					r = true;
				}
				j--;

			}
		}
		return r;
	}

	// verifica a diagonal no sentido da direita e para cima se tem peca ocupada
	// entre a origem
	// da dama ate onde ela deseja ir
	private boolean existePecaDirCima(int origemX, int origemY, int destinoX, int destinoY) {
		boolean r = false;

		int j = origemY + 1;
		for (int i = origemX - 1; i > destinoX; i--) {
			if (j < this.tabuleiro.getGrid().length && i > 0) {
				if (tabuleiro.getGrid()[i][j].getOcupada()) {
					r = true;
				}
				j++;

			}
		}
		return r;
	}

	// verifica a diagonal no sentido da direita e para baixo se existe alguma
	// peca
	// entre
	// origem e destino
	private boolean existePecaDirBaixo(int origemX, int origemY, int destinoX, int destinoY) {
		boolean r = false;
		int j = origemY - 1;
		for (int i = origemX + 1; i < destinoX; i++) {
			if (j > 0 && i < this.tabuleiro.getGrid().length) {
				if (tabuleiro.getGrid()[i][j].getOcupada()) {
					r = true;
				}
				j--;
			}
		}
		return r;
	}

	// verifica a diagonal no sentido esquerda e para baixo entre onde a dama
	// deseja
	// ir se tem peça
	private boolean existePecaEsqBaixo(int origemX, int origemY, int destinoX, int destinoY) {
		boolean r = false;
		int j = origemY + 1;
		for (int i = origemX + 1; i < destinoX; i++) {
			if (j < this.tabuleiro.getGrid().length && i < this.tabuleiro.getGrid().length) {
				if (tabuleiro.getGrid()[i][j].getOcupada()) {
					r = true;
				}
				j++;
			}
		}
		return r;

	}

	// verifica se tem uma peça de uma cor chegou no lado oposto para
	// transformar em
	// uma dama
	private void transformarDama() {

		for (int j = 0; j < tabuleiro.getGrid().length; j++) {
			if (tabuleiro.getGrid()[0][j].getOcupada() && tabuleiro.getGrid()[0][j].getPeca().getDama() == false
					&& tabuleiro.getGrid()[0][j].getPeca().getCorPeca().getValor() == 0) {
				Dama d = new Dama(CorPeca.CLARA, jogador1, true);
				tabuleiro.getGrid()[0][j].setPeca(null);
				tabuleiro.getGrid()[0][j].setPeca(d);

			}
			if (tabuleiro.getGrid()[tabuleiro.getGrid().length - 1][j].getOcupada()
					&& tabuleiro.getGrid()[tabuleiro.getGrid().length - 1][j].getPeca().getDama() == false
					&& tabuleiro.getGrid()[tabuleiro.getGrid().length - 1][j].getPeca().getCorPeca().getValor() == 1) {

				Dama d = new Dama(CorPeca.ESCURA, jogador2, true);
				tabuleiro.getGrid()[tabuleiro.getGrid().length - 1][j].setPeca(null);
				tabuleiro.getGrid()[tabuleiro.getGrid().length - 1][j].setPeca(d);

			}

		}

	}

	// muda a posiçao da dama em relação ao seu destino
	private void mudarPosicaoDama(int origemX, int origemY, int destinoX, int destinoY) {

		Peca peca = tabuleiro.getGrid()[origemX][origemY].getPeca();
		tabuleiro.getGrid()[destinoX][destinoY].setPeca(peca);
		tabuleiro.getGrid()[destinoX][destinoY].setOcupada(true);
		tabuleiro.getGrid()[origemX][origemY].setOcupada(false);
		tabuleiro.getGrid()[origemX][origemY].setPeca(null);
	}

	// metodo que movimenta a dama de acordo com suas diagonais, alem de fazer a
	// captura se necessario

	public boolean isFimDeJogo() {

		boolean fimJogo = false;

		if (quantPecaClara() == 0) {
			this.setResultado(Resultado.COMVENCEDOR);
			this.setVencedor(jogador2);
			fimJogo = true;
		} else if (quantPecaEscura() == 0) {
			this.setResultado(Resultado.COMVENCEDOR);
			this.setVencedor(jogador1);
			fimJogo = true;
		} else if (quantPecaClara() != 0 && quantPecaEscura() != 0) {
			if (this.getContadorJogadas() == 20) {
				this.setResultado(Resultado.EMPATE);
				fimJogo = true;
			}
		}

		return fimJogo;

	}

	private int verificaPecaDirBaixo(int origemX, int origemY, int destinoX, int destinoY) {
		int r = -1;
		int a = 0;
		int b = 0;

		int j = origemY - 1;
		for (int i = origemX + 1; i < destinoX; i++) {
			if (j > 0 && i < this.tabuleiro.getGrid().length) {
				if (tabuleiro.getGrid()[i][j].getOcupada()) {
					a++;

					if (tabuleiro.getGrid()[i][j].getPeca().getCorPeca().getValor() != this.vezAtual) {
						b++;
					}

				}
				j--;
			}
		}
		if (a == 1 && b == 1) {
			r = 1;
		}
		return r;
	}

	private int verificaPecaEsqCima(int origemX, int origemY, int destinoX, int destinoY) {
		int r = -1;
		int a = 0;
		int b = 0;

		int j = origemY - 1;
		for (int i = origemX - 1; i > destinoX; i--) {
			if (j > 0 && i > 0) {
				if (tabuleiro.getGrid()[i][j].getOcupada()) {
					a++;
					if (tabuleiro.getGrid()[i][j].getPeca().getCorPeca().getValor() != this.vezAtual) {
						b++;
					}

				}
				j--;

			}
		}
		if (a == 1 && b == 1) {
			r = 1;
		}
		return r;
	}

	// verifica a diagonal no sentido da direita e para cima se tem peca ocupada
	// entre a origem
	// da dama ate onde ela deseja ir
	private int verificaPecaDirCima(int origemX, int origemY, int destinoX, int destinoY) {
		int r = -1;
		int a = 0;
		int b = 0;

		int j = origemY + 1;
		for (int i = origemX - 1; i > destinoX; i--) {
			if (j < this.tabuleiro.getGrid().length && i > 0) {
				if (tabuleiro.getGrid()[i][j].getOcupada()) {
					a++;
					if (tabuleiro.getGrid()[i][j].getPeca().getCorPeca().getValor() != this.vezAtual) {
						b++;
					}

				}
				j++;

			}
		}
		if (a == 1 && b == 1) {
			r = 1;
		}
		return r;
	}

	private int verificaPecaEsqBaixo(int origemX, int origemY, int destinoX, int destinoY) {
		int r = -1;
		int a = 0;
		int b = 0;

		int j = origemY + 1;
		for (int i = origemX + 1; i < destinoX; i++) {
			if (j < this.tabuleiro.getGrid().length && i < this.tabuleiro.getGrid().length) {
				if (tabuleiro.getGrid()[i][j].getOcupada()) {
					a++;
					if (tabuleiro.getGrid()[i][j].getPeca().getCorPeca().getValor() != this.vezAtual) {
						b++;
					}

				}
				j++;
			}
		}

		if (a == 1 && b == 1) {
			r = 1;
		}
		return r;

	}

	private boolean intencaoCapturaDama(int origemX, int origemY, int destinoX, int destinoY) {
		boolean r = false;

		if (destinoX - origemX > 0 && destinoY - origemY < 0) {
			if (verificaPecaDirBaixo(origemX, origemY, destinoX, destinoY) == 1) {
				r = true;
			}
		}
		if (destinoX - origemX > 0 && destinoY - origemY > 0) {
			if (verificaPecaEsqBaixo(origemX, origemY, destinoX, destinoY) == 1) {
				r = true;
			}
		}
		if (destinoX - origemX < 0 && destinoY - origemY > 0) {
			if (verificaPecaDirCima(origemX, origemY, destinoX, destinoY) == 1) {
				r = true;
			}
		}
		if (destinoX - origemX < 0 && destinoY - origemY < 0) {
			if (verificaPecaEsqCima(origemX, origemY, destinoX, destinoY) == 1) {
				r = true;
			}
		}

		return r;
	}

	private boolean intervaloValido(int origemX, int origemY, int destinoX, int destinoY) {
		boolean r = false;
		if ((origemX >= 0 && origemX < tabuleiro.getGrid().length)
				&& (origemY >= 0 && origemY < tabuleiro.getGrid().length)
				&& (destinoX >= 0 && destinoX < tabuleiro.getGrid().length)
				&& (destinoY >= 0 && destinoY < tabuleiro.getGrid().length)) {
			if ((Math.abs(origemX - destinoX)) == (Math.abs(origemY - destinoY))) {
				r = true;
			}
		}
		return r;
	}

	public int quantPecaClara() {
		int quantPecaClara = 0;

		for (int i = 0; i < tabuleiro.getGrid().length; i++) {
			for (int j = 0; j < tabuleiro.getGrid().length; j++) {
				if (tabuleiro.getGrid()[i][j].getOcupada()) {
					if (tabuleiro.getGrid()[i][j].getPeca().getCorPeca().equals(CorPeca.CLARA)) {
						quantPecaClara++;

					}
				}
			}
		}

		return quantPecaClara;
	}

	public int quantPecaEscura() {
		int quantPecaEscura = 0;
		for (int i = 0; i < tabuleiro.getGrid().length; i++) {
			for (int j = 0; j < tabuleiro.getGrid().length; j++) {
				if (tabuleiro.getGrid()[i][j].getOcupada()) {
					if (tabuleiro.getGrid()[i][j].getPeca().getCorPeca().equals(CorPeca.ESCURA)) {
						quantPecaEscura++;

					}
				}
			}
		}
		return quantPecaEscura;
	}

}