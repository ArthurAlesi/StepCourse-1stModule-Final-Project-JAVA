
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println("hello world");

		Jogo jogo = new Jogo();
	}

}


class Jogo {
	private List<Palavra> palavraLista = new ArrayList();
	private int numero;
	private String pal;
	private int chance;
	private boolean acertou;
	private boolean ganhou;

	Jogo() {
		System.out.println();
		System.out.println();
		System.out.println("	OBS: O JOGO NAO TEM ACENTO OU Ç");
		System.out.println();
		System.out.println();
// jogar();
		ganhou = false;
		chance = 6;
		numero = sortearNumero();
		lerTexto(numero);
		palavraLista.add(new PalavraSorteada(pal));
		palavraLista.add(new PalavraManipulada(pal));
		palavraLista.add(new PalavraErrada());

		jogar();
		defineVitoria();

		System.out.println("A palavra foi " + pal);
	}

	private void jogar() {
		boolean game = true;
		char letraJogada;
		Scanner sc = new Scanner(System.in);

// imprimePalavraAdivinhando(); // <<<<<<<<<<<<<<<<<<<
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=--=");
		while (chance > 0 && ganhou == false) {
			System.out.println("Voce possui " + chance + " chances");
			acertou = false;
			System.out.println();
			imprimePalavraDaLista(1);
			System.out.println("\ndiga a letra");
			letraJogada = sc.next().charAt(0);
// for(int i = 0; i < getTamanhoPalavra( this); i++) {
// CONTINUAR A PARTIR DAQUI

// char char0 = getCaracter(0,i);
// char char1 = getCaracter(1,i);

//	System.out.println("char 0 é " + char0);
//	System.out.println("char 1 é " + char1);

// }

//	for(int i = 0; i< palavraLista.get(0).letraLista.size(); i++) {
//	System.out.print(getCaracter(0,i) + " ");
//	}

//	for(int i = 0; i< palavraLista.get(0).letraLista.size(); i++) {
//	System.out.print(getCaracter(1,i) + " ");
//	}
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=--=");
			setLetraJogada(letraJogada);
			diminuiChance(letraJogada);
			System.out.print("As letras erradas são");
			if (getPalavra(2).letraLista.size() == 1)
				System.out.print(":   nenhuma letra errada ate agora");
			else
				imprimePalavraDaLista(2);
			System.out.println();
			fimJogo();
		}
		sc.close();

	}

	private int getTamanhoPalavra(Jogo j) {
		return j.palavraLista.get(1).letraLista.size();

	}

	private char getCaracter(int i, int nu2) {
		return palavraLista.get(i).letraLista.get(nu2).getLetra();

	}

	private void defineVitoria() {
		if (ganhou) {
			System.out.println("Voce ganhou");

		} else
			System.out.println("Voce perdeu");
	}

	private Palavra getPalavra(int n) {
		return palavraLista.get(n);
	}

	private void setLetraJogada(char ch) {
		for (int t = 0; t < getTamanhoPalavra(this); t++) {
// System.out.println("o tamanho da palavra foi " + getTamanhoPalavra(this));
// System.out.println("caracter = " + getCaracter(0,0));
			if (getCaracter(0, t) == ch) {
				getPalavra(1).letraLista.get(t).setLetra(ch);
				;
				setAcertouTrue();
			}
		}
		System.out.println();
		if (acertou == true) {
			System.out.println(" --Acertou a letra " + ch);
		} else {
			boolean letraInErrada = false;
			for (Letra l : getPalavra(2).letraLista) {
				if (l.getLetra() == ch) {
					letraInErrada = true;
				}
			}
			if (letraInErrada == false) {
				getPalavra(2).letraLista.add(new Letra(ch));
			}
		}

	}

	private void setAcertouTrue() {
		acertou = true;
	}

	private void diminuiChance(char c) {
		if (acertou == false) {
			System.out.println(" --Errou a letra " + c);
			chance -= 1;
		}
	}

	private void fimJogo() {
		int cont = 0;
		for (int i = 0; i < getTamanhoPalavra(this); i++) {
			if (getPalavra(0).letraLista.get(i).getLetra() == getPalavra(1).letraLista.get(i).getLetra()) {
				cont += 1;
			}
		}
		if (cont == getTamanhoPalavra(this)) {
			ganhou = true;
		}
	}

	private String lerTexto(int nu) {
		try {
			FileReader arq = new FileReader("frutas.txt");
			BufferedReader lerArq = new BufferedReader(arq);
			for (int i = 1; i <= nu; i++) {
				pal = lerArq.readLine();
// System.out.println(pal); // <<<<<<<<<<<<<<<<
			}

			arq.close();
		} catch (IOException e) {

		} finally {

		}
		return pal;
	}

	private int sortearNumero() {
		Random gerador = new Random();
		int n = (gerador.nextInt(108) + 1);
// System.out.println(n); // <<<<<<<<<<<<<<<<
		return setNumero(n);
	}

	private int setNumero(int nambar) {
		return nambar;
	}

	private void imprimePalavraDaLista(int n) {
//for (int i = 0; i < palavraLista.get(1).letraLista.size(); i++) {
//	System.out.print(getCaracter(1, i) + " ");
//}

		for (Letra l : getPalavra(n).letraLista) {
			System.out.print(l.getLetra() + " ");
		}
	}

}

// ------------------------------------------------------------------------------------------------------

// ------------------------------------------------------------------------------------------------------

class Palavra {
	List<Letra> letraLista = new ArrayList();

	void addLetra(char c) {
		letraLista.add(new Letra(c));
	}

}

class PalavraSorteada extends Palavra {
	PalavraSorteada(String s) {
		for (int i = 0; i < s.length(); i++)
			this.addLetra(s.charAt(i));

	}
}

class PalavraManipulada extends Palavra {
	PalavraManipulada(String s) {
		for (int j = 0; j < s.length(); j++)
			this.addLetra('_');
	}
}

class PalavraErrada extends Palavra {

	PalavraErrada() {
		this.addLetra(':');
	}

	PalavraErrada(char c) {
		this.addLetra(c);
	}

}

class Letra {
	private char letra;

//
	Letra(char c) {
		setLetra(c);
	}

//
	char getLetra() {
		return this.letra;
	}

//
	void setLetra(char let) {
		this.letra = let;
	}
}