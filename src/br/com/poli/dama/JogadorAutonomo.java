package br.com.poli.dama;

import java.util.Random;

import br.com.poli.enumeradores.CorPeca;
import br.com.poli.enumeradores.Resultado;

import br.com.poli.interfaces.AutoPlayer;

public class JogadorAutonomo extends Jogador implements AutoPlayer {

	private int origemX, origemY, destinoX, destinoY;

	public boolean jogadaValida;
	private boolean combo;
	private int linha;
	private int coluna;
	private Jogador vencedor;

	public JogadorAutonomo() {

	}

	public Jogador jogadaValida(Jogo jogo) {
		if (jogo.getVezAtual() == 0) {
			return jogo.getJogador1();
		} else
			return jogo.getJogador2();
	}

	private boolean inteligencia(int origemX, int origemY, Jogo jogo) {
		boolean r = false;

		if (origemX + 2 < jogo.getTabuleiro().getGrid().length && origemY + 2 < jogo.getTabuleiro().getGrid().length) {
			if (jogo.getTabuleiro().getGrid()[origemX + 1][origemY + 1].getOcupada()) {
				if (jogo.getTabuleiro().getGrid()[origemX + 1][origemY + 1].getPeca().getCorPeca().getValor() != jogo
						.getVezAtual()) {
					if (!jogo.getTabuleiro().getGrid()[origemX + 2][origemY + 2].getOcupada()) {

						r = true;
					}

				}
			}
		}
		if (origemX + 2 < jogo.getTabuleiro().getGrid().length && origemY - 2 >= 0) {
			if (jogo.getTabuleiro().getGrid()[origemX + 1][origemY - 1].getOcupada()) {
				if (jogo.getTabuleiro().getGrid()[origemX + 1][origemY - 1].getPeca().getCorPeca().getValor() != jogo
						.getVezAtual()) {
					if (!jogo.getTabuleiro().getGrid()[origemX + 2][origemY - 2].getOcupada()) {

						r = true;
					}

				}
			}
		}
		if (origemX - 2 >= 0 && origemY + 2 < jogo.getTabuleiro().getGrid().length) {
			if (jogo.getTabuleiro().getGrid()[origemX - 1][origemY + 1].getOcupada()
					&& jogo.getTabuleiro().getGrid()[origemX - 1][origemY + 1].getPeca().getCorPeca().getValor() != jogo
							.getVezAtual()) {
				if (!jogo.getTabuleiro().getGrid()[origemX - 2][origemY + 2].getOcupada()) {

					r = true;
				}
			}
		}
		if (origemX - 2 >= 0 && origemY - 2 >= 0) {
			if (jogo.getTabuleiro().getGrid()[origemX - 1][origemY - 1].getOcupada()) {
				if (jogo.getTabuleiro().getGrid()[origemX - 1][origemY - 1].getPeca().getCorPeca().getValor() != jogo
						.getVezAtual()) {
					if (!jogo.getTabuleiro().getGrid()[origemX - 2][origemY - 2].getOcupada()) {

						r = true;
					}
				}
			}
		}

		return r;

	}

	public boolean jogarAuto(Jogo jogo) {

		int a = 0;
		Random gerador1 = new Random();
		Random gerador2 = new Random();
		Random gerador3 = new Random();
		Random gerador4 = new Random();
		jogadaValida = false;
		if (isFimDeJogo(jogo) == false) {
			while (jogadaValida == false) {

				origemX = gerador1.nextInt(8);
				origemY = gerador2.nextInt(8);
				destinoX = gerador3.nextInt(8);
				destinoY = gerador4.nextInt(8);

				try {
					if (jogo.getTabuleiro().getGrid()[origemX][origemY].getOcupada()) {

						if (inteligencia(destinoX, destinoY, jogo)) {
							a++;
							if (a > 1000) {
								jogar(origemX, origemY, destinoX, destinoY, jogo);
							} else {
								jogadaValida = false;
							}
						} else {

							jogar(origemX, origemY, destinoX, destinoY, jogo);
						}

					}
				}

				catch (Exception e) {

				}

			}
		}

		return jogadaValida;
	}

	public Jogador vencedor() {

		return this.vencedor;
	}

