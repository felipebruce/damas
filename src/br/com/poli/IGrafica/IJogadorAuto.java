package br.com.poli.IGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;

public class IJogadorAuto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel_JogadorAutonomo = new JPanel();
	private JPanel panel_btn_JogadorAutonomo = new JPanel();
	private JPanel panel_AvisoAutonomo = new JPanel();
	private JLabel label_JogadorAutonomo = new JLabel("Jogador 1: ");
	private JLabel labelAvisoAutonomo = new JLabel("CAMPO PREENCHIDO ERRADO!");
	public static JTextField textJogadorAutonomo = new JTextField();
	private JLabel labelId1_jogadorAutonomo = new JLabel("Idade: ");
	private JTextField textIdJogadorAutonomo = new JTextField();
	private JButton iniciarJogadorAutonomo = new JButton("INICIAR");
	private JButton voltarJogadorAutonomo = new JButton("VOLTAR");

	public IJogadorAuto() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		textJogadorAutonomo.setColumns(10);
		textIdJogadorAutonomo.setColumns(5);
		label_JogadorAutonomo.setFont(new Font("Tahoma", Font.BOLD, 18));

		labelId1_jogadorAutonomo.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelId1_jogadorAutonomo.setHorizontalAlignment(SwingConstants.CENTER);
		FlowLayout flowLayout_1 = (FlowLayout) panel_JogadorAutonomo.getLayout();
		flowLayout_1.setVgap(120);
		panel_JogadorAutonomo.add(label_JogadorAutonomo);
		panel_JogadorAutonomo.add(textJogadorAutonomo);
		panel_JogadorAutonomo.add(labelId1_jogadorAutonomo);
		panel_JogadorAutonomo.add(textIdJogadorAutonomo);
		panel_JogadorAutonomo.setBackground(Color.ORANGE);

		FlowLayout flowLayout = (FlowLayout) panel_btn_JogadorAutonomo.getLayout();
		flowLayout.setHgap(30);
		flowLayout.setVgap(100);
		iniciarJogadorAutonomo.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_btn_JogadorAutonomo.add(iniciarJogadorAutonomo);
		voltarJogadorAutonomo.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_btn_JogadorAutonomo.add(voltarJogadorAutonomo);
		labelAvisoAutonomo.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelAvisoAutonomo.setForeground(new Color(128, 0, 0));
		FlowLayout flowLayout_2 = (FlowLayout) panel_AvisoAutonomo.getLayout();
		flowLayout_2.setVgap(10);
		panel_AvisoAutonomo.add(labelAvisoAutonomo);
		labelAvisoAutonomo.setVisible(false);
		panel_btn_JogadorAutonomo.setBackground(Color.ORANGE);
		panel_AvisoAutonomo.setBackground(Color.ORANGE);
		getContentPane().add(panel_JogadorAutonomo, BorderLayout.NORTH);
		getContentPane().add(panel_AvisoAutonomo, BorderLayout.CENTER);
		getContentPane().add(panel_btn_JogadorAutonomo, BorderLayout.SOUTH);

	}

	public boolean verificarDadosAutonomo() {
		boolean r = false;

		if (!textJogadorAutonomo.getText().trim().isEmpty() && !labelId1_jogadorAutonomo.getText().trim().isEmpty()) {

			try {

				Integer.parseUnsignedInt(textIdJogadorAutonomo.getText().replaceAll(" ", ""));

				r = true;
			} catch (NumberFormatException e) {

				r = false;

			}

		}

		return r;
	}

	public JButton getIniciarJogadorAutonomo() {
		return this.iniciarJogadorAutonomo;
	}

	public JButton getVoltarJogadorAutonomo() {
		return this.voltarJogadorAutonomo;
	}

	public JTextField getTextIdJogadorAutonomo() {
		return this.textIdJogadorAutonomo;
	}
	public JLabel getLabelAvisoAutonomo() {
		return this.labelAvisoAutonomo;
	}

}
