package modelo.mapa;

import modelo.MovimentoJogador.GrafoJogador;
import modelo.MovimentoJogador.RelacaoPeso;
import modelo.entidades.*;
import modelo.frutas.Maracuja;
import modelo.tipos.*;
import modelo.utils.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Essa classe configura e contém a floresta na qual o jogo ocorre.
 */
public class Mapa {

    private final int dimensao;
    private final CelulaTerreno[][] floresta;
    private final ArrayList<Arvore> arvoresFloresta = new ArrayList<>();
    private final ArrayList<Jogador> jogadores = new ArrayList<>();
    public MapaTools mapaTools;
    public int quantidadeFrutasOuro;
    public MapaConfiguracao configuracao;

    public Mapa(MapaConfiguracao configuracaoDoMapa, int numeroJogadores) {
        this.dimensao = configuracaoDoMapa.dimensao;
        quantidadeFrutasOuro = configuracaoDoMapa.getFrutasOuroTotais();
        floresta = new CelulaTerreno[dimensao][dimensao];
        this.mapaTools = new MapaTools(floresta, dimensao);
        for (int i = 0; i < numeroJogadores; i++)
            this.jogadores.add(new Jogador("J" + (i+1), Coordenada.origem(), configuracaoDoMapa.tamanhoMochila));
        carregarTerreno(configuracaoDoMapa);
        configuracao = configuracaoDoMapa;
    }


    // getters -----------------------------------------
    public int getDimensao() {
        return dimensao;
    }

