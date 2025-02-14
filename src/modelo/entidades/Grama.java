package modelo.entidades;

import modelo.utils.Imagem;

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

/**
 * Essa classe corresponde ao elemento básico de terreno e é o único qeu pode conter uma fruta frutaOcupante.
 */
public class Grama extends CelulaTerreno {

    /**
     * Fruta ocupante da posição da grama.
     */
    public Fruta frutaOcupante;

    public Grama() {
        super();
        this.frutaOcupante = null;
    }

    public Fruta getFrutaOcupante() {
        return frutaOcupante;
    }

    public void setFrutaOcupante(Fruta frutaOcupante) {
        this.frutaOcupante = frutaOcupante;
    }

    /**
     * Responsável por gerar o modelo para exibição no terminal
     */
    @Override
    public String toString() {
        //Corrigir, fiz na pressa.
        if (this.getJogadorOcupante() != null && frutaOcupante == null) {
            return getJogadorOcupante().toString();
        } else if (this.getJogadorOcupante() != null && frutaOcupante != null) {
            return getJogadorOcupante().toString() + "/" + frutaOcupante.toString();
        } else if (frutaOcupante != null) {
            return frutaOcupante.toString();
        } else return ".";
    }

    /**
     * Gera a imagem da grama com base em uma seleção de pacotes de sprites.
     */
    @Override
    public ImageIcon toImageIcon(String pacoteTextura) {
        String caminhoGrama = "/interfaceVisual/imagens/blocos/" + pacoteTextura + "/grama.png";
        ImageIcon iconGrama = new ImageIcon(this.getClass().getResource(caminhoGrama));

        if (this.frutaOcupante != null) {
            // Caso haja uma fruta naquela célula -> Combinar o sprite da fruta com o da grama.
            String nomeFruta = this.getFrutaOcupante().getClass().getSimpleName().toLowerCase();
            String caminhoFruta = "/interfaceVisual/imagens/frutas/" + nomeFruta + ".png";

            ImageIcon iconFruta = new ImageIcon(this.getClass().getResource(caminhoFruta));

            BufferedImage imagemCombinada = Imagem.combinarImagens(iconGrama, iconFruta);

            BufferedImage imagemComJogador = combinarJogador(imagemCombinada);

            if (imagemComJogador == null)
                return new ImageIcon(imagemCombinada);
            else
                return new ImageIcon(imagemComJogador);
        }

       if (this.getJogadorOcupante() != null) {
           String caminhoJogador;
           if (getJogadorOcupante().getNome().equals("J1"))
               caminhoJogador = "/interfaceVisual/imagens/jogadores/jogador1.png";
           else
               caminhoJogador = "/interfaceVisual/imagens/jogadores/jogador2.png";

           ImageIcon iconJogador = new ImageIcon(this.getClass().getResource(caminhoJogador));

           BufferedImage imagemCombinada = Imagem.combinarImagens(iconGrama, iconJogador);
           return new ImageIcon(imagemCombinada);
       }

        return iconGrama;
    }

    public BufferedImage combinarJogador(BufferedImage combinacaoAnterior) {
        if (this.getJogadorOcupante() != null) {
            String caminhoJogador;
            if (getJogadorOcupante().getNome().equals("J1"))
                caminhoJogador = "/interfaceVisual/imagens/jogadores/jogador1.png";
            else
                caminhoJogador = "/interfaceVisual/imagens/jogadores/jogador2.png";

            ImageIcon iconJogador = new ImageIcon(this.getClass().getResource(caminhoJogador));

            ImageIcon combinacaoAnteriorIcon =  new ImageIcon(combinacaoAnterior);

            BufferedImage imagemCombinada = Imagem.combinarImagens(combinacaoAnteriorIcon, iconJogador);

            return imagemCombinada;
        }
        return null;
    }
}
