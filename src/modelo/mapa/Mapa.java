package modelo.mapa;

import modelo.entidades.*;
import modelo.tipos.*;
import modelo.utils.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * A classe mapa é a classe que representa o mapa do jogo, precisa de uma configuração para ser inicializada.
 *
 * @see modelo.mapa.MapaConfiguracao
 */
public class Mapa {

    /**
     * Dimensão do mapa.
     */
    private final int dimensao;

    /**
     * Matriz que representa a floresta do jogo. 
     */
    private final CelulaTerreno[][] floresta;
  
    /**
     * Array que guarda referencias para as árvores da floresta, na ordem em que foram inseridas.
     */
    private final ArrayList<Arvore> arvoresFloresta = new ArrayList<>();

    /**
     * Array que guarda a referencia pros jogadores.
     */
    private final ArrayList<Jogador> jogadores = new ArrayList<>();

    /**
     * Instância de MapaTools que recebe a própria floresta do mapa e oferece métodos úteis.
     *
     * @see modelo.utils.MapaTools
     */
    private MapaTools mapaTools;



    /**
     * O construtor de um mapa recebe a configuração importada de um arquivo e o número de jogadores.
     *
     * @see modelo.arquivo.GerenciadorMapaArquivo.java
     * @see modelo.arquivo.VerificadorMapaArquivo.java
     * @see modelo.mapa.MapaConfiguracao.java
     */
    public Mapa(MapaConfiguracao configuracaoDoMapa, int numeroJogadores) {
        this.dimensao = configuracaoDoMapa.dimensao;
        floresta = new CelulaTerreno[dimensao][dimensao];
        this.mapaTools = new MapaTools(floresta, dimensao);
        for (int i = 0; i < numeroJogadores; i++)
            this.jogadores.add(new Jogador("" + (i+1)));
        carregarTerreno(configuracaoDoMapa);
    }


    // getters -----------------------------------------
    public int getDimensao() {
        return dimensao;
    }

    public CelulaTerreno[][] getFloresta() {
        return floresta;
    }

    public ArrayList<Arvore> getArvoresFloresta() {
        return arvoresFloresta;
    }
    // ------------------------------------------------


    // Posicionar Elementos ------------------------------------

    /**
     * Esse método posiciona gramas em todas as instâncias de célula terreno da floresta do mapa.
     */
    private void posicionarGramas() {
        for (CelulaTerreno[] line : floresta) {
            for (int i = 0; i < line.length; i++) {
                line[i] = new Grama();  // Criando uma nova instância de Grama para cada célula
            }
        }
    }

    /**
     * Esse método posiciona as pedras na floresta de maneira aleatória.
     *
     * @see modelo.utils.MapaTools.java
     */
    private void posicionarPedras(int qtdPedras) {
        for (int i = 0; i < qtdPedras; i++) {
            Coordenada c = mapaTools.gerarCoordenadaValida();
            floresta[c.getX()][c.getY()] = new Pedra();
        }
    }

    /**
     * Esse método posicona as árvores na floresta.
     *
     * @see modelo.utils.MapaTools.java
     */
    private void posicionarArvores(Map<String, QuantidadeFrutas> FrutaMap) {
        int qtdArvore = quantidadeTotalArvores(FrutaMap);
        for (int i = 0; i < qtdArvore; i++) {
            Coordenada c = mapaTools.gerarCoordenadaValida();
            floresta[c.getX()][c.getY()] = new Arvore();
            arvoresFloresta.add((Arvore) floresta[c.getX()][c.getY()]);
        }
    }

    /**
     * Esse método posicona as frutas no chão da floresta.
     *
     * @see modelo.utils.MapaTools.java
     */
    private void posicionarFrutas(Map<String, QuantidadeFrutas> FrutaMap, int probabilidadeBichada) {

        for (Map.Entry<String, QuantidadeFrutas> entry : FrutaMap.entrySet()) {
            Fruta fruta = FrutaTools.gerarFruta(entry.getKey(), FrutaTools.decidirBichada(probabilidadeBichada));
            int frutasNaGrama = entry.getValue().grama;
            for (int i = 0; i < frutasNaGrama; i++) {
                Coordenada coordenadaValida = mapaTools.gerarCoordenadaValidaFruta();
                Grama grama = (Grama) mapaTools.celulaEm(coordenadaValida);
                grama.setFrutaOcupante(fruta);
            }
        }
    }

    /**
     * Esse método posicona os jogadores. 
     *
     * @see modelo.utils.MapaTools.java
     */
    private void posicionarJogador(Jogador jogador) {
        // valores para posicionar o jogador
        Coordenada c = mapaTools.gerarCoordenadaValidaJogador();
        floresta[c.getX()][c.getY()].setJogadorOcupante(jogador);
    }

    private void posicionarJogadores(ArrayList<Jogador> jogadores) {
        for (Jogador jogador : jogadores) {
            posicionarJogador(jogador);
        }
    }

    // ---------------------------------------------------------

    public int quantidadeTotalArvores(Map<String, QuantidadeFrutas> FrutaMap){
        int qtdArvore = 0;
        for (Map.Entry<String, QuantidadeFrutas> entry : FrutaMap.entrySet()) {
            if (entry.getKey().equals("maracuja")) continue;
            else qtdArvore += entry.getValue().arvore;
        }
        return qtdArvore;
    }

    public void selecionarFrutadasArvores(Map<String, QuantidadeFrutas> frutasMap) {

        int arvore = 0;
        for (Map.Entry<String, QuantidadeFrutas> entry : frutasMap.entrySet()) {

            if (entry.getKey().equals("maracuja")) continue;
            Fruta frutaModelo = FrutaTools.gerarFruta(entry.getKey(), false);

            for (int fruta = 0; fruta < entry.getValue().arvore; fruta++) {
                arvoresFloresta.get(arvore).setFrutaDaArvore(frutaModelo);
                arvore++;
            }
        }
    }

    private void carregarTerreno(MapaConfiguracao configuracao) {

        posicionarGramas();
        posicionarPedras(configuracao.qtdPedras);
        posicionarArvores(configuracao.qntFrutasPorTipo);
        posicionarFrutas(configuracao.qntFrutasPorTipo, configuracao.probabilidadeBichadas);
        selecionarFrutadasArvores(configuracao.qntFrutasPorTipo);
        posicionarJogadores(this.jogadores);

    }

    public void visualizarTerreno(){
        mapaTools.visualizarTerreno();
    }

    @Override
    public String toString(){
        return mapaTools.FlorestaToString();
    }
}