    public Jogador getJogador(int i){
        return jogadores.get(i);
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
     * Esse método preenche a floresta com gramas.
     */
    private void posicionarGramas() {
        for (CelulaTerreno[] line : floresta) {
            for (int i = 0; i < line.length; i++) {
                line[i] = new Grama();  // Criando uma nova instância de Grama para cada célula
            }
        }
    }

    /**
     * Dada uma quantidade de pedras, posiciona elas aleatoriamente pelo mapa.
     *
     * @param qtdPedras Quantidade de pedras a serem colocadas.
     */
    private void posicionarPedras(int qtdPedras) {
        for (int i = 0; i < qtdPedras; i++) {
            Coordenada c = mapaTools.gerarCoordenadaValida();
            floresta[c.getI()][c.getJ()] = new Pedra();
        }
    }

    /**
     * Posiciona arvores em posições livres.
     */
    private void posicionarArvores(Map<String, QuantidadeFrutas> FrutaMap) {
        int qtdArvore = quantidadeTotalArvores(FrutaMap);
        for (int i = 0; i < qtdArvore; i++) {
            Coordenada c = mapaTools.gerarCoordenadaValida();
            floresta[c.getI()][c.getJ()] = new Arvore();
            arvoresFloresta.add((Arvore) floresta[c.getI()][c.getJ()]);
        }
    }

    /**
     * Posiciona as frutas no chão, conforme um mapa de nomes para quantidades.
     *
     * @param FrutaMap Mapa de nomes para quantidades.
     * @param probabilidadeBichada Porcentagem contendo a chance de que as frutas geradas no chão sejam bichadas. 
     */
    private void posicionarFrutas(Map<String, QuantidadeFrutas> FrutaMap, int probabilidadeBichada) {

        for (Map.Entry<String, QuantidadeFrutas> entry : FrutaMap.entrySet()) {
            Fruta fruta = FrutaTools.gerarFruta(entry.getKey(), FrutaTools.decidirBichada(probabilidadeBichada));
            int frutasNaGrama = entry.getValue().grama;
            for (int i = 0; i < frutasNaGrama; i++) {
                Coordenada coordenadaValida = mapaTools.gerarCoordenadaValidaFruta();
                Grama grama = (Grama) mapaTools.celulaEm(coordenadaValida);
                grama.setFrutaOcupante(fruta);
                //fruta.setNome(entry.getKey());
            }
        }
    }

    /**
     * Posiciona um jogador.
     */
    private void posicionarJogador(Jogador jogador) {
        // valores para posicionar o jogador
        Coordenada c = mapaTools.gerarCoordenadaValidaJogador();
        jogador.setCoordenada(c);
        floresta[c.getI()][c.getJ()].setJogadorOcupante(jogador);
        jogador.setCelulaOcupada(floresta[c.getI()][c.getJ()]);
    }

    /**
     * Posiciona os jogadores.
     */
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

    /**
     * Seleciona de qual tipo cada árvore da floresta será.
     *
     * @param frutasMap Mapa de nomes para quantidades.
     */
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

    /**
     * Principal método da classe Mapa. Este método é responsável por executar da maneira correta todos os passos anteriores, garantindo a geração de um mapa íntegro e completo conforme as descrições da configuracaoDoMapa.
     *
     * @param configuracao MapaConfiguracao que será usado de referênca para a criação deste mapa.
     *
     * @see MapaConfiguracao
     */
    private void carregarTerreno(MapaConfiguracao configuracao) {

        posicionarGramas();
        posicionarPedras(configuracao.qtdPedras);
        posicionarArvores(configuracao.qntFrutasPorTipo);
        posicionarFrutas(configuracao.qntFrutasPorTipo, configuracao.probabilidadeBichadas);
        selecionarFrutadasArvores(configuracao.qntFrutasPorTipo);
        posicionarJogadores(this.jogadores);

    }

    public void moverJogador(Jogador jogador, int i, int j) {
        GrafoJogador grafo = new GrafoJogador(this);
        grafo.preencherMatriz(this, jogador.getCoordenada());
        Stack<Coordenada> passos = grafo.passosAtePosicao(new Coordenada(i, j));

        if (floresta[i][j].getJogadorOcupante() == null){
            Coordenada coordenada = jogador.getCoordenada();
            floresta[coordenada.getI()][coordenada.getJ()].setJogadorOcupante(null);
            jogador.setCoordenada(new Coordenada(i, j));
            jogador.setCelulaOcupada(floresta[i][j]);
            floresta[i][j].setJogadorOcupante(jogador);
        }
        else {
            while (passos.size() > 2) passos.pop();
            Coordenada novaCoordenada = passos.pop();
            Coordenada coordenada = jogador.getCoordenada();
            floresta[coordenada.getI()][coordenada.getJ()].setJogadorOcupante(null);
            jogador.setCoordenada(novaCoordenada);
            jogador.setCelulaOcupada(floresta[novaCoordenada.getI()][novaCoordenada.getJ()]);
            floresta[novaCoordenada.getI()][novaCoordenada.getJ()].setJogadorOcupante(jogador);
            Jogador jogadorDefensor = floresta[i][j].getJogadorOcupante();
            encrenca(jogador, jogadorDefensor);
        }

    }

    /**
     * vizualiza o terreno atual no terminal.
     */
    public void visualizarTerreno(){
        mapaTools.visualizarTerreno();
    }

    /**
     * Gera uma string completa do mapa. Pode ser usada para serialização ou até mesmo para trasnformação em imagens.
     */
    @Override
    public String toString(){
        return mapaTools.FlorestaToString();
    }



    public ArrayList<CelulaTerreno> getGramasLivres(){
        ArrayList<CelulaTerreno> celulasLivres = new ArrayList<>();
        for (CelulaTerreno[] linha : this.floresta){
            for (CelulaTerreno celula : linha){
                if (celula instanceof Grama){
                    if(((Grama) celula).frutaOcupante == null) celulasLivres.add(celula);
                }
            }
        }
        return celulasLivres;
    }

    public void encrenca(Jogador jogadorAtacante, Jogador jogadorDefensor){

        ArrayList<CelulaTerreno> gramasLivres = getGramasLivres();
        if (gramasLivres == null) return;
        ArrayList<Fruta> frutasDerrubadas = JogadorUtils.frutasDerrubadas(jogadorAtacante, jogadorDefensor, gramasLivres.size());
        if (frutasDerrubadas == null) return;
        for (Fruta fruta : frutasDerrubadas){
            posicinarFruta(fruta);
        }
    }

    public void posicinarFruta(Fruta fruta){
        Coordenada c = mapaTools.gerarCoordenadaValidaFruta();
        ((Grama) mapaTools.celulaEm(c)).frutaOcupante = fruta;
    }

    public int getQuantidadeFrutasOuro() {
        return quantidadeFrutasOuro;
    }
}
