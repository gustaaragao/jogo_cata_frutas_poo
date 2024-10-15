package modelo.utils.perlin;

import modelo.tipos.Vetor2D;

import java.util.HashMap;
import java.util.Map;

/*
*
* Fontes:
* - https://adrianb.io/2014/08/09/perlinnoise.html
*
* */
public class PerlinNoise {
	// Tabela de hash padrão conforme definida por Ken Perlin. Este é um array
	// arranjado aleatoriamente com todos os números de 0 a 255, incluídos duas vezes.
	private static int[] permutation = { 
			151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 
		    21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 
		    237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 
		    83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 
		    216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 
		    173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 
		    47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 
		    167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 
		    251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 
		    31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 
		    29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180, 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 
		    194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 
		    197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 
		    175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 
		    41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 
		    200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 
		    38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213, 
		    119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 
		    79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 
		    241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 
		    50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 
		    156, 180
		};
	
    /***
     * Função de hashing baseada na função fornecida em
     * https://www.scratchapixel.com/lessons/procedural-generation-virtual-worlds/perlin-noise-part-2,
     * que é baseada na original de Ken Perlin.
     * @param x coordenada x do ponto para gerar o hash
     * @param y coordenada y do ponto para gerar o hash
     * @return valor na faixa [0, 255] da tabela de hash
     */
    private static int hash(int x, int y) {
        x = x & 255;  // mesmo que % 256, mas mais rápido
        y = y & 255;
        return permutation[permutation[x] + y];
    }
    
    /**
     * Para qualquer coordenada (x, y) fornecida, seleciona pseudorandomicamente um vetor de gradiente
     * usando o método de hash.
     * @param canto O vetor que representa as coordenadas de um canto do quadrado unitário.
     * @return O vetor de gradiente para esse ponto, no formato {x, y}, onde
     *         o vetor é medido com sua origem em (0, 0) e sua extremidade em
     *         (x, y).
     */
    public static Vetor2D<Integer> selecionarVetorGradiente(Vetor2D<Integer> canto) {
        // Vetores de gradiente válidos (quatro direções possíveis)
        final int[][] vetoresGradienteValidos = {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

        // Retorna um vetor de gradiente com base no valor de hash(x, y) % 4 (usando bitwise & 3 para ser mais rápido)
        int indice = hash(canto.getX(), canto.getY()) & 3;

		return new Vetor2D<>(vetoresGradienteValidos[indice][0], vetoresGradienteValidos[indice][1]);
    }
    
	public static double perlin(double x, double y) {
		/* ETAPA 1: Calcular o produto escalar entre o vetor de Distância e Gradiente */
		/* CALCULANDO O QUADRADO UNITARIO DO PONTO (x, y) */
		Map<String, Vetor2D<Integer>> quadradoUnitario = new HashMap<>();

		int x0, x1, y0, y1;
		x0 = (int) x;
		y0 = (int) y;
		x1 = x0 + 1;
		y1 = y0 + 1;

		quadradoUnitario.put("SuperiorEsquerdo", new Vetor2D<>(x0, y0));
		quadradoUnitario.put("SuperiorDireito", new Vetor2D<>(x1, y0));
		quadradoUnitario.put("InferiorEsquerdo", new Vetor2D<>(x0, y1));
		quadradoUnitario.put("InferiorDireito", new Vetor2D<>(x0, y1));

		// Produto escalar entre cada (distancia . gradiente) de cada canto.
		Map<String, Double> produtoEscalar = new HashMap<>();

		System.out.println("Ponto: " + x + ", " + y);

		for (String chave : quadradoUnitario.keySet()) {
			Vetor2D<Integer> canto = quadradoUnitario.get((chave));

			Vetor2D<Double> vetorDistancia = new Vetor2D<>(x - canto.getX(), y - canto.getY());

			Vetor2D<Integer> vetorGradiente = selecionarVetorGradiente(canto);

			// Calculando o resultado do produto escalar entre (distancia . gradiente)
			double resultado = vetorDistancia.getX() * vetorGradiente.getX() + vetorDistancia.getY() * vetorGradiente.getY();

			produtoEscalar.put(chave, resultado);

			System.out.println("Canto: " + canto.getX() + ", " + canto.getY());
			System.out.println("Distancia: " + vetorDistancia.getX() + ", " + vetorDistancia.getY());
			System.out.println("Gradiente: " + vetorGradiente.getX() + ", " + vetorGradiente.getY());
			System.out.println("Produto Escalar: " + resultado);
		}

		/* ETAPA 2: Interpolação Bilinear de Produtos de 4 Pontos para Valor Final */

		return 0.0;
	}

}
