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

public class MenuJogo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JPanel panel2 = new JPanel();
	private JLabel label = new JLabel();
	private JButton btn1_Jogar = new JButton("JOGAR");
	private JButton btn2_Instrucoes = new JButton("INSTRUÇÕES");
	private JButton btn3_Sair = new JButton("SAIR");
	private ImageIcon dama = new ImageIcon("dama.png");

	public MenuJogo() {
		setTitle("Jogo de Damas");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		label = new JLabel(dama);
		FlowLayout flowLayoutIm = (FlowLayout) panel.getLayout();
		flowLayoutIm.setVgap(50);
		panel.add(label);
		panel.setBackground(Color.ORANGE);
		getContentPane().add(panel, BorderLayout.CENTER);
		FlowLayout flowLayout = (FlowLayout) panel2.getLayout();
		flowLayout.setHgap(30);
		flowLayout.setVgap(130);
		btn1_Jogar.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel2.add(btn1_Jogar);
		btn2_Instrucoes.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel2.add(btn2_Instrucoes);
		btn3_Sair.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel2.add(btn3_Sair);
		panel2.setBackground(Color.ORANGE);
		getContentPane().add(panel2, BorderLayout.SOUTH);

	}

	public JButton getBtn1_Jogar() {
		return this.btn1_Jogar;
	}

	public JButton getBtn2_Instrucoes() {
		return this.btn2_Instrucoes;
	}

	public JButton getBtn3_Sair() {
		return this.btn3_Sair;
	}

}