	protected int verifica(int origemX, int origemY, int destinoX, int destinoY, Jogo jogo) {

		if ((origemX - destinoX == 1 || origemX - destinoX == -1)
				&& ((origemY - destinoY == 1 || origemY - destinoY == -1))) {
			if ((jogo.getTabuleiro().getGrid()[origemX][origemY].getPeca().getCorPeca().getValor() == 0)
					&& (destinoX - origemX < 0)
					|| (jogo.getTabuleiro().getGrid()[origemX][origemY].getPeca().getCorPeca().getValor() == 1)
							&& destinoX - origemX > 0) {
				return 1;
			}
		} else if ((origemX - destinoX == 2 || origemX - destinoX == -2)
				&& ((origemY - destinoY == 2 || origemY - destinoY == -2))) {
			return 2;

		}

		return 0;
	}

	private void combar(int origemX, int origemY, int destinoX, int destinoY, Jogo jogo) {
		if (linha == origemX && coluna == origemY) {
			if (!jogo.getTabuleiro().getGrid()[origemX][origemY].getPeca().getDama()) {
				if (verificarExistenciaPeca(origemX, origemY, jogo)
						&& verifica(origemX, origemY, destinoX, destinoY, jogo) == 2) {

					jogo.capturar(origemX, origemY, destinoX, destinoY);
					jogo.setContadorJogadas(0);

					if (verificarExistenciaPeca(destinoX, destinoY, jogo)) {
						this.setCombo(true);
						linha = destinoX;
						coluna = destinoY;
					}

					else if (!verificarExistenciaPeca(destinoX, destinoY, jogo)) {

						this.setCombo(false);
						transformarDama(jogo);
						jogo.setVezAtual();

					}

				} else {
					jogadaValida = false;
				}
			} else if (jogo.getTabuleiro().getGrid()[origemX][origemY].getPeca().getDama()) {
				if (intencaoCapturaDama(origemX, origemY, destinoX, destinoY, jogo) == true) {

					jogo.capturar(origemX, origemY, destinoX, destinoY);

					jogo.setContadorJogadas(0);

					if (verificaCapturaObrigatoriaDama(destinoX, destinoY, jogo)) {
						this.setCombo(true);
						linha = destinoX;
						coluna = destinoY;
					} else if (!verificaCapturaObrigatoriaDama(destinoX, destinoY, jogo)) {
						this.setCombo(false);
						jogo.setVezAtual();
					}
				} else {
					jogadaValida = false;

				}

			}
		} else {
			jogadaValida = false;
		}
		if ((!jogo.getTabuleiro().getGrid()[origemX][origemY].getOcupada())) {

			imprimeTabuleiro(jogo);
			System.out.println();

		}
	}

	protected void imprimeTabuleiro(Jogo jogo) {
		for (int i = 0; i < jogo.getTabuleiro().getGrid().length; i++) {
			for (int j = 0; j < jogo.getTabuleiro().getGrid().length; j++) {
				if (jogo.getTabuleiro().getGrid()[i][j].getOcupada()) {
					if (jogo.getTabuleiro().getGrid()[i][j].getPeca().getDama()
							&& jogo.getTabuleiro().getGrid()[i][j].getPeca().getCorPeca().getValor() == 0) {
						System.out.print("C ");

					} else if (jogo.getTabuleiro().getGrid()[i][j].getPeca().getDama()
							&& jogo.getTabuleiro().getGrid()[i][j].getPeca().getCorPeca().getValor() == 1) {
						System.out.print("E ");
					} else if (jogo.getTabuleiro().getGrid()[i][j].getPeca().getCorPeca().getValor() == 0) {
						System.out.print("c ");

					} else if (jogo.getTabuleiro().getGrid()[i][j].getPeca().getCorPeca().getValor() == 1) {
						System.out.print("e ");
					}
				} else {
					System.out.print("- ");
				}
			}
			System.out.println();
		}

	}

