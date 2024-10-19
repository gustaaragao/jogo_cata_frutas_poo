package modelo.utils.perlin;

import interfaceVisual.componentes.BtnCelulaTerreno;
import modelo.arquivo.GerenciadorMapaArquivo;
import modelo.entidades.CelulaTerreno;
import modelo.mapa.Mapa;
import modelo.mapa.MapaConfiguracao;
import modelo.utils.GeradorRuidoFlores;
import modelo.utils.Randomizador;

import javax.swing.*;

public class TestePerlin {
	public static void main(String[] args) {
		/* SETUP */
		MapaConfiguracao mapaConfig = GerenciadorMapaArquivo.importarArquivoTerreno("input.txt");

		assert mapaConfig != null;
		Mapa mapa = new Mapa(mapaConfig, 2);

		CelulaTerreno[][] floresta = mapa.getFloresta();

		int dimensao = mapa.getDimensao(); // Varia entre 5 a 12

		GeradorRuidoFlores geradorRuidoFlores = new GeradorRuidoFlores(dimensao);

		String pacoteTextura = Randomizador.sortearPacoteTextura();

		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(800,800);
		frame.setLayout(null);
		frame.setVisible(true);

		for (int i = 0; i < dimensao; i++) {
			for (int j = 0; j < dimensao; j++) {
				int posicaoX = i * 50;
				int posicaoY = j * 50;

				BtnCelulaTerreno btnCelulaTerreno = new BtnCelulaTerreno(
						floresta[i][j], pacoteTextura, posicaoX, posicaoY
				);

				geradorRuidoFlores.posicionarFloresBloco(btnCelulaTerreno);

				frame.add(btnCelulaTerreno);

				frame.revalidate();
				frame.repaint();
			}
		}
	}
}