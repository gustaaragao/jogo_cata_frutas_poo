package modelo.utils;

import interfaceVisual.componentes.BtnCelulaTerreno;
import modelo.entidades.Grama;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class GeradorRuidoFlores {
    private final double[][] ruidoMatriz;
    
    private final int LARGURA_FLOR = 10;
    private final int ALTURA_FLOR = 10;
    
    public GeradorRuidoFlores(int dimensao) {
        this.ruidoMatriz = gerarMatriz(dimensao, 50);
    }

    private double[][] gerarMatriz(int dimensao, int tamanhoBloco) {
        int tamanhoMapa = dimensao * tamanhoBloco;

        PerlinNoise perlinNoise = new PerlinNoise();

        double[][] ruidoMatriz = new double[tamanhoMapa / LARGURA_FLOR][tamanhoMapa / ALTURA_FLOR];

        for (int i = 0; i < ruidoMatriz.length; i++) {
            for (int j = 0; j < ruidoMatriz[0].length; j++) {
                double ruido = perlinNoise.noise(i, j); // [-1, 1]
                
                double ruidoAjustado = (ruido + 1) / 2;
                
                if (ruidoAjustado > 0.5) // REVER ISSO AQUI
                	System.out.print("F ");
                else
                	System.out.print(". ");
                
                ruidoMatriz[i][j] = ruidoAjustado;
            }
            System.out.println();
        }

        return ruidoMatriz;
    }

    public void posicionarFloresBloco(BtnCelulaTerreno btnCelulaTerreno) {
        if (btnCelulaTerreno.getCelulaTerreno() instanceof Grama grama) {
        	int posicaoX = btnCelulaTerreno.getPosicaoX() * 10;
        	int posicaoY = btnCelulaTerreno.getPosicaoY() * 10;
        	for (int florX = posicaoX; florX < this.ruidoMatriz.length; florX++) {
        		for (int florY = posicaoY; florY < this.ruidoMatriz[0].length; florY++) {
        			double ruidoFlor = ruidoMatriz[florX][florY];
        			
        			if (ruidoFlor > 0.5) {
        				btnCelulaTerreno.posicionarFlor(
        						(florX % 5), 
        						(florY % 5), 
        						"ciano"
        				);
        			}
        		}
        	}

            if (grama.getFrutaOcupante() != null) {
                // Caso haja uma fruta naquela célula -> Combinar o sprite da fruta com o da grama.
                String nomeFruta = grama.getFrutaOcupante().getClass().getSimpleName().toLowerCase();

                String caminhoFruta = "/interfaceVisual/imagens/frutas/" + nomeFruta + ".png";

                ImageIcon iconFruta = new ImageIcon(this.getClass().getResource(caminhoFruta));

                ImageIcon iconGrama = btnCelulaTerreno.getCelulaIcon();

                BufferedImage imagemCombinada = Imagem.combinarImagens(iconGrama, iconFruta);

                btnCelulaTerreno.setCelulaIcon(new ImageIcon(imagemCombinada));
            }
        }
    }
}
