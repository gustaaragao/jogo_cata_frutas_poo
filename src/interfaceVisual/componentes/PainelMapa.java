package interfaceVisual.componentes;

import modelo.entidades.CelulaTerreno;
import modelo.mapa.Mapa;
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

    /**
     * Construtor padrão que inicializa o painel do mapa com um layout nulo e uma cor de fundo.
     * O construtor também define o pacote de textura aleatoriamente utilizando o {@link Randomizador}.
     */
    public PainelMapa() {
        // Define o layout absoluto (null layout)
        setLayout(null);

        // Define uma cor de fundo temporária
        setBackground(Color.LIGHT_GRAY);  // Cor de fundo para visualizar a área reservada

        // Define o tamanho e posição do painel
        setBounds(0, 0, 624, 624);

        // Sorteia um pacote de textura para ser usado no mapa
        this.pacoteTextura = Randomizador.sortearPacoteTextura();
    }

    /**
     * Define o mapa que será renderizado no painel.
     *
     * @param mapa O objeto {@link Mapa} que contém as células do terreno.
     */
    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    /**
     * Atualiza a renderização do mapa no painel.
     * Esse método cria e posiciona os botões que representam as células do terreno,
     * centralizando o grid no painel.
     */
    public void atualizarMapa() {
        // Define a dimensão máxima para o grid, com limite de 12x12 células
        int dimensao = Math.min(mapa.getDimensao(), 12);

        // Obtém a matriz de células do mapa
        CelulaTerreno[][] floresta = mapa.getFloresta();

        // Define o tamanho de cada botão de célula de terreno
        int tamanhoBtnCelulaTerreno = 50;

        // Calcula as dimensões totais do grid
        int larguraGrid = dimensao * tamanhoBtnCelulaTerreno;
        int alturaGrid = dimensao * tamanhoBtnCelulaTerreno;

        // Calcula a posição inicial para centralizar o grid dentro do painel de 624x624
        int startX = (this.getWidth() - larguraGrid) / 2;
        int startY = (this.getHeight() - alturaGrid) / 2;

        // Remove os componentes antigos, caso o mapa esteja sendo atualizado
        this.removeAll();

        // Cria e posiciona os botões que representam as células do terreno
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                // Cria o botão para a célula de terreno e define o pacote de texturas
                BtnCelulaTerreno btnCelulaTerreno = new BtnCelulaTerreno(floresta[i][j], this.pacoteTextura);

                // Calcula a posição do botão no grid
                int posicaoX = startX + j * tamanhoBtnCelulaTerreno;
                int posicaoY = startY + i * tamanhoBtnCelulaTerreno;

                // Define o tamanho e posição do botão no painel
                btnCelulaTerreno.setBounds(posicaoX, posicaoY, tamanhoBtnCelulaTerreno, tamanhoBtnCelulaTerreno);

                // Adiciona o botão ao painel
                this.add(btnCelulaTerreno);
            }
        }

        // Atualiza o layout e repinta o painel após adicionar os componentes
        this.revalidate();
        this.repaint();
    }
}
