package br.com.poli.IGrafica;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Component;

public class IniciarJogadores extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel panelJogadores = new JPanel();
	private JPanel panel2Jogadores = new JPanel();
	private JPanel panel3Jogadores = new JPanel();
	private JLabel labelJogadores = new JLabel("Jogador 1: ");
	private JLabel label2Jogadores = new JLabel("Jogador 2: ");
	private JLabel labelAvisoJogadores = new JLabel("CAMPO PREENCHIDO ERRADO!");
	private JButton btn1Jogadores = new JButton("INICIAR");
	private JButton btn2Jogadores = new JButton("VOLTAR");
	public static JTextField textJogador1Jogadores = new JTextField();
	public static JTextField textJogador2Jogadores = new JTextField();
	private JLabel labelId1Jogadores = new JLabel("Idade: ");
	private JLabel labelId2Jogadores = new JLabel("Idade: ");
	private JTextField textId1Jogadores = new JTextField();
	private JTextField textId2Jogadores = new JTextField();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new IniciarJogadores();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public IniciarJogadores() {
		textId2Jogadores.setColumns(5);
		textId1Jogadores.setColumns(5);
		textJogador2Jogadores.setColumns(10);
		getContentPane().setLayout(new BorderLayout());
		labelJogadores.setFont(new Font("Tahoma", Font.BOLD, 18));

		label2Jogadores.setFont(new Font("Tahoma", Font.BOLD, 18));

		FlowLayout flowLayout = (FlowLayout) panelJogadores.getLayout();
		flowLayout.setVgap(80);
		flowLayout.setHgap(30);
		panelJogadores.add(labelJogadores);
		FlowLayout flowLayout_1 = (FlowLayout) panel2Jogadores.getLayout();
		flowLayout_1.setVgap(70);
		flowLayout_1.setHgap(30);
		panel2Jogadores.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel2Jogadores.add(label2Jogadores);
		getContentPane().add(panelJogadores, BorderLayout.PAGE_START);
		textJogador1Jogadores.setColumns(10);

		panelJogadores.add(textJogador1Jogadores);
		labelId1Jogadores.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelId1Jogadores.setHorizontalAlignment(SwingConstants.CENTER);

		panelJogadores.add(labelId1Jogadores);
		panelJogadores.add(textId1Jogadores);
		getContentPane().add(panel2Jogadores, BorderLayout.CENTER);
		panelJogadores.setBackground(Color.ORANGE);

		panel2Jogadores.add(textJogador2Jogadores);
		labelId2Jogadores.setFont(new Font("Tahoma", Font.BOLD, 18));

		panel2Jogadores.add(labelId2Jogadores);

		panel2Jogadores.add(textId2Jogadores);
		panel2Jogadores.setBackground(Color.ORANGE);
		FlowLayout flowLayout_2 = (FlowLayout) panel3Jogadores.getLayout();
		flowLayout_2.setVgap(80);
		flowLayout_2.setHgap(60);
		btn1Jogadores.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel3Jogadores.add(btn1Jogadores);
		
		btn2Jogadores.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel3Jogadores.add(btn2Jogadores);
		labelAvisoJogadores.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelAvisoJogadores.setForeground(new Color(128, 0, 0));
		panel3Jogadores.add(labelAvisoJogadores);
		labelAvisoJogadores.setVisible(false);
		getContentPane().add(panel3Jogadores, BorderLayout.SOUTH);
		panel3Jogadores.setBackground(Color.ORANGE);

	}
	public boolean verificarDados() {
		boolean r = false;

		if (!textJogador1Jogadores.getText().trim().isEmpty() && !textJogador2Jogadores.getText().trim().isEmpty()
				&& !labelId1Jogadores.getText().trim().isEmpty() && !labelId2Jogadores.getText().trim().isEmpty()) {

			try {

				Integer.parseUnsignedInt(textId1Jogadores.getText().replaceAll(" ", ""));
				Integer.parseUnsignedInt(textId2Jogadores.getText().replaceAll(" ", ""));

				r = true;
			} catch (NumberFormatException e) {

				r = false;

			}

		}

		return r;
	}
	public JButton getBtn1Jogadores() {
		return this.btn1Jogadores;
	}
	public JButton getBtn2Jogadores() {
		return this.btn2Jogadores;
	}
	public JTextField getTextId1Jogadores() {
		return this.textId1Jogadores;
	}
	public JTextField getTextId2Jogadores() {
		return this.textId2Jogadores;
	}
	public JLabel getLabelAvisoJogadores() {
		return this.labelAvisoJogadores;
	}

}
