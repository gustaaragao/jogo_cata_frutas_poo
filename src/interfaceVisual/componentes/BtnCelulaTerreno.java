package interfaceVisual.componentes;

import modelo.entidades.CelulaTerreno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A classe {@code BtnCelulaTerreno} estende {@link JButton} e é utilizada para criar botões que representam
 * células de terreno em um mapa. Ela parametriza o botão, definindo o ícone da célula e ajustando o comportamento
 * visual do botão quando o mouse interage com ele.
 */
public class BtnCelulaTerreno extends JButton {
    private final int posicaoX;
    private final int posicaoY;
    private final CelulaTerreno celulaTerreno;


    /**
     * Construtor que cria um botão personalizado para representar uma célula de terreno no mapa.
     * O botão utiliza a imagem correspondente à célula, recebida como parâmetro.
     *
     * @param celulaTerreno  A instância de {@link CelulaTerreno} que contém as informações da célula que será
     *                       representada pelo botão.
     *
     */
    public BtnCelulaTerreno(CelulaTerreno celulaTerreno, int posicaoX, int posicaoY) {
        super();

        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.celulaTerreno = celulaTerreno;

        this.setBounds(posicaoX, posicaoY, 50, 50);

        // Remove margens do botão para ajustar ao tamanho da célula
        setMargin(new Insets(0, 0, 0, 0));

        // Deixa o fundo transparente
        setOpaque(false);

        // Remove a cor do Fundo do Botão
        setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("a");
            }
        });
    }
}