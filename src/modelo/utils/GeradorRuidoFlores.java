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

        double[][] ruidoMatriz = new double[tamanhoMapa][tamanhoMapa]; 

        for (int i = 0; i < tamanhoMapa; i += LARGURA_FLOR) {
            for (int j = 0; j < tamanhoMapa; j += ALTURA_FLOR) {
                double ruido = perlinNoise.noise(i, j); // [-1, 1]
                
                double ruidoAjustado = (ruido + 1) / 2;
                
                if (ruidoAjustado > 0.5)
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
        	for (int florX = 0; florX < this.ruidoMatriz.length; florX++) {
        		for (int florY = 0; florY < this.ruidoMatriz[0].length; florY++) {
        			double ruidoFlor = ruidoMatriz[florX][florY];
        			
        			if (ruidoFlor >= 0.5) {
        				
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
