package interfaceVisual.componentes;

import modelo.Partida;
import modelo.entidades.CelulaTerreno;
import modelo.mapa.Mapa;
import modelo.utils.GeradorFlores;
import modelo.utils.Randomizador;

import javax.swing.*;
import java.awt.*;

/**
 * A classe {@code PainelMapa} estende {@link JPanel} e é responsável por exibir graficamente o mapa do jogo.
 * Ela renderiza as células do terreno como botões e centraliza o grid de células dentro do painel.
 */
public class PainelMapa extends JPanel {

    private Mapa mapa;               // Mapa que contém a estrutura das células do terreno
    private String pacoteTextura;    // Pacote de texturas a ser usado para os ícones das células
    private BtnCelulaTerreno[][] matrizBotoes;

    /**
     * Construtor padrão que inicializa o painel do mapa com um layout nulo.
     * O construtor também define o pacote de textura aleatoriamente utilizando o {@link Randomizador}.
     *
     * @param mapa - instância do mapa
     * @param largura - Largura que esse painel vai ocuparar.
     * @param altura - Altura que esse painel vai ocuparar.
     *
     */
    public PainelMapa(Mapa mapa, int largura, int altura) {
        this.mapa = mapa;

    	// Define o layout absoluto (null layout)
        setLayout(null);

        // Define o tamanho e posição do painel
        setBounds(0, 0, largura, altura);

        // Sorteia um pacote de textura para ser usado no mapa
        this.pacoteTextura = Randomizador.sortearPacoteTextura();

        this.inicializarMapa();
    }

//    public PainelMapa(Mapa mapa, int largura, int altura, Partida partida) {
//        this.mapa = mapa;
//
//        // Define o layout absoluto (null layout)
//        setLayout(null);
//
//        // Define o tamanho e posição do painel
//        setBounds(0, 0, largura, altura);
//
//        // Sorteia um pacote de textura para ser usado no mapa
//        this.pacoteTextura = Randomizador.sortearPacoteTextura();
//    }

    /**
     * Construtor que não recebe largura e altura do painel, e utiliza o tamanho do mapa (m) multiplicado por 50 como tamanho.
     * O interessante dessa bordagem é que podemos usar ele para criar Frames (ou Telas) ajustadas com o tamanho mínimo possível
     * para representar um mapa.
     *
     * @param mapa - instância do Mapa
     *
     */
    public PainelMapa(Mapa mapa) {
        this.mapa = mapa;

        setLayout(null);

        setBounds(0, 0, mapa.getDimensao() * 50, mapa.getDimensao() * 50);

        this.pacoteTextura = Randomizador.sortearPacoteTextura();

        this.inicializarMapa();
    }

    /**
     * Atualiza a renderização do mapa no painel.
     * Esse método cria e posiciona os botões que representam as células do terreno,
     * centralizando o grid no painel.
     */
    private void inicializarMapa() {
    	int dimensao = this.mapa.getDimensao();

    	CelulaTerreno[][] floresta = this.mapa.getFloresta();

        GeradorFlores geradorFlores = new GeradorFlores(dimensao);

        // Obtém a matriz de células do mapa
        matrizBotoes = new BtnCelulaTerreno[dimensao][dimensao];

        // Calcula as dimensões totais do grid
        int larguraGrid = dimensao * 50;
        int alturaGrid = dimensao * 50;

        // Calcula a posição inicial para centralizar o grid dentro do painel
        int startX = (this.getWidth() - larguraGrid) / 2;
        int startY = (this.getHeight() - alturaGrid) / 2;

        // Remove os componentes antigos, caso o mapa esteja sendo atualizado
        this.removeAll();

        // Cria e posiciona os botões que representam as células do terreno
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                // Calcula a posição do botão no grid
                int posicaoX = startX + j * 50;
                int posicaoY = startY + i * 50;

                // Cria o botão para a célula de terreno e define o pacote de texturas
                BtnCelulaTerreno btnCelulaTerreno = new BtnCelulaTerreno(
                        floresta[i][j], this.pacoteTextura, this, posicaoX, posicaoY
                );

                // TODO: Refatorar toda essa parte de imagens
                // geradorFlores.posicionarFloresBloco(btnCelulaTerreno, i, j);

                matrizBotoes[i][j] = btnCelulaTerreno;

                // Adiciona o botão ao painel
                this.add(btnCelulaTerreno);
            }
        }

        // Atualiza o layout e repinta o painel após adicionar os componentes
        this.revalidate();
        this.repaint();
    }

    public void inicializarMapa(Partida partida, PainelInterfaceJogador painelInterface) {
        int dimensao = this.mapa.getDimensao();

        CelulaTerreno[][] floresta = this.mapa.getFloresta();

        GeradorFlores geradorFlores = new GeradorFlores(dimensao);

        // Obtém a matriz de células do mapa
        matrizBotoes = new BtnCelulaTerreno[dimensao][dimensao];

        // Calcula as dimensões totais do grid
        int larguraGrid = dimensao * 50;
        int alturaGrid = dimensao * 50;

        // Calcula a posição inicial para centralizar o grid dentro do painel
        int startX = (this.getWidth() - larguraGrid) / 2;
        int startY = (this.getHeight() - alturaGrid) / 2;

        // Remove os componentes antigos, caso o mapa esteja sendo atualizado
        this.removeAll();

        // Cria e posiciona os botões que representam as células do terreno
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                // Calcula a posição do botão no grid
                int posicaoX = startX + j * 50;
                int posicaoY = startY + i * 50;

                // Cria o botão para a célula de terreno e define o pacote de texturas
                BtnCelulaTerreno btnCelulaTerreno = new BtnCelulaTerreno(
                        floresta[i][j], this.pacoteTextura, this, painelInterface, posicaoX, posicaoY, partida, i, j
                );

                // TODO: Refatorar toda essa parte de imagens
                // geradorFlores.posicionarFloresBloco(btnCelulaTerreno, i, j);

                matrizBotoes[i][j] = btnCelulaTerreno;

                // Adiciona o botão ao painel
                this.add(btnCelulaTerreno);
            }
        }

        // Atualiza o layout e repinta o painel após adicionar os componentes
        this.revalidate();
        this.repaint();
    }

    public void atualizarMapa() {
        for (int i = 0; i < mapa.getDimensao(); i++) {
            for (int j = 0; j < mapa.getDimensao(); j++) {
                matrizBotoes[i][j].atualizar(mapa.getFloresta()[i][j]);
            }
        }
    }

    /** Mostra no mapa o esquema de cores da distância de um jogador em um determinado
     * quadrado do mapa a todos os outros alcançaveis.
     * @param matrizCaminhos matriz com a quantidade de pontos de movimento necessários para o jogador se mover
     */
    public void mostrarPesos(Integer[][] matrizCaminhos, int pontos) {
        for (int i = 0; i < matrizCaminhos.length; i++) {
            for (int j = 0; j < matrizCaminhos.length; j++) {
                if (matrizCaminhos[i][j] <= pontos)
                    matrizBotoes[i][j].atualizarPeso(matrizCaminhos[i][j]);
            }
        }
    }

    public void removerPesos() {
        for (int i = 0; i < mapa.getDimensao(); i++) {
            for (int j = 0; j < mapa.getDimensao(); j++) {
                matrizBotoes[i][j].removerPeso();
            }
        }
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public Mapa getMapa() {
        return mapa;
    }
}
