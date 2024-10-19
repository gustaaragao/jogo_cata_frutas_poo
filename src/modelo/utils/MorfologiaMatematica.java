package modelo.utils;

public class MorfologiaMatematica {

    // Função para aplicar erosão em uma matriz binária
    public static int[][] erosao(int[][] imagem, int[][] elementoEstruturante) {
        int linhas = imagem.length;
        int colunas = imagem[0].length;
        int[][] resultado = new int[linhas][colunas];
        
        // Tamanho do elemento estruturante
        int tamLinhas = elementoEstruturante.length;
        int tamColunas = elementoEstruturante[0].length;
        int kr = tamLinhas / 2;
        int kc = tamColunas / 2;

        // Aplicar erosão
        for (int i = kr; i < linhas - kr; i++) {
            for (int j = kc; j < colunas - kc; j++) {
                boolean erodirPixel = true;
                // Percorrer a vizinhança
                for (int ki = 0; ki < tamLinhas; ki++) {
                    for (int kj = 0; kj < tamColunas; kj++) {
                        if (elementoEstruturante[ki][kj] == 1 && imagem[i - kr + ki][j - kc + kj] == 0) {
                            erodirPixel = false;
                            break;
                        }
                    }
                    if (!erodirPixel) break;
                }
                resultado[i][j] = erodirPixel ? 1 : 0;
            }
        }
        return resultado;
    }

    // Função para aplicar dilatação em uma matriz binária
    public static int[][] dilatacao(int[][] imagem, int[][] elementoEstruturante) {
        int linhas = imagem.length;
        int colunas = imagem[0].length;
        int[][] resultado = new int[linhas][colunas];

        // Tamanho do elemento estruturante
        int tamLinhas = elementoEstruturante.length;
        int tamColunas = elementoEstruturante[0].length;
        int kr = tamLinhas / 2;
        int kc = tamColunas / 2;

        // Aplicar dilatação
        for (int i = kr; i < linhas - kr; i++) {
            for (int j = kc; j < colunas - kc; j++) {
                boolean dilatarPixel = false;
                // Percorrer a vizinhança
                for (int ki = 0; ki < tamLinhas; ki++) {
                    for (int kj = 0; kj < tamColunas; kj++) {
                        if (elementoEstruturante[ki][kj] == 1 && imagem[i - kr + ki][j - kc + kj] == 1) {
                            dilatarPixel = true;
                            break;
                        }
                    }
                    if (dilatarPixel) break;
                }
                resultado[i][j] = dilatarPixel ? 1 : 0;
            }
        }
        return resultado;
    }
    
 // Função que aplica o abertura (dilatação seguida de erosão)
    public static int[][] abertura(int[][] imagem, int[][] elementoEstruturante) {
        // Aplicar dilatação
        int[][] dilatado = dilatacao(imagem, elementoEstruturante);
        // Aplicar erosão no resultado da dilatação
        return erosao(dilatado, elementoEstruturante);
    }
    
    // Função que aplica o fechamento (erosão seguida de dilatação)
    public static int[][] fechamento(int[][] imagem, int[][] elementoEstruturante) {
        // Aplicar erosão
        int[][] erodido = erosao(imagem, elementoEstruturante);
        // Aplicar dilatação no resultado da erosão
        return dilatacao(erodido, elementoEstruturante);
    }
}