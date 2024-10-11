package interfaceVisual.telasStatePattern;

import interfaceVisual.componentes.PainelMapa;
import modelo.mapa.Mapa;

import javax.swing.*;

public class Preview {
    JFrame frame;
    JPanel panel;

    public Preview(Mapa mapa) {
        frame = new JFrame("Preview");
        frame.setSize(624 + 16,624 + 39); 
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
