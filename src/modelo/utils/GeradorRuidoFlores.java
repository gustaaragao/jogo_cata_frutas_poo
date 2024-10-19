package modelo.utils;

import interfaceVisual.componentes.BtnCelulaTerreno;
import modelo.entidades.Grama;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class GeradorRuidoFlores {
    private final double[][] ruidoMatriz;
    
    private final double LIMIAR_FLORES = 0.5;
    
    private final boolean DEBUG = true;
    
    public GeradorRuidoFlores(int dimensao) {
        this.ruidoMatriz = gerarMatriz(dimensao, 50);
    }

    private double[][] gerarMatriz(int dimensao, int tamanhoBloco) {
        int tamanhoMapa = dimensao * tamanhoBloco;

        PerlinNoise perlinNoise = new PerlinNoise();

        double[][] ruidoMatriz = new double[tamanhoMapa / 10][tamanhoMapa / 10];

        for (int i = 0; i < ruidoMatriz.length; i++) {
            for (int j = 0; j < ruidoMatriz[0].length; j++) {
                double ruido = perlinNoise.noise(i, j); // [-1, 1]
                
                double ruidoAjustado = (ruido + 1) / 2;
                
                if (DEBUG) {
                	if (ruidoAjustado > LIMIAR_FLORES) // REVER ISSO AQUI
                		System.out.print("F ");
                	else
                		System.out.print(". ");                	
                }
                
                ruidoMatriz[i][j] = ruidoAjustado;
            }
            System.out.println();
        }

        return ruidoMatriz;
    }

    public void posicionarFloresBloco(BtnCelulaTerreno btnCelulaTerreno) {
        if (btnCelulaTerreno.getCelulaTerreno() instanceof Grama grama) {
            // Pegue a posição X e Y do bloco de 50x50 no grid
            int indiceBlocoX = btnCelulaTerreno.getPosicaoX() / 50;
            int indiceBlocoY = btnCelulaTerreno.getPosicaoY() / 50;
            
            // Transforme para a posição da célula menor de 10x10
            int posicaoX = indiceBlocoX * 5;
            int posicaoY = indiceBlocoY * 5;

            // Iterar sobre as subcélulas do bloco
            for (int subX = 0; subX < 5; subX++) {
                for (int subY = 0; subY < 5; subY++) {
                    // Calcular a posição dentro da matriz de ruído
                    double ruidoFlor = ruidoMatriz[posicaoX + subX][posicaoY + subY];
                    
                    if (DEBUG)
                    	System.out.printf("Bloco (%d, %d) Subcélula (%d, %d)\n", indiceBlocoX, indiceBlocoY, subX, subY);

                    if (ruidoFlor > LIMIAR_FLORES) {
                        btnCelulaTerreno.posicionarFlor(subX * 10, subY * 10, "ciano");
                    }
                }
            }

            // Verifique se há uma fruta ocupante e combine com a grama
            if (grama.getFrutaOcupante() != null) {
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
