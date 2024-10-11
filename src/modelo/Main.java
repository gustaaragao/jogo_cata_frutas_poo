package modelo;

import interfaceVisual.telasStatePattern.Frame;

import interfaceVisual.telasStatePattern.Jogo;
import modelo.arquivo.GerenciadorMapaArquivo;
import modelo.mapa.*;
import modelo.utils.Randomizador;

/**
 * Essa classe é atualmente o ponto de partida do programa.
 * Sua única função é iniciar a interface gráfica com o jogador.
 * 
 *
 * @see interfaceVisual.telasStatePattern.Frame
 */
public class Main {

	public static void main(String[] args) {
		Frame frame = new Frame();
	}
}


// Essa parte é destinada aos programadores que venham a alterar parte do código
// use "{@link nome_da_classe}" no javadoc para linkar inline uma classe.
// ->Caso queira usar o método de uma classe, use nome_da_classe#metodo(parametros)
// -> Use @see no final do comentário para linkar com alguma parte do documento
// -> Use @return para comentar sobre o retorno.
// -> Use @param para comentar sobre o parametro, diga o nome do parametro após @param. 
//
//
// => Lembrar que deve ser dado o caminho completo (acho) para que o see e o link funcionem.
