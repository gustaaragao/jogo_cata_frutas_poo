package interfaceVisual.telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** É a tela onde o usuário seleciona se deseja importar ou configurar um mapa. A tela é
 * pintada sobre o {@link Frame}.
 */
public class Menu extends Tela{
    /** Construtor recebe uma instância de {@link Frame} e define as particularidades do {@link JPanel}
     * da tela.
     * @param frame
     */
    public Menu (Frame frame) {
        super(frame);

        ImageIcon bgImage = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/telas/menu.png"));
        JLabel background = new JLabel(bgImage);
        background.setSize(1024, 624);
        background.setBounds(0, 0, 1024, 624);

        ImageIcon bgVoltar = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/botoes/voltar.png"));
        JButton botaoVoltar = new JButton(bgVoltar);
        botaoVoltar.setBounds(29, 32, 75,75);
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                frame.setState(frame.getStart());
            }
        });

        ImageIcon bgSelecionar = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/botoes/selecionar.png"));

        // botao importar
        JButton botaoSelecionarImportar = new JButton(bgSelecionar);
        botaoSelecionarImportar.setBounds(169, 480, 310,49);
        botaoSelecionarImportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                frame.setState(frame.getImportarTerreno());
            }
        });

        // botao selecionar
        JButton botaoSelecionarConfigurar = new JButton(bgSelecionar);
        botaoSelecionarConfigurar.setBounds(569, 480, 310,49);
        botaoSelecionarConfigurar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                frame.setState(frame.getConfigurarTerreno());
            }
        });

        panel.add(botaoSelecionarConfigurar);
        panel.add(botaoSelecionarImportar);
        panel.add(botaoVoltar);
        panel.add(background);
    }
}