	public void jogar(int origemX, int origemY, int destinoX, int destinoY, Jogo jogo) {

		if (isFimDeJogo(jogo) == false) {

			if (jogo.isCombo() == true) {

				combar(origemX, origemY, destinoX, destinoY, jogo);

			} else {
				if (!verificaLimiteTabuleiro(origemX, origemY, destinoX, destinoY, jogo)) {
					jogadaValida = false;
				} else {
					if (jogo.getTabuleiro().getGrid()[origemX][origemY].getOcupada()) {
						if (jogo.getTabuleiro().getGrid()[origemX][origemY].getPeca().getCorPeca().getValor() == jogo
								.getVezAtual()) {

							if (intervaloValido(origemX, origemY, destinoX, destinoY, jogo) == true) {
								if (jogo.getTabuleiro().getGrid()[destinoX][destinoY].getOcupada()) {

									jogadaValida = false;

								} else {
									if (isCapturaObrigatoria(jogo) == true) {

										if (jogo.getTabuleiro().getGrid()[origemX][origemY].getPeca()
												.getDama() == false) {

											if (verifica(origemX, origemY, destinoX, destinoY, jogo) == 2
													&& verificarExistenciaPeca(origemX, origemY, jogo)) {

												jogo.capturar(origemX, origemY, destinoX, destinoY);
												jogo.setContadorJogadas(0);
												jogadaValida = true;
												if (verificarExistenciaPeca(destinoX, destinoY, jogo)) {
													this.setCombo(true);
													linha = destinoX;
													coluna = destinoY;

												}

												else if (!verificarExistenciaPeca(destinoX, destinoY, jogo)) {
													this.setCombo(false);
													transformarDama(jogo);
													jogo.setVezAtual();

												}

											} else {
												jogadaValida = false;
											}
										} else if (jogo.getTabuleiro().getGrid()[origemX][origemY].getPeca()
												.getDama() == true) {
											if (intencaoCapturaDama(origemX, origemY, destinoX, destinoY,
													jogo) == true) {

												jogo.capturar(origemX, origemY, destinoX, destinoY);
												jogo.setContadorJogadas(0);
												jogadaValida = true;

												if (!verificaCapturaObrigatoriaDama(destinoX, destinoY, jogo)) {
													jogo.setVezAtual();
												}
											} else {
												jogadaValida = false;
											}

										}

									} else if (isCapturaObrigatoria(jogo) == false) {

										if ((jogo.getTabuleiro().getGrid()[origemX][origemY].getOcupada()
												&& jogo.getTabuleiro().getGrid()[origemX][origemY].getPeca().getDama()
												&& !jogo.getTabuleiro().getGrid()[destinoX][destinoY].getOcupada()
												&& jogo.getTabuleiro().getGrid()[origemX][origemY].getPeca()
														.getCorPeca().getValor() == jogo.getVezAtual())) {
											if (destinoX - origemX < 0 && destinoY - origemY < 0) {
												if ((existePecaEsqCima(origemX, origemY, destinoX, destinoY,
														jogo) == false)) {

													jogo.getTabuleiro().executarMovimento(origemX, origemY, destinoX,
															destinoY);

												}
											}
											if (destinoX - origemX < 0 && destinoY - origemY > 0) {

												if (existePecaDirCima(origemX, origemY, destinoX, destinoY,
														jogo) == false) {

													jogo.getTabuleiro().executarMovimento(origemX, origemY, destinoX,
															destinoY);
												}
											}
											if (destinoX - origemX > 0 && destinoY - origemY < 0) {
												if (existePecaDirBaixo(origemX, origemY, destinoX, destinoY,
														jogo) == false) {

													jogo.getTabuleiro().executarMovimento(origemX, origemY, destinoX,
															destinoY);
												}

											}

											if (destinoX - origemX > 0 && destinoY - origemY > 0) {
												if (existePecaEsqBaixo(origemX, origemY, destinoX, destinoY,
														jogo) == false) {

													jogo.getTabuleiro().executarMovimento(origemX, origemY, destinoX,
															destinoY);
												}

											}

											jogo.setVezAtual();
											jogo.setContadorJogadas(jogo.getContadorJogadas() + 1);
											jogadaValida = true;
										}

										if (jogo.getTabuleiro().getGrid()[origemX][origemY].getOcupada()
												&& jogo.getTabuleiro().getGrid()[origemX][origemY].getPeca()
														.getDama() == false) {

											if ((jogo.getTabuleiro().verifica(origemX, origemY, destinoX,
													destinoY) == 1)) {
												jogo.getTabuleiro().executarMovimento(origemX, origemY, destinoX,
														destinoY);
												transformarDama(jogo);
												jogo.setVezAtual();
												jogo.setContadorJogadas(jogo.getContadorJogadas() + 1);
												jogadaValida = true;
											}

										}

									}
									if ((!jogo.getTabuleiro().getGrid()[origemX][origemY].getOcupada())) {

										imprimeTabuleiro(jogo);
										System.out.println();

									}

									if (isFimDeJogo(jogo) == true) {
										if (jogo.getContadorJogadas() == 20) {
											System.out.println("Empate!");
										} else
											this.vencedor = jogo.getVencedor();
										System.out.println("Vencedor:" + jogo.getVencedor().getNome());
									}

								}
							} else {
								jogadaValida = false;
							}

						} else {
							jogadaValida = false;
						}

					}

				}
			}

		} else if (isFimDeJogo(jogo)) {

			System.out.println("Fim de jogo!");
		}
	}

