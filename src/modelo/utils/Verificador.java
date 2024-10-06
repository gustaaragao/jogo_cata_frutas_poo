package modelo.utils;

import modelo.entidades.*;

public class Verificador {

    public static boolean isVizinhanca4Livre(int x, int y, CelulaTerreno[][] floresta) {
        // maxX e maxY são utéis para verificar se a coordenada (x, y) está na borda do mapa.
        // Por exemplo, na coordenada (0, 1) só precisamos verificar se (0, 0), (0, 2) e (1, 1) estão livres.
        // Essas coordenadas correspodem, respectivamente às células à esquerda, à direita e abaixo de (0, 1).
        int maxX = floresta.length;
        int maxY = floresta[0].length;

        // Verificar a célula acima da coordenada (x, y)
        if (x - 1 >= 0 && !(floresta[x - 1][y] instanceof Grama)) {
            return false;
        }

        // Verificar a célula abaixo da coordenada (x, y)
        if (x + 1 < maxX && !(floresta[x + 1][y] instanceof Grama)) {
            return false;
        }

        // Verificar a célula à esquerda da coordenada (x, y)
        if (y - 1 >= 0 && !(floresta[x][y - 1] instanceof Grama)) {
            return false;
        }

        // Verificar a célula à direita da coordenada (x, y)
        if (y + 1 < maxY && !(floresta[x][y + 1] instanceof Grama)) {
            return false;
        }

        return true;
    }

    public static boolean isVizinhanca8Livre(int x, int y, CelulaTerreno[][] floresta) {
        // Verificar se a vizinhança 4 está livre.
        if (!isVizinhanca4Livre(x, y, floresta)) {
            return false;
        }

        // Vamos verificar as "quinas"/"cantos" da célula (x, y) estão livres
        int maxX = floresta.length;
        int maxY = floresta[0].length;

        // Verificar a célula acima-esquerda da coordenada (x, y)
        if (x - 1 >= 0 && y - 1 >= 0 && !(floresta[x - 1][y - 1] instanceof Grama)) {
            return false;
        }

        // Verificar a célula acima-direita da coordenada (x, y)
        if (x - 1 >= 0 && y + 1 < maxY && !(floresta[x - 1][y + 1] instanceof Grama)) {
            return false;
        }

        // Verificar a célula acima-esquerda da coordenada (x, y)
        if (x + 1 < maxX && y - 1 >= 0 && !(floresta[x + 1][y - 1] instanceof Grama)) {
            return false;
        }

        // Verificar a célula abaixo-direita da coordenada (x, y)
        if (x + 1 < maxX && y + 1 < maxY && !(floresta[x + 1][y + 1] instanceof Grama)) {
            return false;
        }

        return true;
    }

    public static boolean isGrama(CelulaTerreno celula) {
        return celula instanceof Grama;
    }

    public static boolean temFrutaNaGrama(CelulaTerreno celula) {
        if (isGrama(celula)) {
            return ((Grama) celula).getFrutaOcupante() != null;
        }
        return false;
    }

    public static boolean temJogadorNaGrama(CelulaTerreno celula) {
        if (isGrama(celula)) {
            return celula.getJogadorOcupante() != null;
        }
        return false;
    }

    public static boolean isPedra(CelulaTerreno celula) {
        return celula instanceof Pedra;
    }

    public static boolean temJogadorNaPedra(CelulaTerreno celula) {
        if (isPedra(celula)) {
            return celula.getJogadorOcupante() != null;
        }
        return false;
    }

    public static boolean isArvore(CelulaTerreno celula) {
        return celula instanceof Arvore;
    }

    public static boolean temJogadorNaArvore(CelulaTerreno celula) {
        if (isArvore(celula)) {
            return celula.getJogadorOcupante() != null;
        }
        return false;
    }

}
