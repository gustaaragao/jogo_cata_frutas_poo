package interfaceVisual.componentes;

import modelo.entidades.CelulaTerreno;
import modelo.mapa.Mapa;

import javax.swing.*;
import java.awt.*;

public class PainelMapa extends JPanel {

    Mapa mapa;

    public PainelMapa() {
        setLayout(null);
        setBackground(Color.decode("#ffc9c9"));  // Cor de fundo para visualizar a área reservada
        setBounds(0, 0, 624, 624);
    }

    public PainelMapa(Mapa mapa) {
        this();
        this.mapa = mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public void atualizarMapa() {
        // Isso daqui é temporário. Remover quando unirmos as telas de GerarMapa com o de Jogo.
        int dimensao = Math.min(mapa.getDimensao(), 12);

        CelulaTerreno[][] floresta = mapa.getFloresta();

        int tamanhoBtnCelulaTerreno = 50;

        // Dimensões totais do grid
        int larguraGrid = dimensao * tamanhoBtnCelulaTerreno;
        int alturaGrid = dimensao * tamanhoBtnCelulaTerreno;

        // Calcula a posição inicial para centralizar o grid no painel de 624x624
        int startX = (this.getWidth() - larguraGrid) / 2;
        int startY = (this.getHeight() - alturaGrid) / 2;

        // Remove os componentes antigos (caso esteja atualizando o mapa)
        this.removeAll();

        // Cria o grid de botões e os posiciona no painel centralizado
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                // Cria o botão e define seu tamanho e posição
                BtnCelulaTerreno btnCelulaTerreno = new BtnCelulaTerreno(floresta[i][j]);

                int posicaoX = startX + j * tamanhoBtnCelulaTerreno;
                int posicaoY = startY + i * tamanhoBtnCelulaTerreno;

                btnCelulaTerreno.setBounds(posicaoX, posicaoY, tamanhoBtnCelulaTerreno, tamanhoBtnCelulaTerreno);

                this.add(btnCelulaTerreno);
            }
        }

        // Atualiza o layout do painel de mapa e do painel principal
        this.revalidate();
        this.repaint();
    }
}
