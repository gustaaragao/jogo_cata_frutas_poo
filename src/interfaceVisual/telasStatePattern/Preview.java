package interfaceVisual.telasStatePattern;

import interfaceVisual.componentes.PainelMapa;
import modelo.mapa.Mapa;

import javax.swing.*;
import java.awt.*;

public class Preview {
    JFrame frame;
    JPanel panel;

    public Preview(Mapa mapa) {
        frame = new JFrame("Preview");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/interfaceVisual/imagens/previewIcon.png")));
        frame.setSize(624,624);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        //MapaPreview mapaPreview = new MapaPreview(frame, mapa);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 624, 624);

        PainelMapa painelMapa = new PainelMapa();
        painelMapa.setMapa(mapa);
        painelMapa.atualizarMapa();

        panel.add(painelMapa);

        frame.add(panel);
        frame.setVisible(true);
    }
}
