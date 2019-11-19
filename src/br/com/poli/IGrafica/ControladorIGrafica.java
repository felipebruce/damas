package br.com.poli.IGrafica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import br.com.poli.dama.Jogador;
import br.com.poli.dama.JogadorAutonomo;
import br.com.poli.dama.Jogo;
import br.com.poli.interfaces.InterfaceJogo;

public class ControladorIGrafica implements InterfaceJogo {

	public static JFrame frameAtual = new JFrame();
	public static MenuJogo menu;
	private Instrucoes instrucoes;
	private SelecionarJogadores selecionarJogadores;
	private IniciarJogadores inicioJogadores;
	private IJogadorAuto jogadorAuto;
	private InicioJogo tabuleiro;
	public static FimJogo fimJogo;
	public static Jogo jogo = new Jogo();
	public static Jogador jogador1Jogadores = new Jogador();
	public static Jogador jogador2Jogadores = new Jogador();
	public static JogadorAutonomo jogador2Autonomo = new JogadorAutonomo();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ControladorIGrafica();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ControladorIGrafica() {
		frameAtual.setTitle("Jogo de Damas");
		frameAtual.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frameAtual.setResizable(false);
		frameAtual.setSize(800, 600);
		telaMenu();
	}

	public void telaMenu() {
		menu = new MenuJogo();
		frameAtual.setContentPane(menu.getContentPane());
		frameAtual.setVisible(true);
		menu.getBtn1_Jogar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarJogadores();
			}
		});
		menu.getBtn2_Instrucoes().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instrucoes();
			}
		});
		menu.getBtn3_Sair().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameAtual.dispose();

			}
		});
	}

	public void instrucoes() {
		instrucoes = new Instrucoes();
		frameAtual.setContentPane(instrucoes.getContentPane());
		frameAtual.setVisible(true);

		instrucoes.getBtn_voltar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaMenu();
			}
		});
	}

	public void selecionarJogadores() {
		selecionarJogadores = new SelecionarJogadores();
		frameAtual.setContentPane(selecionarJogadores.getContentPane());
		frameAtual.setVisible(true);

		selecionarJogadores.getButton2Jogadores().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doisJogadores();
			}
		});
		selecionarJogadores.getButtonVoltar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaMenu();
			}
		});
		selecionarJogadores.getButton1Jogador().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jogadorAutonomo();
			}
		});
	}

	public void doisJogadores() {
		inicioJogadores = new IniciarJogadores();
		frameAtual.setContentPane(inicioJogadores.getContentPane());
		frameAtual.setVisible(true);

		inicioJogadores.getBtn1Jogadores().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (inicioJogadores.verificarDados()) {
					jogador1Jogadores.setNome(IniciarJogadores.textJogador1Jogadores.getText().replaceAll(" ", "")
							.replaceAll("[^a-zA-Z]", ""));
					jogador2Jogadores.setNome(IniciarJogadores.textJogador2Jogadores.getText().replaceAll(" ", "")
							.replaceAll("[^a-zA-Z]", ""));
					jogador1Jogadores.setIdade(inicioJogadores.getTextId1Jogadores().getText().replaceAll(" ", "")
							.replaceAll("[^a-zA-Z]", ""));
					jogador2Jogadores.setIdade(inicioJogadores.getTextId2Jogadores().getText().replaceAll(" ", "")
							.replaceAll("[^a-zA-Z]", ""));
					inicioJogadores.getLabelAvisoJogadores().setVisible(false);
					jogo.iniciarPartida(jogador1Jogadores.getNome(), jogador1Jogadores.getIdade(),
							jogador2Jogadores.getNome(), jogador2Jogadores.getIdade());
					inicioJogo();
				}else {
					inicioJogadores.getLabelAvisoJogadores().setVisible(true);
				}

			}
		});
		inicioJogadores.getBtn2Jogadores().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarJogadores();

			}
		});
	}

	public void jogadorAutonomo() {
		jogadorAuto = new IJogadorAuto();
		frameAtual.setContentPane(jogadorAuto.getContentPane());
		frameAtual.setVisible(true);
		jogadorAuto.getIniciarJogadorAutonomo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jogador2Jogadores.setJogadorAutonomo(true);

				if (jogadorAuto.verificarDadosAutonomo()) {
					jogador1Jogadores.setNome(IJogadorAuto.textJogadorAutonomo.getText().replaceAll(" ", "")
							.replaceAll("[^a-zA-Z]", ""));

					jogador1Jogadores.setIdade(jogadorAuto.getTextIdJogadorAutonomo().getText().replaceAll(" ", "")
							.replaceAll("[^a-zA-Z]", ""));
					jogadorAuto.getLabelAvisoAutonomo().setVisible(false);
					jogo.iniciarPartida(jogador1Jogadores.getNome(), jogador1Jogadores.getIdade(), "Auto", "18");
					inicioJogo();
					
				}else {
					jogadorAuto.getLabelAvisoAutonomo().setVisible(true);
				}
			}
		});

		jogadorAuto.getVoltarJogadorAutonomo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarJogadores();
			}
		});

	}

	public void inicioJogo() {
		tabuleiro = new InicioJogo();
		frameAtual.setContentPane(tabuleiro.getContentPane());
		frameAtual.setVisible(true);
		tabuleiro.getDesistir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jogador2Jogadores.getJogadorAutonomo()) {
					if (jogo.jogadorAtual().equals(jogo.getJogador1())) {
						jogo.setVencedor(jogo.getJogador2());
						fimJogo();
					} else {
						tabuleiro.getDesistir().setEnabled(false);
					}
				} else if(jogador2Jogadores.getJogadorAutonomo()== false){
					if (jogo.jogadorAtual().equals(jogo.getJogador1())) {
						jogo.setVencedor(jogo.getJogador2());
						fimJogo();
					} else {
						jogo.setVencedor(jogo.getJogador1());
						fimJogo();
					}
				}

				
			}
		});
	}

	public void fimJogo() {
		fimJogo = new FimJogo();
		frameAtual.setContentPane(fimJogo.getContentPane());
		frameAtual.setVisible(true);
		fimJogo.getFim_Sair().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameAtual.dispose();
			}
		});
	}
}
