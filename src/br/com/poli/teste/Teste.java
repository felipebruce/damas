package br.com.poli.teste;

import br.com.poli.dama.JogadorAutonomo;
import br.com.poli.dama.Jogo;

public class Teste {

	public static void main(String[] args) {

		Jogo j = new Jogo();
		j.iniciarPartida("Felipe", "18", "Isabela", "19");
		System.out.println(" ");
		System.out.println("Jogador 1: " + j.getJogador1().getNome() + "  " + "Idade: " + j.getJogador1().getIdade());
		System.out.println("Jogador 2: " + j.getJogador2().getNome() + "  " + "Idade: " + j.getJogador2().getIdade());
		JogadorAutonomo a = new JogadorAutonomo();
		a.setNome(j.getJogador1().getNome());
		a.setIdade(j.getJogador1().getIdade());

		JogadorAutonomo b = new JogadorAutonomo();
		b.setNome(j.getJogador1().getNome());
		b.setIdade(j.getJogador1().getIdade());

		while (j.isFimDeJogo() == false) {

			a.jogarAuto(j);
			System.out.println(j.getVezAtual());
			System.out.println(" ");

			try {

				Thread.sleep(10);

			} catch (InterruptedException e) {

			}

			b.jogarAuto(j);
			System.out.println(j.getVezAtual());

		}
	}
}