package modelo.utils;

import interfaceVisual.componentes.BtnCelulaTerreno;
import modelo.entidades.Grama;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class GeradorFlores {
    private final int[][] matrizFlores;
    
    private final int[][] matrizFloresRotularizada;
    
    private final double LIMIAR_FLORES = 0.6;
    
    private final boolean DEBUG = true;
    
    public GeradorFlores(int dimensao) {
        this.matrizFlores = gerarMatrizFlores(dimensao, 50);
        
        this.matrizFloresRotularizada = Grafos.rotularizarComponentesConexosVizinhanca4(matrizFlores);
        
        if (DEBUG) {
        	for (int i = 0; i < matrizFloresRotularizada.length; i++) {
        		for (int j = 0; j < matrizFloresRotularizada[0].length; j++) {
        			System.out.printf("%d ", matrizFloresRotularizada[i][j]);
        		}        	
        		System.out.println();
        	}        	
        }
    }
    
    private int[][] gerarMatrizFlores(int dimensao, int tamanhoBloco) {
        int tamanhoMapa = dimensao * tamanhoBloco;

        PerlinNoise perlinNoise = new PerlinNoise();

        int[][] matrizFlores = new int[tamanhoMapa / 10][tamanhoMapa / 10];

        for (int i = 0; i < matrizFlores.length; i++) {
            for (int j = 0; j < matrizFlores[0].length; j++) {
                double ruido = perlinNoise.noise(i, j); // [-1, 1]
                
                double ruidoAjustado = (ruido + 1) / 2; // [0, 1]
                
                if (ruidoAjustado > LIMIAR_FLORES)
                	matrizFlores[i][j] = 1;
                else
                	matrizFlores[i][j] = 0;
                
                if (DEBUG) {
                	System.out.printf("%s ", matrizFlores[i][j] == 1 ? "F" : ".");
                }
            }
            if (DEBUG)
            	System.out.println();
        }

        return matrizFlores;
    }
    
    
    public void posicionarFloresBloco(BtnCelulaTerreno btnCelulaTerreno) {
        if (btnCelulaTerreno.getCelulaTerreno() instanceof Grama grama) {
            // Pegue a posição X e Y do bloco de 50x50 no grid (é o indíce na matriz de blocos (0, 0), (0, 1), ...)
            int indiceBlocoX = btnCelulaTerreno.getPosicaoX() / 50;
            int indiceBlocoY = btnCelulaTerreno.getPosicaoY() / 50;
            
            // Transforme para a posição da célula menor de 10x10
            int posicaoX = indiceBlocoX * 5;
            int posicaoY = indiceBlocoY * 5;

            // Iterar sobre as subcélulas do bloco
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (this.matrizFlores[posicaoY + j][posicaoX + i] == 1) {
                        btnCelulaTerreno.posicionarFlor(j * 10, i * 10, "ciano");
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
