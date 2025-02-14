package interfaceVisual.telas;

import interfaceVisual.componentes.PainelMapa;
import modelo.mapa.Mapa;

import javax.swing.*;
import java.awt.*;

// TODO: Estou pensando um botão transparente com um dado para ir aleatorizando

/** É uma classe que utiliza de um {@link JFrame} e um {@link JPanel} para mostrar ao usuário
 * um pré-visualização e um mapa de jogo selecionado.
 */
public class Preview {
    private JFrame frame;
    private JPanel panel;
    private PainelMapa painelMapa;

    /** Construtor recebe uma instância de {@link Mapa} e configura o {@link JFrame} da classe.
     * A instância de mapa é pintada sobre o {@link JPanel} a partir da classe {@link PainelMapa}.
     * @param mapa Um mapa do jogo.
     */
    public Preview(Mapa mapa) {

        // Verificar o sistema operacional
        String os = System.getProperty("os.name").toLowerCase();
        int margemX = 0, margemY = 0;

        // Se o sistema for Windows, adicionar margem
        if (os.contains("win")) {
            // TODO: Rapha veja qual o valor exato de margem que precisamos usar aqui.
            margemX = 16;
            margemY = 35; // Ajuste a margem conforme necessário
        }

        frame = new JFrame("Preview");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/interfaceVisual/imagens/icones/previewIcon.png")));
        frame.setSize(mapa.getDimensao() * 50 + margemX, mapa.getDimensao() * 50 + margemY);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, mapa.getDimensao() * 50, mapa.getDimensao() * 50);

        painelMapa = new PainelMapa(mapa);

        panel.add(painelMapa);

        frame.add(panel);
        frame.setVisible(true);
    }

    public PainelMapa getPainelMapa() {
        return painelMapa;
    }
 }
