package br.com.poli.IGrafica;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.Font;

public class SelecionarJogadores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel_SelecionaJogadores = new JPanel();
	private JPanel panel_Imagem = new JPanel();
	private JLabel label_Imagem = new JLabel();
	private JPanel panelVoltar = new JPanel();
	private JButton button2Jogadores = new JButton("DOIS JOGADORES");
	private JButton button1Jogador = new JButton("UM JOGADOR");
	private JButton buttonVoltar = new JButton("VOLTAR");
	private ImageIcon damas = new ImageIcon("DamasS.png");

	public SelecionarJogadores() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		setSize(500, 500);
		label_Imagem = new JLabel(damas);
		panel_Imagem.add(label_Imagem);
		getContentPane().add(panel_Imagem, BorderLayout.NORTH);
		panel_Imagem.setBackground(Color.ORANGE);
		
		button2Jogadores.setFont(new Font("Tahoma", Font.BOLD, 22));
		button1Jogador.setFont(new Font("Tahoma", Font.BOLD, 22));
		panel_SelecionaJogadores.add(button1Jogador);
		FlowLayout flowLayout = (FlowLayout) panel_SelecionaJogadores.getLayout();
		flowLayout.setHgap(30);
		flowLayout.setVgap(90);
		panel_SelecionaJogadores.add(button2Jogadores);
		panel_SelecionaJogadores.setBackground(Color.ORANGE);
		getContentPane().add(panel_SelecionaJogadores, BorderLayout.CENTER);
		
		FlowLayout flowLayout_1 = (FlowLayout) panelVoltar.getLayout();
		flowLayout_1.setVgap(50);
		buttonVoltar.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelVoltar.add(buttonVoltar);
		panelVoltar.setBackground(Color.ORANGE);
		getContentPane().add(panelVoltar, BorderLayout.SOUTH);
		
		
		
	}

	public JButton getButton2Jogadores() {
		return this.button2Jogadores;
	}

	public JButton getButtonVoltar() {
		return this.buttonVoltar;
	}

	public JButton getButton1Jogador() {
		return this.button1Jogador;
	}

}
