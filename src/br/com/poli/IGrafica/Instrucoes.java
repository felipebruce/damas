package br.com.poli.IGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.FlowLayout;

public class Instrucoes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel_Instrucoes = new JPanel();
	private JPanel panel_Voltar = new JPanel();
	private JButton btn_voltar = new JButton("VOLTAR");



	
	public Instrucoes() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(500, 500);
		FlowLayout flowLayout_1 = (FlowLayout) panel_Instrucoes.getLayout();
		flowLayout_1.setVgap(70);
		panel_Instrucoes.setBackground(Color.ORANGE);
		getContentPane().add(panel_Instrucoes, BorderLayout.CENTER);
		
		JTextPane txtpnInstruesoJogo = new JTextPane();
		txtpnInstruesoJogo.setForeground(Color.BLACK);
		txtpnInstruesoJogo.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnInstruesoJogo.setBackground(Color.ORANGE);
		txtpnInstruesoJogo.setEditable(false);
		txtpnInstruesoJogo.setText("Instru\u00E7\u00F5es:\r\n\r\n*O jogo de damas \u00E9 praticado entre dois parceiros, com 12 pedras brancas \r\nde um lado e com 12 pedras pretas de outro lado.\r\n*Para mover uma pe\u00E7a basta clicar na casa escolhida com sua pe\u00E7a e depois \r\nonde desejar efetuar a jogada.\r\n*O lance inicial cabe sempre a quem estiver com as pe\u00E7as brancas.\r\n*A pedra anda s\u00F3 para frente, uma casa de cada vez. Quando a pedra atinge \r\na oitava linha do tabuleiro ela \u00E9 promovida \u00E0 dama. \r\n*A dama \u00E9 uma pe\u00E7a de movimentos mais amplos. Ela anda para frente e \r\npara tr\u00E1s, quantas casas quiser.\r\n*A dama n\u00E3o pode saltar uma pe\u00E7a da mesma cor.  \r\n*A pedra e a dama podem capturar tanto para frente como para tr\u00E1s, uma\r\nou mais pe\u00E7as.\r\n*Empate: Ap\u00F3s 20 lances sucessivos de damas, sem captura ou deslocamento \r\nde pedra, a partida \u00E9 declarada empatada.\r\n*Caso na sua vez seja acionado o bot\u00E3o desitir, o oponente vence.\r\n*Vence quem capturar todas as pe\u00E7as do oponente.");
		panel_Instrucoes.add(txtpnInstruesoJogo);
		FlowLayout flowLayout = (FlowLayout) panel_Voltar.getLayout();
		flowLayout.setHgap(20);
		flowLayout.setVgap(80);
		btn_voltar.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_Voltar.add(btn_voltar);
		panel_Voltar.setBackground(Color.ORANGE);
		getContentPane().add(panel_Voltar, BorderLayout.SOUTH);
		
	}
	public JButton getBtn_voltar() {
		return this.btn_voltar;
	}
}
