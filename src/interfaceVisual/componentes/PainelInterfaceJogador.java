package interfaceVisual.componentes;

import javax.swing.*;
import java.awt.*;

/**
 * A classe {@code PainelInterfaceJogador} estende {@link JPanel} e é utilizada para representar a interface do jogador,
 * onde futuramente será exibido o menu do jogador.
 * Atualmente, o painel é configurado de forma temporária com um fundo azul e um layout absoluto.
 */
public class PainelInterfaceJogador extends JPanel {

    /**
     * Construtor padrão que inicializa o painel com configurações temporárias.
     * O painel tem fundo azul e está posicionado à direita do mapa com um layout absoluto.
     * No futuro, essa área será usada para o menu do jogador.
     */
    public PainelInterfaceJogador() {
        // Define o layout absoluto (null layout)
        setLayout(null);

        // Define a cor de fundo temporária como azul
        setBackground(Color.BLUE); // Temporário

        // Define as dimensões e posição do painel
        setBounds(624, 0, 400, 624);
    }
}
