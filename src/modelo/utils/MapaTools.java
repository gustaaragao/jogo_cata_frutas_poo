package modelo.utils;

import modelo.MovimentoJogador.RelacaoPeso;
import modelo.entidades.*;
import modelo.mapa.Mapa;
import modelo.tipos.Coordenada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

/**
* Esta classe tem o intuito de simplificar e generalizar algumas funções úteis ao se trabalhar com o mapa do jogo
* Funciona como um util.Scanner sendo necessário instanciar um objeto dela com o mapa em questão.
* 
*/
public class MapaTools {

    /**
     * A floresta em que se deseja aplicar os métodos da classe.
     */
    private CelulaTerreno[][] floresta;

    /**
     * Dimensao da floresta instanciada.
     */
    int dimensao;
    
    /**
     * O construtor recebe uma floresta e uma dimensao.
     *
     * @param floresta A matriz de {@link modelo.entidades.CelulaTerreno}.
     * @param dimensao A dimensao da floresta.
     */
    public MapaTools(CelulaTerreno[][] floresta, int dimensao){
        this.floresta = floresta;
        this.dimensao = dimensao;
    }


    /**
     * Retorna com um cast genérico a célula de terreno da Matriz floresta na Coordenada Dada.
     *
     * @param c Coordenada contendo a posição x e y de interesse.
     *
     * @see modelo.tipos.Coordenada
     */
    public CelulaTerreno celulaEm(Coordenada c) {
        CelulaTerreno[] linha = floresta[c.getI()];
        return linha[c.getJ()];
    }

    // GerarCoordenadas ----------------------------------------------------

  
    /**
     * Retorna uma coordenada aleatória.
     *
     * @return {@code new Coordenada(x,y)} 
     */
    public Coordenada gerarCoordenada(){
        Random r = new Random();
        int i, j;
        i = r.nextInt(dimensao);
        j = r.nextInt(dimensao);
        return new Coordenada(i, j);
    }

  
    /**
     * Retorna uma coordenada que necessariamente deve ser uma grama.
     * Deve se atentar ao fato de que essa função não verifica a existência de uma fruta 
     * na posição retornada.
     * 
     * Para eventuais checagens a melhor função para verificar posições completamente livres é
     * a função {@link modelo.utils.MapaTools#gerarCoordenadaValidaFruta}
     */
    public Coordenada gerarCoordenadaValida() {
        boolean livre;
        Coordenada c;
        do {
            c = gerarCoordenada();
            livre = celulaEm(c) instanceof Grama;
        } while (!livre);
        return c;
    }

  
    /**
     * Retorna uma coordenada da floresta que além de grama deve estar livre de frutas.
     * Observar que este método não verifica a existência de um jogador ocupante.
     */
    public Coordenada gerarCoordenadaValidaFruta() {
        Coordenada c;
        do {
             c = gerarCoordenadaValida();
        } while (((Grama) celulaEm(c)).getFrutaOcupante() != null);
        return c;
    }

  
    /**
     * Procura uma coordenada do tipo grama, e então verifica se não há jogadores naquela posição, e se a posição não é uma árvore.
     */
    public Coordenada gerarCoordenadaValidaJogador() {
        boolean livre;
        Coordenada c;
        do {
            c = gerarCoordenada();
            livre = celulaEm(c).getJogadorOcupante() == null && !(celulaEm(c) instanceof Arvore);
        } while (!livre);
        return c;
    }
    // -----------------------------------------------------------------------



    // Visualização no terminal ----------------------------------------------
  
    /**
     * Exibe no terminal a forma em string, formatada, da célula dada.
     *
     * @param celula CelulaTerreno para exibir.
     */
    public void printarCelula(CelulaTerreno celula) {
        String celulaStr = celula.toString();

        System.out.print(String.format("%-3s", celulaStr)); // Ajusta à esquerda para garantir o alinhamento
    }

  
    /**
     * Exibe uma linha completa formatada da floresta.
     *
     * @see modelo.utils.MapaTools#printarCelula
     */
    public void printarLinha(CelulaTerreno[] linha) {
        for (CelulaTerreno celula : linha) {
            printarCelula(celula);
            System.out.print(" | "); // Separador entre as células
        }
    }

  
    /**
     * Formata da forma adequada e exibe por completo a floresta instanciada na classe.
     */
    public void visualizarTerreno() {
        // Imprime cada linha com o índice da linha correspondente
        for (int i = 0; i < dimensao; i++) {
            System.out.printf("%3d | ", i); // Impressão do índice da linha

            // Imprime as células da linha
            CelulaTerreno[] linha = floresta[i];

            printarLinha(linha);

            System.out.println();
        }
    }
    // ------------------------------------------------------------




    //TODO: Verificar se a forma de string pode ser útil na geração do gráfico e alterá-la confome necessário

    // Retorno Como String ------------------------------------------
  
    /**
     * Formata e retorna uma única célula da floresta como string.
     * 
     * @param celula CelulaTerreno fornecida para formatação para String.
     *
     * @return String formadata da célula dada.
     */
    public String formatarCelula(CelulaTerreno celula) {
        String celulaStr = celula.toString();
        return String.format("%-2s", celulaStr);
    }

  
    /**
     * Formata e retorna uma linha completa da floresta instanciada.
     *
     * @param linha Um array simples do tipo CelulaTerreno.
     *
     * @return String com a linha formatada.
     *
     * @see modelo.utils.MapaTools#formatarCelula
     */
    public String formatarLinha(CelulaTerreno[] linha) {
        StringBuilder linhaBuilder = new StringBuilder();
        for (CelulaTerreno celula : linha) {
            linhaBuilder.append(formatarCelula(celula));
            linhaBuilder.append(" | "); // Adiciona o separador entre as células
        }
        return linhaBuilder.toString(); // Retorna a linha formatada
    }

  
    /**
     * Retorna a string completa com todas as células da floresta formatadas e em sequência.
     * Pode ser utilizada para faclitar o manuseio e exibição do terreno.
     *
     * @return String contendo a floresta formatada.
     */
    public String FlorestaToString() {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < dimensao; i++) {
                builder.append(String.format("%3d | ", i));

                CelulaTerreno[] linha = floresta[i];

                builder.append(formatarLinha(linha));
                builder.append("\n");
            }
            return builder.toString();
    }
    // ------------------------------------------------------------


}


// -> Será útil quando precisarmos por exemplo colocar as frutas no chão após serem derrubadas, podemos criar um subconjunto
// -> da floresta e instanciar um objeto dessa classe, e usar as funções dentro dos limites alternativos.
//
// -> Na linha 118 verificar se seria mais viável utilizar uma coordenada como parâmetro para podermos utilizar a função 
//    iterando sobre as coordenadas. Verificar a necessidade de sobrecarregar o método.
