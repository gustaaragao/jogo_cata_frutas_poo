package interfaceVisual.telasStatePattern;

import interfaceVisual.fontes.Press_Start_2P.PressStartFont;
import modelo.arquivo.GerenciadorMapaArquivo;
import modelo.mapa.Mapa;
import modelo.mapa.MapaConfiguracao;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ImportarTerreno extends Tela{
    private String caminho;
    public ImportarTerreno(Frame frame) {
        super(frame);

        ImageIcon bgImage = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuImportarTerreno.png"));
        JLabel background = new JLabel(bgImage);
        background.setSize(1024, 624);
        background.setBounds(0, 0, 1024, 624);

        ImageIcon bgComecar = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/botoes/comecar2cinza.png"));
        JButton botaoComecar = new JButton(bgComecar);
        botaoComecar.setBounds(324, 510, 321,49);
        botaoComecar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                //frame.setState(frame.getJogo());
            }
        });

        ImageIcon bgVoltar = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/botoes/voltar.png"));
        JButton botaoVoltar = new JButton(bgVoltar);
        botaoVoltar.setBounds(29, 32, 75,75);
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                frame.setState(frame.getMenu());
            }
        });

        ImageIcon bgDisplay = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/displayPath.png"));
        JLabel displayPath = new JLabel(bgDisplay);
        displayPath.setBounds(333, 450, 357,49);

        ImageIcon bgSelecionar = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/botoes/selecionar2.png"));
        JButton botaoSelecionar = new JButton(bgSelecionar);
        botaoSelecionar.setBounds(333, 400, 357,49);
        botaoSelecionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Selecione um arquivo de terreno");
                FileNameExtensionFilter filtroExtensao = new FileNameExtensionFilter(
                        "Apenas arquivos .txt ou .trn", "txt", "trn");

                fileChooser.setFileFilter(filtroExtensao);

                int output = fileChooser.showOpenDialog(null);

                if(output == JFileChooser.APPROVE_OPTION) {
                    caminho = fileChooser.getSelectedFile().getAbsolutePath();
                    displayPath.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    displayPath.setHorizontalTextPosition(JLabel.CENTER);
//                    displayPath.setFont(new PressStartFont().getFont());
                    displayPath.setForeground(Color.white);
                }
            }
        });

        ImageIcon bgPreview = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/botoes/preview.png"));
        JButton botaoPreview = new JButton(bgPreview);
        botaoPreview.setBounds(653, 510, 47, 49);
        botaoPreview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                if (caminho != null) {
                    Mapa mapa = new Mapa(new GerenciadorMapaArquivo().importarArquivoTerreno(caminho), 0);
                    if (mapa != null) {
                        Preview preview = new Preview(mapa);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,
                                "Selecione um arquivo válido!", "Arquivo inválido", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "Selecione um arquivo válido!", "Arquivo inválido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        panel.add(botaoPreview);
        panel.add(displayPath);
        panel.add(botaoSelecionar);
        panel.add(botaoVoltar);
        panel.add(botaoComecar);
        panel.add(background);
    }
}
