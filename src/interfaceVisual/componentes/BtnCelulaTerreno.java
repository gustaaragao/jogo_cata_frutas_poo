package interfaceVisual.componentes;

import modelo.entidades.CelulaTerreno;
import modelo.entidades.Grama;

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

    /**
     * Construtor que cria um botão personalizado para representar uma célula de terreno no mapa.
     * O botão utiliza a imagem correspondente à célula, recebida como parâmetro.
     *
     * @param celulaTerreno  A instância de {@link CelulaTerreno} que contém as informações da célula que será
     *                       representada pelo botão.
     * @param pacoteTextura  O caminho para o pacote de texturas que contém a imagem a ser usada como ícone do botão.
     */
    public BtnCelulaTerreno(CelulaTerreno celulaTerreno, String pacoteTextura) {
        super();

        // Define o ícone do botão com base na célula do terreno e no pacote de texturas fornecido
        ImageIcon icon = celulaTerreno.toImageIcon(pacoteTextura);
        setIcon(icon);

        // Remove margens do botão para ajustar ao tamanho da célula
        setMargin(new Insets(0, 0, 0, 0));

        // Remove a borda padrão do botão
        setBorder(BorderFactory.createEmptyBorder());

        // Adiciona um listener para mudar a borda quando o mouse interage com o botão
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Define uma borda preta fina quando o mouse está sobre o botão
                setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Remove a borda quando o mouse sai do botão
                setBorder(BorderFactory.createEmptyBorder());
            }
        });
    }
}
