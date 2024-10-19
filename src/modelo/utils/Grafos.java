package modelo.utils;

/*
 * Fontes:
 * - https://www.youtube.com/watch?v=OOuBM2kFghU
 * - https://www.youtube.com/watch?v=hMIrQdX4BkE
 */
public class Grafos {

    // Função para rotularizar componentes conexos usando vizinhança-4
    public static int[][] rotularizarComponentesConexosVizinhanca4(int[][] matrizBinaria) {
        int numLinhas = matrizBinaria.length;
        int numColunas = matrizBinaria[0].length;
        int[][] matrizRotularizada = new int[numLinhas][numColunas];
        
        int rotulo = 1;
        
        for (int i = 0; i < numLinhas; i++) {
            for (int j = 0; j < numColunas; j++) {
                // Vamos rotularizar apenas pixels do Foreground
                if (matrizBinaria[i][j] != 0 && matrizRotularizada[i][j] == 0) {
                    // Iniciar a rotulagem para este componente
                    dfsVizinhos4(matrizBinaria, matrizRotularizada, i, j, rotulo);
                    rotulo++;  // Próximo rótulo
                }
            }
        }
        
        return matrizRotularizada;
    }
    
    // Função recursiva de DFS para vizinhança-4
    private static void dfsVizinhos4(int[][] matrizBinaria, int[][] matrizRotularizada, int x, int y, int rotulo) {
        int numLinhas = matrizBinaria.length;
        int numColunas = matrizBinaria[0].length;

        // Verificar se o pixel está dentro dos limites e se faz parte do Foreground
        if (x < 0 || x >= numLinhas || y < 0 || y >= numColunas || matrizBinaria[x][y] == 0 || matrizRotularizada[x][y] != 0) {
            return;
        }

        // Rotularizar o pixel atual
        matrizRotularizada[x][y] = rotulo;

        // Verificar os 4 vizinhos (cima, baixo, esquerda, direita)
        dfsVizinhos4(matrizBinaria, matrizRotularizada, x - 1, y, rotulo);  // cima
        dfsVizinhos4(matrizBinaria, matrizRotularizada, x + 1, y, rotulo);  // baixo
        dfsVizinhos4(matrizBinaria, matrizRotularizada, x, y - 1, rotulo);  // esquerda
        dfsVizinhos4(matrizBinaria, matrizRotularizada, x, y + 1, rotulo);  // direita
    }

    // Função para rotularizar componentes conexos usando vizinhança-8
    public static int[][] rotularizarComponentesConexosVizinhanca8(int[][] matrizBinaria) {
        int numLinhas = matrizBinaria.length;
        int numColunas = matrizBinaria[0].length;
        int[][] matrizRotularizada = new int[numLinhas][numColunas];
        
        int rotulo = 1;

        for (int i = 0; i < numLinhas; i++) {
            for (int j = 0; j < numColunas; j++) {
                if (matrizBinaria[i][j] != 0 && matrizRotularizada[i][j] == 0) {
                    // Iniciar a rotulagem para este componente
                    dfsVizinhos8(matrizBinaria, matrizRotularizada, i, j, rotulo);
                    rotulo++;
                }
            }
        }
        
        return matrizRotularizada;
    }

    // Função recursiva de DFS para vizinhança-8
    private static void dfsVizinhos8(int[][] matrizBinaria, int[][] matrizRotularizada, int x, int y, int rotulo) {
        int numLinhas = matrizBinaria.length;
        int numColunas = matrizBinaria[0].length;

        // Verificar se o pixel está dentro dos limites e se faz parte do Foreground
        if (x < 0 || x >= numLinhas || y < 0 || y >= numColunas || matrizBinaria[x][y] == 0 || matrizRotularizada[x][y] != 0) {
            return;
        }

        // Rotularizar o pixel atual
        matrizRotularizada[x][y] = rotulo;

        // Verificar os 8 vizinhos (cima, baixo, esquerda, direita e diagonais)
        dfsVizinhos8(matrizBinaria, matrizRotularizada, x - 1, y, rotulo);  // cima
        dfsVizinhos8(matrizBinaria, matrizRotularizada, x + 1, y, rotulo);  // baixo
        dfsVizinhos8(matrizBinaria, matrizRotularizada, x, y - 1, rotulo);  // esquerda
        dfsVizinhos8(matrizBinaria, matrizRotularizada, x, y + 1, rotulo);  // direita
        dfsVizinhos8(matrizBinaria, matrizRotularizada, x - 1, y - 1, rotulo);  // diagonal superior esquerda
        dfsVizinhos8(matrizBinaria, matrizRotularizada, x - 1, y + 1, rotulo);  // diagonal superior direita
        dfsVizinhos8(matrizBinaria, matrizRotularizada, x + 1, y - 1, rotulo);  // diagonal inferior esquerda
        dfsVizinhos8(matrizBinaria, matrizRotularizada, x + 1, y + 1, rotulo);  // diagonal inferior direita
    }
}
