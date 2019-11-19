package br.com.poli.IGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import br.com.poli.dama.*;
import br.com.poli.enumeradores.*;
import br.com.poli.exceptions.*;

import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.Insets;

public class InicioJogo extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel label_Aviso = new JLabel();
	private JLabel nomeJogador2 = new JLabel();
	private JLabel nomeJogador1 = new JLabel();
	private JLabel vezJogador = new JLabel();
	private JLabel movSemCaptura = new JLabel();
	private JLabel quantPecaJogador1 = new JLabel();
	private JLabel quantPecaJogador2 = new JLabel();
	private JLabel tempo = new JLabel();
	private JButton desistir = new JButton("DESISTIR");
	private JPanel panel_Tabuleiro = new JPanel();
	private JButton[][] btn_Tabuleiro = new JButton[8][8];
	private ImageIcon pecaClara = new ImageIcon("PecaClara.png");
	private ImageIcon pecaEscura = new ImageIcon("PecaEscura.png");
	private ImageIcon pecaClaraDama = new ImageIcon("PecaClaraDama.png");
	private ImageIcon pecaEscuraDama = new ImageIcon("PecaEscuraDama.png");
	private JPanel informacoes = new JPanel();
	private boolean selecionar;
	public static Jogo jogo = ControladorIGrafica.jogo;
	private int clickI;
	private int clickJ;
	private final JPanel panelJogador2 = new JPanel();
	private final JPanel panelJogador1 = new JPanel();
	private final JPanel panelJogo = new JPanel();
	private final JPanel panel_Vez = new JPanel();
	private final JPanel panel_Mov = new JPanel();
	private final JPanel panel_Aviso = new JPanel();
	private final JPanel panel_Desistir = new JPanel();
	private final JPanel panel_Tempo = new JPanel();
	private Timer t;
	private Timer a;

	public InicioJogo() {
		setTitle("Jogo de Damas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);

		informacoes.setLayout(new BoxLayout(informacoes, BoxLayout.PAGE_AXIS));
		panelJogador2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		informacoes.add(panelJogador2);
		panelJogador2.setLayout(new BoxLayout(panelJogador2, BoxLayout.PAGE_AXIS));
		informacoes.add(panelJogo);
		panelJogo.setLayout(new BoxLayout(panelJogo, BoxLayout.PAGE_AXIS));
		FlowLayout fl_panel_Tempo = (FlowLayout) panel_Tempo.getLayout();
		fl_panel_Tempo.setHgap(1);
		fl_panel_Tempo.setVgap(1);

		panelJogo.add(panel_Tempo);
		panel_Tempo.add(tempo);
		tempo.setText("TEMPO: ");
		panelJogo.add(panel_Vez);
		panel_Vez.add(vezJogador);
		vezJogador.setHorizontalAlignment(SwingConstants.CENTER);
		vezJogador.setText("Vez Atual: " + ControladorIGrafica.jogo.jogadorAtual().getNome());

		panelJogo.add(panel_Mov);
		panel_Mov.add(movSemCaptura);
		movSemCaptura.setText("Jogadas s/Captura: " + jogo.getContadorJogadas());
		FlowLayout fl_panel_Desistir = (FlowLayout) panel_Desistir.getLayout();
		fl_panel_Desistir.setHgap(1);
		fl_panel_Desistir.setVgap(1);

		panelJogo.add(panel_Desistir);
		desistir.setMargin(new Insets(21, 14, 21, 14));
		desistir.setBackground(Color.ORANGE);
		panel_Desistir.add(desistir);
		desistir.setVerticalAlignment(SwingConstants.TOP);
		desistir.setHorizontalAlignment(SwingConstants.LEFT);
		panelJogo.add(panel_Aviso);
		panel_Aviso.add(label_Aviso);
		label_Aviso.setVisible(false);
		label_Aviso.setForeground(new Color(210, 105, 30));
		panelJogador1.setAlignmentX(Component.RIGHT_ALIGNMENT);

		informacoes.add(panelJogador1);
		panelJogador1.setLayout(new BoxLayout(panelJogador1, BoxLayout.PAGE_AXIS));
		if (ControladorIGrafica.jogador2Jogadores.getJogadorAutonomo()) {
			nomeJogador2.setText("Jogador 2: " + jogo.getJogador2().getNome());
			nomeJogador1.setText("Jogador 1: " + ControladorIGrafica.jogador1Jogadores.getNome());
		} else {
			nomeJogador2.setText("Jogador 2: " + ControladorIGrafica.jogador2Jogadores.getNome());
			nomeJogador1.setText("Jogador 1: " + ControladorIGrafica.jogador1Jogadores.getNome());
		}
		panelJogador2.add(nomeJogador2);
		nomeJogador2.setHorizontalAlignment(SwingConstants.TRAILING);
		panelJogador1.add(nomeJogador1);
		nomeJogador1.setHorizontalAlignment(SwingConstants.TRAILING);

		panelJogador2.add(quantPecaJogador2);
		quantPecaJogador2.setText(jogo.quantPecaEscura() + " peças escuras");

		panelJogador1.add(quantPecaJogador1);
		quantPecaJogador1.setText(jogo.quantPecaClara() + " peças claras");
		panelJogo.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		panel_Vez.setBackground(Color.LIGHT_GRAY);
		panel_Mov.setBackground(Color.LIGHT_GRAY);
		panel_Aviso.setBackground(Color.LIGHT_GRAY);
		panel_Desistir.setBackground(Color.LIGHT_GRAY);
		panel_Tempo.setBackground(Color.LIGHT_GRAY);

		panel_Tabuleiro.setLayout(new GridLayout(8, 8));
		ButtonHandler buttonHandler = new ButtonHandler();

		for (int i = 0; i < btn_Tabuleiro.length; i++) {
			for (int j = 0; j < btn_Tabuleiro[i].length; j++) {
				if (jogo.getTabuleiro().getGrid()[i][j].getCorCasa().equals(CorCasa.BRANCO)) {
					btn_Tabuleiro[i][j] = new JButton();
					btn_Tabuleiro[i][j].setBackground(Color.WHITE);
					btn_Tabuleiro[i][j].addActionListener(buttonHandler);
					panel_Tabuleiro.add(btn_Tabuleiro[i][j]);

				} else if (jogo.getTabuleiro().getGrid()[i][j].getCorCasa().equals(CorCasa.PRETO)) {
					if (i >= 0 && i < 3) {
						btn_Tabuleiro[i][j] = new JButton(pecaEscura);
						btn_Tabuleiro[i][j].setBackground(Color.BLACK);
						btn_Tabuleiro[i][j].addActionListener(buttonHandler);
						panel_Tabuleiro.add(btn_Tabuleiro[i][j]);

					} else if (i >= 5 && i < btn_Tabuleiro.length) {
						btn_Tabuleiro[i][j] = new JButton(pecaClara);
						btn_Tabuleiro[i][j].setBackground(Color.BLACK);
						btn_Tabuleiro[i][j].addActionListener(buttonHandler);
						panel_Tabuleiro.add(btn_Tabuleiro[i][j]);
					} else {
						btn_Tabuleiro[i][j] = new JButton();
						btn_Tabuleiro[i][j].setBackground(Color.BLACK);
						btn_Tabuleiro[i][j].addActionListener(buttonHandler);
						panel_Tabuleiro.add(btn_Tabuleiro[i][j]);
					}
				}

			}
		}
		getContentPane().add(informacoes, BorderLayout.EAST);
		getContentPane().add(panel_Tabuleiro, BorderLayout.CENTER);

		t = new Timer(1000, new ActionListener() {
			int seg = 0;
			int min = 0;
			int horas = 0;

			@Override
			public void actionPerformed(ActionEvent arg0) {

				tempo.setText("TEMPO: " + horas + "h: " + min + "m: " + seg + "s");
				seg++;
				if (seg == 60) {
					min++;
					seg = 0;
				}
				if (min == 60) {
					horas++;
					min = 0;
					seg = 0;
				}

			}
		});
		t.start();
	}

	private class ButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent evnt) {
			Object source = evnt.getSource();
			for (int i = 0; i < btn_Tabuleiro.length; i++) {
				for (int j = 0; j < btn_Tabuleiro.length; j++) {
					if (source == btn_Tabuleiro[i][j]) {
						if (btn_Tabuleiro[i][j].getIcon() != null && selecionar == false) {
							clickI = i;
							clickJ = j;
							selecionar = true;

						} else {
							selecionar = false;
							try {
								if (ControladorIGrafica.jogador2Jogadores.getJogadorAutonomo()) {
									jogo.jogar(clickI, clickJ, i, j);
									if (jogo.jogadorAtual().equals(jogo.getJogador2())) {
										a = new Timer(1000, new ActionListener() {

											@Override
											public void actionPerformed(ActionEvent arg0) {

												while (jogo.jogadorAtual().equals(jogo.getJogador2())) {
													ControladorIGrafica.jogador2Autonomo.jogarAuto(jogo);
													atualizarTabuleiro();
													atualizaInformacoes();
													label_Aviso.setVisible(false);
													a.stop();

													if (jogo.combo) {
														a.restart();

													}
													if (jogo.isFimDeJogo() == true) {
														FimJogo fimJogo = new FimJogo();
														ControladorIGrafica.frameAtual
																.setContentPane(fimJogo.getContentPane());
														fimJogo.getFim_Sair().addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent e) {
																ControladorIGrafica.frameAtual.dispose();
															}
														});
													}

												}
												desistir.setEnabled(true);
											}
										});
										a.start();

									}
									atualizarTabuleiro();
									atualizaInformacoes();
									if (jogo.isFimDeJogo() == true) {
										FimJogo fimJogo = new FimJogo();
										ControladorIGrafica.frameAtual.setContentPane(fimJogo.getContentPane());
										fimJogo.getFim_Sair().addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												ControladorIGrafica.frameAtual.dispose();
											}
										});
									}
								} else {
									jogo.jogar(clickI, clickJ, i, j);
									atualizarTabuleiro();
									atualizaInformacoes();
									if (jogo.isFimDeJogo() == true) {
										FimJogo fimJogo = new FimJogo();
										ControladorIGrafica.frameAtual.setContentPane(fimJogo.getContentPane());
										fimJogo.getFim_Sair().addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												ControladorIGrafica.frameAtual.dispose();
											}
										});

									}
								}

							} catch (CapturaObrigatoriaException e) {
								label_Aviso.setText(e.getMessage());
								label_Aviso.setVisible(true);
							}

							catch (MovimentoInvalidoException e) {
								label_Aviso.setText(e.getMessage());
								label_Aviso.setVisible(true);
							}

						}
					}
				}

			}
		}

	}

	public void atualizaInformacoes() {
		vezJogador.setText("Vez Atual: " + jogo.jogadorAtual().getNome());
		movSemCaptura.setText("Jogadas s/Captura: " + jogo.getContadorJogadas());
		quantPecaJogador2.setText(jogo.quantPecaEscura() + " peças escuras");
		quantPecaJogador1.setText(jogo.quantPecaClara() + " peças claras");
		label_Aviso.setVisible(false);
	}

	public void atualizarTabuleiro() {
		for (int i = 0; i < btn_Tabuleiro.length; i++) {
			for (int j = 0; j < btn_Tabuleiro.length; j++) {
				btn_Tabuleiro[i][j].setIcon(null);
				if (jogo.getTabuleiro().getGrid()[i][j].getOcupada()) {
					if (jogo.getTabuleiro().getGrid()[i][j].getPeca().getDama()) {
						if (jogo.getTabuleiro().getGrid()[i][j].getPeca().getCorPeca().equals(CorPeca.CLARA)) {
							btn_Tabuleiro[i][j].setIcon(pecaClaraDama);
						} else if (jogo.getTabuleiro().getGrid()[i][j].getPeca().getCorPeca().equals(CorPeca.ESCURA)) {
							btn_Tabuleiro[i][j].setIcon(pecaEscuraDama);
						}
					} else {
						if (jogo.getTabuleiro().getGrid()[i][j].getPeca().getCorPeca().equals(CorPeca.CLARA)) {
							btn_Tabuleiro[i][j].setIcon(pecaClara);
						} else if (jogo.getTabuleiro().getGrid()[i][j].getPeca().getCorPeca().equals(CorPeca.ESCURA)) {
							btn_Tabuleiro[i][j].setIcon(pecaEscura);
						}
					}

				}

			}
		}

	}

	public JButton getDesistir() {
		return this.desistir;
	}
}
