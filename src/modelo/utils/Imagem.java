package modelo.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A classe Imagem contém utilitários para manipulação e combinação de imagens.
 */
public class Imagem {

    /**
     * Combina duas imagens, desenhando a imagem sobreposta no centro da imagem base.
     * 
     * <p>Este método recebe duas imagens do tipo {@link ImageIcon}, sendo que a imagem sobreposta
     * será desenhada sobre a imagem base. A imagem final será retornada como um {@link BufferedImage}.</p>
     *
     * @param imagemBase       A imagem de fundo sobre a qual a segunda imagem será desenhada.
     * @param imagemSobreposta A imagem que será desenhada no centro da imagem base.
     * @return Um {@link BufferedImage} contendo a imagem base com a imagem sobreposta desenhada no centro.
     * @throws IllegalArgumentException Se uma ou ambas as imagens forem inválidas (nulas).
     */
    public static BufferedImage combinarImagens(ImageIcon imagemBase, ImageIcon imagemSobreposta) {
        if (imagemBase.getImage() == null || imagemSobreposta.getImage() == null) {
            throw new IllegalArgumentException("Uma ou ambas as imagens são inválidas.");
        }

        // Cria um BufferedImage com o tamanho da imagem base
        int largura = imagemBase.getIconWidth();
        int altura = imagemBase.getIconHeight();
        BufferedImage imagemCombinada = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);

        // Objeto Graphics2D para desenhar na imagem combinada
        Graphics2D g2d = imagemCombinada.createGraphics();

        // Desenha a imagem base
        g2d.drawImage(imagemBase.getImage(), 0, 0, largura, altura, null);

        // Obtém o tamanho da imagem sobreposta
        int larguraSobreposta = imagemSobreposta.getIconWidth();
        int alturaSobreposta = imagemSobreposta.getIconHeight();

        // Calcula a posição da imagem sobreposta para centralizá-la
        int posX = (largura - larguraSobreposta) / 2;
        int posY = (altura - alturaSobreposta) / 2;

        // Desenha a imagem sobreposta no centro da imagem base com o tamanho original
        g2d.drawImage(imagemSobreposta.getImage(), posX, posY, larguraSobreposta, alturaSobreposta, null);

        // Libera os recursos gráficos
        g2d.dispose();

        // Retorna a imagem combinada
        return imagemCombinada;
    }
}
