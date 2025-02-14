package modelo.entidades;

import modelo.utils.Imagem;
import modelo.utils.Randomizador;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Pedra extends CelulaTerreno {

    public Pedra() {
        super();
    }


    /**
     * Gera o elemento para exibição no terminal.
     */
    /**
     * Responsável por gerar o modelo para exibição no terminal
     */
    @Override
    public String toString() {
        //Corrigir, fiz na pressa.
        if (this.getJogadorOcupante() != null) {
            return getJogadorOcupante().toString() + "/#";
        } else return "#";
    }
    /**
     * Gera a imagem para vizualização.
     */
    @Override
    public ImageIcon toImageIcon(String pacoteTextura) {
        String caminhoGrama = "/interfaceVisual/imagens/blocos/" + pacoteTextura + "/grama.png";
        String caminhoPedra = "/interfaceVisual/imagens/pedras/pedra.png";

        ImageIcon iconGrama = new ImageIcon(this.getClass().getResource(caminhoGrama));
        ImageIcon iconPedra = new ImageIcon(this.getClass().getResource(caminhoPedra));

        BufferedImage imagemCombinada = Imagem.combinarImagens(iconGrama, iconPedra);

        if (this.getJogadorOcupante() != null) {
            String caminhoJogador;
            if (getJogadorOcupante().getNome().equals("J1"))
                caminhoJogador = "/interfaceVisual/imagens/jogadores/jogador1.png";
            else
                caminhoJogador = "/interfaceVisual/imagens/jogadores/jogador2.png";

            ImageIcon iconJogador = new ImageIcon(this.getClass().getResource(caminhoJogador));

            ImageIcon combinacaoAnterior =  new ImageIcon(imagemCombinada);

            imagemCombinada = Imagem.combinarImagens(combinacaoAnterior, iconJogador);

            return new ImageIcon(imagemCombinada);
        }

        return new ImageIcon(imagemCombinada);
    }
}