	private boolean existePecaEsqCima(int origemX, int origemY, int destinoX, int destinoY, Jogo jogo) {
		boolean r = false;
		int j = origemY - 1;
		for (int i = origemX - 1; i > destinoX; i--) {
			if (j > 0 && i > 0) {
				if (jogo.getTabuleiro().getGrid()[i][j].getOcupada()) {
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
	private boolean existePecaDirCima(int origemX, int origemY, int destinoX, int destinoY, Jogo jogo) {
		boolean r = false;

		int j = origemY + 1;
		for (int i = origemX - 1; i > destinoX; i--) {
			if (j < jogo.getTabuleiro().getGrid().length && i > 0) {
				if (jogo.getTabuleiro().getGrid()[i][j].getOcupada()) {
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
	private boolean existePecaDirBaixo(int origemX, int origemY, int destinoX, int destinoY, Jogo jogo) {
		boolean r = false;
		int j = origemY - 1;
		for (int i = origemX + 1; i < destinoX; i++) {
			if (j > 0 && i < jogo.getTabuleiro().getGrid().length) {
				if (jogo.getTabuleiro().getGrid()[i][j].getOcupada()) {
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
	private boolean existePecaEsqBaixo(int origemX, int origemY, int destinoX, int destinoY, Jogo jogo) {
		boolean r = false;
		int j = origemY + 1;
		for (int i = origemX + 1; i < destinoX; i++) {
			if (j < jogo.getTabuleiro().getGrid().length && i < jogo.getTabuleiro().getGrid().length) {
				if (jogo.getTabuleiro().getGrid()[i][j].getOcupada()) {
					r = true;
				}
				j++;
			}
		}
		return r;

	}

	private boolean verificaLimiteTabuleiro(int origemX, int origemY, int destinoX, int destinoY, Jogo jogo) {

		boolean r = false;

		if (origemX >= 0 && origemX < jogo.getTabuleiro().getGrid().length && origemY >= 0
				&& origemY < jogo.getTabuleiro().getGrid().length && destinoX >= 0
				&& destinoX < jogo.getTabuleiro().getGrid().length && destinoX >= 0
				&& destinoX < jogo.getTabuleiro().getGrid().length) {
			r = true;
		}

		return r;

	}

	private int verificaPecaDirBaixo(int origemX, int origemY, int destinoX, int destinoY, Jogo jogo) {
		int r = -1;
		int a = 0;
		int b = 0;

		int j = origemY - 1;
		for (int i = origemX + 1; i < destinoX; i++) {
			if (j > 0 && i < jogo.getTabuleiro().getGrid().length) {
				if (jogo.getTabuleiro().getGrid()[i][j].getOcupada()) {
					a++;

					if (jogo.getTabuleiro().getGrid()[i][j].getPeca().getCorPeca().getValor() != jogo.getVezAtual()) {
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

	private int verificaPecaEsqCima(int origemX, int origemY, int destinoX, int destinoY, Jogo jogo) {
		int r = -1;
		int a = 0;
		int b = 0;

		int j = origemY - 1;
		for (int i = origemX - 1; i > destinoX; i--) {
			if (j > 0 && i > 0) {
				if (jogo.getTabuleiro().getGrid()[i][j].getOcupada()) {
					a++;
					if (jogo.getTabuleiro().getGrid()[i][j].getPeca().getCorPeca().getValor() != jogo.getVezAtual()) {
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
	private int verificaPecaDirCima(int origemX, int origemY, int destinoX, int destinoY, Jogo jogo) {
		int r = -1;
		int a = 0;
		int b = 0;

		int j = origemY + 1;
		for (int i = origemX - 1; i > destinoX; i--) {
			if (j < jogo.getTabuleiro().getGrid().length && i > 0) {
				if (jogo.getTabuleiro().getGrid()[i][j].getOcupada()) {
					a++;
					if (jogo.getTabuleiro().getGrid()[i][j].getPeca().getCorPeca().getValor() != jogo.getVezAtual()) {
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

	private int verificaPecaEsqBaixo(int origemX, int origemY, int destinoX, int destinoY, Jogo jogo) {
		int r = -1;
		int a = 0;
		int b = 0;

		int j = origemY + 1;
		for (int i = origemX + 1; i < destinoX; i++) {
			if (j < jogo.getTabuleiro().getGrid().length && i < jogo.getTabuleiro().getGrid().length) {
				if (jogo.getTabuleiro().getGrid()[i][j].getOcupada()) {
					a++;
					if (jogo.getTabuleiro().getGrid()[i][j].getPeca().getCorPeca().getValor() != jogo.getVezAtual()) {
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

	private boolean isCapturaObrigatoria(Jogo jogo) {
		boolean r = false;

		for (int i = 0; i < jogo.getTabuleiro().getGrid().length; i++) {
			for (int j = 0; j < jogo.getTabuleiro().getGrid().length; j++) {
				if (jogo.getTabuleiro().getGrid()[i][j].getOcupada()
						&& jogo.getTabuleiro().getGrid()[i][j].getPeca().getDama() == false) {

					if (jogo.getTabuleiro().getGrid()[i][j].getPeca().getCorPeca().getValor() == jogo.getVezAtual()) {

						if (i + 2 < jogo.getTabuleiro().getGrid().length
								&& j + 2 < jogo.getTabuleiro().getGrid().length) {

							if (jogo.getTabuleiro().getGrid()[i + 1][j + 1].getOcupada()
									&& jogo.getTabuleiro().getGrid()[i + 1][j + 1].getPeca().getCorPeca()
											.getValor() != jogo.getVezAtual()) {
								if (!jogo.getTabuleiro().getGrid()[i + 2][j + 2].getOcupada()) {
									r = true;

								}

							}
						}
						if (i + 2 < jogo.getTabuleiro().getGrid().length && j - 2 >= 0) {

							if (jogo.getTabuleiro().getGrid()[i + 1][j - 1].getOcupada()
									&& jogo.getTabuleiro().getGrid()[i + 1][j - 1].getPeca().getCorPeca()
											.getValor() != jogo.getVezAtual()) {

								if (!jogo.getTabuleiro().getGrid()[i + 2][j - 2].getOcupada()) {

									r = true;

								}

							}
						}
						if (i - 2 >= 0 && j + 2 < jogo.getTabuleiro().getGrid().length) {

							if (jogo.getTabuleiro().getGrid()[i - 1][j + 1].getOcupada()
									&& jogo.getTabuleiro().getGrid()[i - 1][j + 1].getPeca().getCorPeca()
											.getValor() != jogo.getVezAtual()) {
								if (!jogo.getTabuleiro().getGrid()[i - 2][j + 2].getOcupada()) {
									r = true;

								}

							}
						}
						if (i - 2 >= 0 && j - 2 >= 0) {

							if (jogo.getTabuleiro().getGrid()[i - 1][j - 1].getOcupada()
									&& jogo.getTabuleiro().getGrid()[i - 1][j - 1].getPeca().getCorPeca()
											.getValor() != jogo.getVezAtual()) {
								if (!jogo.getTabuleiro().getGrid()[i - 2][j - 2].getOcupada()) {
									r = true;

								}

							}
						}

					}

				} else if (jogo.getTabuleiro().getGrid()[i][j].getOcupada()
						&& jogo.getTabuleiro().getGrid()[i][j].getPeca().getDama() == true) {
					if (verificaCapturaObrigatoriaDama(i, j, jogo) == true) {

						r = true;

					}
				}

			}
		}
		return r;
	}

	private boolean verificaCapturaObrigatoriaDama(int origemX, int origemY, Jogo jogo) {
		boolean r = false;
		int i = 1;
		int a = 0;
		if (jogo.getTabuleiro().getGrid()[origemX][origemY].getPeca().getCorPeca().getValor() == jogo.getVezAtual()) {
			while (r == false) {

				if (a == 0) {

					if (origemX - i - 1 >= 0 && origemY + i + 1 < jogo.getTabuleiro().getGrid().length) {
						if (!jogo.getTabuleiro().getGrid()[origemX - i][origemY + i].getOcupada()) {
							i++;
						} else if (jogo.getTabuleiro().getGrid()[origemX - i][origemY + i].getOcupada()) {
							if (jogo.getTabuleiro().getGrid()[origemX - i][origemY + i].getPeca().getCorPeca()
									.getValor() != jogo.getVezAtual()
									&& !jogo.getTabuleiro().getGrid()[origemX - i - 1][origemY + i + 1].getOcupada()) {
								r = true;

							} else if (jogo.getTabuleiro().getGrid()[origemX - i - 1][origemY + i + 1].getOcupada()) {
								a = 1;
								i = 1;

							} else if (jogo.getTabuleiro().getGrid()[origemX - i][origemY + i].getPeca().getCorPeca()
									.getValor() == jogo.getVezAtual()) {
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

					if (origemX + i + 1 < jogo.getTabuleiro().getGrid().length
							&& origemY + i + 1 < jogo.getTabuleiro().getGrid().length) {
						if (!jogo.getTabuleiro().getGrid()[origemX + i][origemY + i].getOcupada()) {
							i++;

						} else if (jogo.getTabuleiro().getGrid()[origemX + i][origemY + i].getOcupada()) {
							if (jogo.getTabuleiro().getGrid()[origemX + i][origemY + i].getPeca().getCorPeca()
									.getValor() != jogo.getVezAtual()
									&& !jogo.getTabuleiro().getGrid()[origemX + i + 1][origemY + i + 1].getOcupada()) {
								r = true;

							} else if (jogo.getTabuleiro().getGrid()[origemX + i + 1][origemY + i + 1].getOcupada()) {
								a = 2;
								i = 1;

							} else if (jogo.getTabuleiro().getGrid()[origemX + i][origemY + i].getPeca().getCorPeca()
									.getValor() == jogo.getVezAtual()) {
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

					if (origemX + i + 1 < jogo.getTabuleiro().getGrid().length && origemY - i - 1 >= 0) {
						if (!jogo.getTabuleiro().getGrid()[origemX + i][origemY - i].getOcupada()) {
							i++;

						} else if (jogo.getTabuleiro().getGrid()[origemX + i][origemY - i].getOcupada()) {
							if (jogo.getTabuleiro().getGrid()[origemX + i][origemY - i].getPeca().getCorPeca()
									.getValor() != jogo.getVezAtual()
									&& !jogo.getTabuleiro().getGrid()[origemX + i + 1][origemY - i - 1].getOcupada()) {
								r = true;

							} else if (jogo.getTabuleiro().getGrid()[origemX + i + 1][origemY - i - 1].getOcupada()) {

								a = 3;
								i = 1;

							} else if (jogo.getTabuleiro().getGrid()[origemX + i][origemY - i].getPeca().getCorPeca()
									.getValor() == jogo.getVezAtual()) {
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
						if (!jogo.getTabuleiro().getGrid()[origemX - i][origemY - i].getOcupada()) {
							i++;

						} else if (jogo.getTabuleiro().getGrid()[origemX - i][origemY - i].getOcupada()) {
							if (jogo.getTabuleiro().getGrid()[origemX - i][origemY - i].getPeca().getCorPeca()
									.getValor() != jogo.getVezAtual()
									&& !jogo.getTabuleiro().getGrid()[origemX - i - 1][origemY - i - 1].getOcupada()) {
								r = true;

							} else if (jogo.getTabuleiro().getGrid()[origemX - i - 1][origemY - i - 1].getOcupada()) {
								break;
							} else if (jogo.getTabuleiro().getGrid()[origemX - i][origemY - i].getPeca().getCorPeca()
									.getValor() == jogo.getVezAtual()) {
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
	private boolean verificarExistenciaPeca(int origemX, int origemY, Jogo jogo) {
		boolean r = false;

		if (jogo.getTabuleiro().getGrid()[origemX][origemY].getPeca().getDama() == false) {

			if (origemX + 2 < jogo.getTabuleiro().getGrid().length
					&& origemY + 2 < jogo.getTabuleiro().getGrid().length) {
				if (jogo.getTabuleiro().getGrid()[origemX + 1][origemY + 1].getOcupada()) {
					if (jogo.getTabuleiro().getGrid()[origemX + 1][origemY + 1].getPeca().getCorPeca()
							.getValor() != jogo.getVezAtual()) {
						if (!jogo.getTabuleiro().getGrid()[origemX + 2][origemY + 2].getOcupada()) {

							r = true;
						}

					}
				}
			}
			if (origemX + 2 < jogo.getTabuleiro().getGrid().length && origemY - 2 >= 0) {
				if (jogo.getTabuleiro().getGrid()[origemX + 1][origemY - 1].getOcupada()) {
					if (jogo.getTabuleiro().getGrid()[origemX + 1][origemY - 1].getPeca().getCorPeca()
							.getValor() != jogo.getVezAtual()) {
						if (!jogo.getTabuleiro().getGrid()[origemX + 2][origemY - 2].getOcupada()) {

							r = true;
						}

					}
				}
			}
			if (origemX - 2 >= 0 && origemY + 2 < jogo.getTabuleiro().getGrid().length) {
				if (jogo.getTabuleiro().getGrid()[origemX - 1][origemY + 1].getOcupada()
						&& jogo.getTabuleiro().getGrid()[origemX - 1][origemY + 1].getPeca().getCorPeca()
								.getValor() != jogo.getVezAtual()) {
					if (!jogo.getTabuleiro().getGrid()[origemX - 2][origemY + 2].getOcupada()) {

						r = true;
					}
				}
			}
			if (origemX - 2 >= 0 && origemY - 2 >= 0) {
				if (jogo.getTabuleiro().getGrid()[origemX - 1][origemY - 1].getOcupada()) {
					if (jogo.getTabuleiro().getGrid()[origemX - 1][origemY - 1].getPeca().getCorPeca()
							.getValor() != jogo.getVezAtual()) {
						if (!jogo.getTabuleiro().getGrid()[origemX - 2][origemY - 2].getOcupada()) {

							r = true;
						}
					}
				}
			}
		}

		return r;

	}

	// verifica se tem uma peça de uma cor chegou no lado oposto para
	// transformar em
	// uma dama
	private void transformarDama(Jogo jogo) {

		for (int j = 0; j < jogo.getTabuleiro().getGrid().length; j++) {
			if (jogo.getTabuleiro().getGrid()[0][j].getOcupada()
					&& jogo.getTabuleiro().getGrid()[0][j].getPeca().getDama() == false
					&& jogo.getTabuleiro().getGrid()[0][j].getPeca().getCorPeca().getValor() == 0) {
				Dama d = new Dama(CorPeca.CLARA, jogo.getJogador1(), true);
				jogo.getTabuleiro().getGrid()[0][j].setPeca(null);
				jogo.getTabuleiro().getGrid()[0][j].setPeca(d);

			}
			if (jogo.getTabuleiro().getGrid()[jogo.getTabuleiro().getGrid().length - 1][j].getOcupada()
					&& jogo.getTabuleiro().getGrid()[jogo.getTabuleiro().getGrid().length - 1][j].getPeca()
							.getDama() == false
					&& jogo.getTabuleiro().getGrid()[jogo.getTabuleiro().getGrid().length - 1][j].getPeca().getCorPeca()
							.getValor() == 1) {

				Dama d = new Dama(CorPeca.ESCURA, jogo.getJogador2(), true);
				jogo.getTabuleiro().getGrid()[jogo.getTabuleiro().getGrid().length - 1][j].setPeca(null);
				jogo.getTabuleiro().getGrid()[jogo.getTabuleiro().getGrid().length - 1][j].setPeca(d);

			}

		}

	}

	// metodo que movimenta a dama de acordo com suas diagonais, alem de fazer a
	// captura se necessario

	public boolean isFimDeJogo(Jogo jogo) {

		boolean fimJogo = false;

		if (quantPecaClara(jogo) == 0) {
			jogo.setResultado(Resultado.COMVENCEDOR);
			jogo.setVencedor(jogo.getJogador2());
			fimJogo = true;
		} else if (quantPecaEscura(jogo) == 0) {
			jogo.setResultado(Resultado.COMVENCEDOR);
			jogo.setVencedor(jogo.getJogador1());
			fimJogo = true;
		} else if (quantPecaClara(jogo) != 0 && quantPecaEscura(jogo) != 0) {
			if (jogo.getContadorJogadas() == 20) {
				jogo.setResultado(Resultado.EMPATE);
				fimJogo = true;
			}
		}

		return fimJogo;
	}

	private boolean intencaoCapturaDama(int origemX, int origemY, int destinoX, int destinoY, Jogo jogo) {
		boolean r = false;

		if (destinoX - origemX > 0 && destinoY - origemY < 0) {
			if (verificaPecaDirBaixo(origemX, origemY, destinoX, destinoY, jogo) == 1) {
				r = true;
			}
		}
		if (destinoX - origemX > 0 && destinoY - origemY > 0) {
			if (verificaPecaEsqBaixo(origemX, origemY, destinoX, destinoY, jogo) == 1) {
				r = true;
			}
		}
		if (destinoX - origemX < 0 && destinoY - origemY > 0) {
			if (verificaPecaDirCima(origemX, origemY, destinoX, destinoY, jogo) == 1) {
				r = true;
			}
		}
		if (destinoX - origemX < 0 && destinoY - origemY < 0) {
			if (verificaPecaEsqCima(origemX, origemY, destinoX, destinoY, jogo) == 1) {
				r = true;
			}
		}

		return r;
	}

	private boolean intervaloValido(int origemX, int origemY, int destinoX, int destinoY, Jogo jogo) {
		boolean r = false;
		if ((origemX >= 0 && origemX < jogo.getTabuleiro().getGrid().length)
				&& (origemY >= 0 && origemY < jogo.getTabuleiro().getGrid().length)
				&& (destinoX >= 0 && destinoX < jogo.getTabuleiro().getGrid().length)
				&& (destinoY >= 0 && destinoY < jogo.getTabuleiro().getGrid().length)) {
			if ((Math.abs(origemX - destinoX)) == (Math.abs(origemY - destinoY))) {
				r = true;
			}
		}
		return r;
	}

	public int quantPecaClara(Jogo jogo) {
		int quantPecaClara = 0;
		for (int i = 0; i < jogo.getTabuleiro().getGrid().length; i++) {
			for (int j = 0; j < jogo.getTabuleiro().getGrid().length; j++) {
				if (jogo.getTabuleiro().getGrid()[i][j].getOcupada()) {
					if (jogo.getTabuleiro().getGrid()[i][j].getPeca().getCorPeca().equals(CorPeca.CLARA)) {
						quantPecaClara++;

					}
				}
			}
		}
		return quantPecaClara;
	}

	public int quantPecaEscura(Jogo jogo) {
		int quantPecaEscura = 0;
		for (int i = 0; i < jogo.getTabuleiro().getGrid().length; i++) {
			for (int j = 0; j < jogo.getTabuleiro().getGrid().length; j++) {
				if (jogo.getTabuleiro().getGrid()[i][j].getOcupada()) {
					if (jogo.getTabuleiro().getGrid()[i][j].getPeca().getCorPeca().equals(CorPeca.ESCURA)) {
						quantPecaEscura++;

					}
				}
			}
		}
		return quantPecaEscura;
	}

	public boolean getCombo() {
		return combo;
	}

	public void setCombo(boolean combo) {
		this.combo = combo;
	}
}
