package br.com.poli.IGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import br.com.poli.dama.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FimJogo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelFim = new JPanel();
	private JPanel panelFim_Sair = new JPanel();
	private JPanel panelResultado = new JPanel();
	private JLabel labelFim = new JLabel();;
	private JLabel resultado = new JLabel();
	private JButton fim_Sair = new JButton("SAIR");
	private ImageIcon fimDeJogo = new ImageIcon("fim.png");

	public FimJogo() {
		setTitle("Jogo de Damas");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		labelFim = new JLabel(fimDeJogo);
		FlowLayout fl_panelFim = (FlowLayout) panelFim.getLayout();
		fl_panelFim.setHgap(30);
		fl_panelFim.setVgap(100);
		panelFim.add(labelFim);
		panelFim.setBackground(Color.ORANGE);
		getContentPane().add(panelFim, BorderLayout.NORTH);
		FlowLayout fl_panelFim_Sair = (FlowLayout) panelFim_Sair.getLayout();
		fl_panelFim_Sair.setHgap(30);
		fl_panelFim_Sair.setVgap(60);
		panelResultado.setLayout(new BoxLayout(panelResultado, BoxLayout.PAGE_AXIS));
		InicioJogo.jogo = new Jogo();
		if (InicioJogo.jogo.getContadorJogadas() == 20) {
			resultado.setText("EMPATE");
		} else {
			resultado.setText("VENCEDOR: " + ControladorIGrafica.jogo.getVencedor().getNome());
		}
		resultado.setAlignmentX(0.5f);
		resultado.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelResultado.add(resultado);
		panelResultado.setBackground(Color.ORANGE);
		getContentPane().add(panelResultado, BorderLayout.CENTER);
		fim_Sair.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelFim_Sair.add(fim_Sair);
		panelFim_Sair.setBackground(Color.ORANGE);
		getContentPane().add(panelFim_Sair, BorderLayout.SOUTH);

	}

	public JButton getFim_Sair() {
		return this.fim_Sair;
	}
}
