package interfaceVisual.telas;

import modelo.arquivo.GerenciadorMapaArquivo;
import modelo.arquivo.VerificadorConfiguracao;
import modelo.mapa.Mapa;
import modelo.mapa.MapaConfiguracao;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/** É a tela onde o usuário pode configurar os parâmetros de mapa da forma que deseja. A tela é
 * pintada sobre o {@link Frame}.
 */
public class ConfigurarTerreno extends Tela{

    /** Construtor recebe uma instância de {@link Frame} e define as particularidades do {@link JPanel}
     * da tela.
     * @param frame
     */
    public ConfigurarTerreno(Frame frame){
        super(frame);

        VerificadorConfiguracao verificador = new VerificadorConfiguracao();

        ImageIcon bgImage = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/telas/menuConfigurarTerreno.png"));
        JLabel background = new JLabel(bgImage);
        background.setSize(1024, 624);
        background.setBounds(0, 0, 1024, 624);

        ImageIcon bgComecar = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/botoes/comecar.png"));
        JButton botaoComecar = new JButton(bgComecar);
        botaoComecar.setBounds(500, 510, 272,49);
        botaoComecar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
            	frame.setJogo(new Mapa(new MapaConfiguracao(verificador), 2));
                frame.setState(frame.getJogo());
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

        ImageIcon bgAdicionar = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/botoes/mais.png"));
        ImageIcon bgRemover = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/botoes/menos.png"));
        ImageIcon bgDisplay = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/icones/displayContador.png"));

        ImageIcon bgSalvar = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/botoes/salvar.png"));
        JButton botaoSalvar = new JButton(bgSalvar);
        botaoSalvar.setBounds(830, 510, 47, 49);
        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                JFileChooser fileChooser = new JFileChooser(new File("C:\\"));
                fileChooser.setDialogTitle("Salvar arquivo de terreno");

                FileNameExtensionFilter filtroExtensao = new FileNameExtensionFilter(
                        "Arquivo .txt", "txt");

                fileChooser.setFileFilter(filtroExtensao);

                int output = fileChooser.showSaveDialog(null);
                if(output == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    System.out.println(file.getAbsolutePath());
                    verificador.distribuirFrutas(); // distribui as frutas diversas em quantidades de cada tipo de fruta
                    GerenciadorMapaArquivo.exportarArquivoTerreno(file.getAbsolutePath(), new MapaConfiguracao(verificador));
                }
            }
        });

        ImageIcon bgPreview = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/botoes/preview.png"));
        JButton botaoPreview = new JButton(bgPreview);
        botaoPreview.setBounds(778, 510, 47, 49);
        botaoPreview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                Preview preview = new Preview(new Mapa(new MapaConfiguracao(verificador), 0));
            }
        });

        // dimensao
        JLabel numeroDimensao = new JLabel(bgDisplay);
        numeroDimensao.setBounds(400,170,30, 30);
        numeroDimensao.setText(Integer.toString(verificador.getDimensao()));
        numeroDimensao.setHorizontalTextPosition(JLabel.CENTER);
//        numeroDimensao.setFont(new PressStartFont().getFont());
        numeroDimensao.setForeground(Color.white);

        JButton adicionarDimensao = new JButton(bgAdicionar);
        adicionarDimensao.setBounds(435, 175, 20,20);
        adicionarDimensao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getDimensao();
                if (verificador.setDimensao(atual + 1)) {
                    numeroDimensao.setText(Integer.toString(verificador.getDimensao()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é maior do que o permitido!", "Valor Excedido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton removerDimensao = new JButton(bgRemover);
        removerDimensao.setBounds(375, 175, 20,20);
        removerDimensao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getDimensao();
                if (verificador.setDimensao(atual - 1)) {
                    numeroDimensao.setText(Integer.toString(verificador.getDimensao()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é menor do que o permitido ou " +
                            "a quantidade de elementos no \nterreno é maior do que o possível, " +
                            "diminua-os ou mantenha a dimensão atual!", "Valor Inválido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // capacidade da mochila
        JLabel numeroCapacidade = new JLabel(bgDisplay);
        numeroCapacidade.setBounds(400,520,30, 30);
        numeroCapacidade.setText(Integer.toString(verificador.getTamanhoMochila()));
        numeroCapacidade.setHorizontalTextPosition(JLabel.CENTER);
//        numeroCapacidade.setFont(new PressStartFont().getFont());
        numeroCapacidade.setForeground(Color.white);

        JButton adicionarCapacidade = new JButton(bgAdicionar);
        adicionarCapacidade.setBounds(435, 525, 20,20);
        adicionarCapacidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getTamanhoMochila();
                if (verificador.setTamanhoMochila(atual + 1)) {
                    numeroCapacidade.setText(Integer.toString(verificador.getTamanhoMochila()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é maior do que o permitido!", "Valor Excedido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton removerCapacidade = new JButton(bgRemover);
        removerCapacidade.setBounds(375, 525, 20,20);
        removerCapacidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getTamanhoMochila();
                if (verificador.setTamanhoMochila(atual - 1)) {
                    numeroCapacidade.setText(Integer.toString(verificador.getTamanhoMochila()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é menor do que o permitido!", "Valor Inválido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // frutas ouro no chao
        JLabel numeroFrutasOuroChao = new JLabel(bgDisplay);
        numeroFrutasOuroChao.setBounds(400,265,30, 30);
        numeroFrutasOuroChao.setText(Integer.toString(verificador.getFrutasOuroChao()));
        numeroFrutasOuroChao.setHorizontalTextPosition(JLabel.CENTER);
//        numeroFrutasOuroChao.setFont(new PressStartFont().getFont());
        numeroFrutasOuroChao.setForeground(Color.white);

        JButton adicionarFrutasOuroChao = new JButton(bgAdicionar);
        adicionarFrutasOuroChao.setBounds(435, 270, 20,20);
        adicionarFrutasOuroChao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getFrutasOuroChao();
                if (verificador.setFrutasOuroChao(atual + 1)) {
                    numeroFrutasOuroChao.setText(Integer.toString(verificador.getFrutasOuroChao()));
                    if (verificador.verificarTamanhoMinimo(verificador.getFrutasOuroTotais(), verificador.getTamanhoMochila())) {
                        numeroCapacidade.setText(Integer.toString(verificador.getTamanhoMochila()));
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é maior do que o permitido!", "Valor Excedido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton removerFrutasOuroChao = new JButton(bgRemover);
        removerFrutasOuroChao.setBounds(375, 270, 20,20);
        removerFrutasOuroChao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getFrutasOuroChao();
                if (verificador.setFrutasOuroChao(atual - 1)) {
                    numeroFrutasOuroChao.setText(Integer.toString(verificador.getFrutasOuroChao()));
                    if (verificador.verificarTamanhoMinimo(verificador.getFrutasOuroTotais(), verificador.getTamanhoMochila())) {
                        numeroCapacidade.setText(Integer.toString(verificador.getTamanhoMochila()));
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é menor do que o permitido!", "Valor Inválido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // frutas ouro a surgir
        JLabel numeroFrutasOuroSurgir = new JLabel(bgDisplay);
        numeroFrutasOuroSurgir.setBounds(400,300,30, 30);
        numeroFrutasOuroSurgir.setText(Integer.toString(verificador.getFrutasOuroASurgir()));
        numeroFrutasOuroSurgir.setHorizontalTextPosition(JLabel.CENTER);
//        numeroFrutasOuroSurgir.setFont(new PressStartFont().getFont());
        numeroFrutasOuroSurgir.setForeground(Color.white);

        JButton adicionarFrutasOuroSurgir = new JButton(bgAdicionar);
        adicionarFrutasOuroSurgir.setBounds(435, 305, 20,20);
        adicionarFrutasOuroSurgir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getFrutasOuroASurgir();
                if (verificador.setFrutasOuroASurgir(atual + 1)) {
                    numeroFrutasOuroSurgir.setText(Integer.toString(verificador.getFrutasOuroASurgir()));
                    if (verificador.verificarTamanhoMinimo(verificador.getFrutasOuroTotais(), verificador.getTamanhoMochila())) {
                        numeroCapacidade.setText(Integer.toString(verificador.getTamanhoMochila()));
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é maior do que o permitido!", "Valor Excedido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton removerFrutasOuroSurgir = new JButton(bgRemover);
        removerFrutasOuroSurgir.setBounds(375, 305, 20,20);
        removerFrutasOuroSurgir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getFrutasOuroASurgir();
                if (verificador.setFrutasOuroASurgir(atual - 1)) {
                    numeroFrutasOuroSurgir.setText(Integer.toString(verificador.getFrutasOuroASurgir()));
                    if (verificador.verificarTamanhoMinimo(verificador.getFrutasOuroTotais(), verificador.getTamanhoMochila())) {
                        numeroCapacidade.setText(Integer.toString(verificador.getTamanhoMochila()));
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é menor do que o permitido!", "Valor Inválido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // frutas diversas
        JLabel numeroFrutasDiversas = new JLabel(bgDisplay);
        numeroFrutasDiversas.setBounds(400,355,30, 30);
        numeroFrutasDiversas.setText(Integer.toString(verificador.getFrutasDiversas()));
        numeroFrutasDiversas.setHorizontalTextPosition(JLabel.CENTER);
//        numeroFrutasDiversas.setFont(new PressStartFont().getFont());
        numeroFrutasDiversas.setForeground(Color.white);

        JButton adicionarFrutasDiversas = new JButton(bgAdicionar);
        adicionarFrutasDiversas.setBounds(435, 360, 20,20);
        adicionarFrutasDiversas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getFrutasDiversas();
                if (verificador.setFrutasDiversas(atual + 1)) {
                    numeroFrutasDiversas.setText(Integer.toString(verificador.getFrutasDiversas()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é maior do que o permitido!", "Valor Excedido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton removerFrutasDiversas = new JButton(bgRemover);
        removerFrutasDiversas.setBounds(375, 360, 20,20);
        removerFrutasDiversas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getFrutasDiversas();
                if (verificador.setFrutasDiversas(atual - 1)) {
                    numeroFrutasDiversas.setText(Integer.toString(verificador.getFrutasDiversas()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é menor do que o permitido!", "Valor Inválido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // chance bichada
        JLabel numeroBichadas = new JLabel(bgDisplay);
        numeroBichadas.setBounds(400,410,30, 30);
        String porcentagem = Integer.toString(verificador.getChanceBichada()) + "%";
        numeroBichadas.setText(porcentagem);
        numeroBichadas.setHorizontalTextPosition(JLabel.CENTER);
//        numeroBichadas.setFont(new PressStartFont(7).getFont());
        numeroBichadas.setForeground(Color.white);

        JButton adicionarBichadas = new JButton(bgAdicionar);
        adicionarBichadas.setBounds(435, 415, 20,20);
        adicionarBichadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getChanceBichada();
                if (verificador.setChanceBichada(atual + 5)) {
                    String porcentagem = Integer.toString(verificador.getChanceBichada()) + "%";
                    numeroBichadas.setText(porcentagem);
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é maior do que o permitido!", "Valor Excedido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton removerBichadas = new JButton(bgRemover);
        removerBichadas.setBounds(375, 415, 20,20);
        removerBichadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getChanceBichada();
                if (verificador.setChanceBichada(atual - 5)) {
                    String porcentagem = Integer.toString(verificador.getChanceBichada()) + "%";
                    numeroBichadas.setText(porcentagem);
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é menor do que o permitido!", "Valor Inválido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        // pedras
        JLabel numeroPedras = new JLabel(bgDisplay);
        numeroPedras.setBounds(400,465,30, 30);
        numeroPedras.setText(Integer.toString(verificador.getPedras()));
        numeroPedras.setHorizontalTextPosition(JLabel.CENTER);
//        numeroPedras.setFont(new PressStartFont().getFont());
        numeroPedras.setForeground(Color.white);

        JButton adicionarPedras = new JButton(bgAdicionar);
        adicionarPedras.setBounds(435, 470, 20,20);
        adicionarPedras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getPedras();
                if (verificador.setPedras(atual + 1)) {
                    numeroPedras.setText(Integer.toString(verificador.getPedras()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é maior do que o permitido!", "Valor Excedido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton removerPedras = new JButton(bgRemover);
        removerPedras.setBounds(375, 470, 20,20);
        removerPedras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getPedras();
                if (verificador.setPedras(atual - 1)) {
                    numeroPedras.setText(Integer.toString(verificador.getPedras()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é menor do que o permitido!", "Valor Inválido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // arvore coco
        JLabel numeroArvoresCoco = new JLabel(bgDisplay);
        numeroArvoresCoco.setBounds(760,224,30, 30);
        numeroArvoresCoco.setText(Integer.toString(verificador.getArvCoco()));
        numeroArvoresCoco.setHorizontalTextPosition(JLabel.CENTER);
//        numeroArvoresCoco.setFont(new PressStartFont().getFont());
        numeroArvoresCoco.setForeground(Color.white);

        JButton adicionarArvoreCoco = new JButton(bgAdicionar);
        adicionarArvoreCoco.setBounds(795, 229, 20,20);
        adicionarArvoreCoco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getArvCoco();
                if (verificador.setArvCoco(atual + 1)) {
                    numeroArvoresCoco.setText(Integer.toString(verificador.getArvCoco()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é maior do que o permitido!", "Valor Excedido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton removerArvoreCoco = new JButton(bgRemover);
        removerArvoreCoco.setBounds(735, 229, 20,20);
        removerArvoreCoco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getArvCoco();
                if (verificador.setArvCoco(atual - 1)) {
                    numeroArvoresCoco.setText(Integer.toString(verificador.getArvCoco()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é menor do que o permitido!", "Valor Inválido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        // arvore laranja
        JLabel numeroArvoresLaranja = new JLabel(bgDisplay);
        numeroArvoresLaranja.setBounds(760,266,30, 30);
        numeroArvoresLaranja.setText(Integer.toString(verificador.getArvLaranja()));
        numeroArvoresLaranja.setHorizontalTextPosition(JLabel.CENTER);
//        numeroArvoresLaranja.setFont(new PressStartFont().getFont());
        numeroArvoresLaranja.setForeground(Color.white);

        JButton adicionarArvoreLaranja = new JButton(bgAdicionar);
        adicionarArvoreLaranja.setBounds(795, 271, 20,20);
        adicionarArvoreLaranja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getArvLaranja();
                if (verificador.setArvLaranja(atual + 1)) {
                    numeroArvoresLaranja.setText(Integer.toString(verificador.getArvLaranja()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é maior do que o permitido!", "Valor Excedido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton removerArvoreLaranja = new JButton(bgRemover);
        removerArvoreLaranja.setBounds(735, 271, 20,20);
        removerArvoreLaranja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getArvLaranja();
                if (verificador.setArvLaranja(atual - 1)) {
                    numeroArvoresLaranja.setText(Integer.toString(verificador.getArvLaranja()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é menor do que o permitido!", "Valor Inválido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // arvore abacate
        JLabel numeroArvoresAbacate = new JLabel(bgDisplay);
        numeroArvoresAbacate.setBounds(760,309,30, 30);
        numeroArvoresAbacate.setText(Integer.toString(verificador.getArvAbacate()));
        numeroArvoresAbacate.setHorizontalTextPosition(JLabel.CENTER);
//        numeroArvoresAbacate.setFont(new PressStartFont().getFont());
        numeroArvoresAbacate.setForeground(Color.white);

        JButton adicionarArvoreAbacate = new JButton(bgAdicionar);
        adicionarArvoreAbacate.setBounds(795, 314, 20,20);
        adicionarArvoreAbacate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getArvAbacate();
                if (verificador.setArvAbacate(atual + 1)) {
                    numeroArvoresAbacate.setText(Integer.toString(verificador.getArvAbacate()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é maior do que o permitido!", "Valor Excedido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton removerArvoreAbacate = new JButton(bgRemover);
        removerArvoreAbacate.setBounds(735, 314, 20,20);
        removerArvoreAbacate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getArvAbacate();
                if (verificador.setArvAbacate(atual - 1)) {
                    numeroArvoresAbacate.setText(Integer.toString(verificador.getArvAbacate()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é menor do que o permitido!", "Valor Inválido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // "arvore" amora
        JLabel numeroArvoresAmora = new JLabel(bgDisplay);
        numeroArvoresAmora.setBounds(760,352,30, 30);
        numeroArvoresAmora.setText(Integer.toString(verificador.getArvAmora()));
        numeroArvoresAmora.setHorizontalTextPosition(JLabel.CENTER);
//        numeroArvoresAmora.setFont(new PressStartFont().getFont());
        numeroArvoresAmora.setForeground(Color.white);

        JButton adicionarArvoreAmora = new JButton(bgAdicionar);
        adicionarArvoreAmora.setBounds(795, 357, 20,20);
        adicionarArvoreAmora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getArvAmora();
                if (verificador.setArvAmora(atual + 1)) {
                    numeroArvoresAmora.setText(Integer.toString(verificador.getArvAmora()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é maior do que o permitido!", "Valor Excedido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton removerArvoreAmora = new JButton(bgRemover);
        removerArvoreAmora.setBounds(735, 357, 20,20);
        removerArvoreAmora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getArvAmora();
                if (verificador.setArvAmora(atual - 1)) {
                    numeroArvoresAmora.setText(Integer.toString(verificador.getArvAmora()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é menor do que o permitido!", "Valor Inválido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // arvore acerola
        JLabel numeroArvoresAcerola = new JLabel(bgDisplay);
        numeroArvoresAcerola.setBounds(760,394,30, 30);
        numeroArvoresAcerola.setText(Integer.toString(verificador.getArvAcerola()));
        numeroArvoresAcerola.setHorizontalTextPosition(JLabel.CENTER);
//        numeroArvoresAcerola.setFont(new PressStartFont().getFont());
        numeroArvoresAcerola.setForeground(Color.white);

        JButton adicionarArvoreAcerola = new JButton(bgAdicionar);
        adicionarArvoreAcerola.setBounds(795, 399, 20,20);
        adicionarArvoreAcerola.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getArvAcerola();
                if (verificador.setArvAcerola(atual + 1)) {
                    numeroArvoresAcerola.setText(Integer.toString(verificador.getArvAcerola()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é maior do que o permitido!", "Valor Excedido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton removerArvoreAcerola = new JButton(bgRemover);
        removerArvoreAcerola.setBounds(735, 399, 20,20);
        removerArvoreAcerola.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getArvAcerola();
                if (verificador.setArvAcerola(atual - 1)) {
                    numeroArvoresAcerola.setText(Integer.toString(verificador.getArvAcerola()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é menor do que o permitido!", "Valor Inválido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // arvore goiaba
        JLabel numeroArvoresGoiaba = new JLabel(bgDisplay);
        numeroArvoresGoiaba.setBounds(760,436,30, 30);
        numeroArvoresGoiaba.setText(Integer.toString(verificador.getArvGoiaba()));
        numeroArvoresGoiaba.setHorizontalTextPosition(JLabel.CENTER);
//        numeroArvoresGoiaba.setFont(new PressStartFont().getFont());
        numeroArvoresGoiaba.setForeground(Color.white);

        JButton adicionarArvoreGoiaba = new JButton(bgAdicionar);
        adicionarArvoreGoiaba.setBounds(795, 441, 20,20);
        adicionarArvoreGoiaba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getArvGoiaba();
                if (verificador.setArvGoiaba(atual + 1)) {
                    numeroArvoresGoiaba.setText(Integer.toString(verificador.getArvGoiaba()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é maior do que o permitido!", "Valor Excedido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton removerArvoreGoiaba = new JButton(bgRemover);
        removerArvoreGoiaba.setBounds(735, 441, 20,20);
        removerArvoreGoiaba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReprodutorDeSom().tocarBotao();
                int atual = verificador.getArvGoiaba();
                if (verificador.setArvGoiaba(atual - 1)) {
                    numeroArvoresGoiaba.setText(Integer.toString(verificador.getArvGoiaba()));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O valor inserido é menor do que o permitido!", "Valor Inválido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        //comecar
        panel.add(botaoComecar);
        //salvar
        panel.add(botaoSalvar);
        //preview
        panel.add(botaoPreview);
        //voltar
        panel.add(botaoVoltar);
        //coco
        panel.add(adicionarArvoreCoco);
        panel.add(removerArvoreCoco);
        panel.add(numeroArvoresCoco);
        //amora
        panel.add(adicionarArvoreAmora);
        panel.add(removerArvoreAmora);
        panel.add(numeroArvoresAmora);
        //abacate
        panel.add(adicionarArvoreAbacate);
        panel.add(removerArvoreAbacate);
        panel.add(numeroArvoresAbacate);
        //laranja
        panel.add(adicionarArvoreLaranja);
        panel.add(removerArvoreLaranja);
        panel.add(numeroArvoresLaranja);
        //acerola
        panel.add(adicionarArvoreAcerola);
        panel.add(removerArvoreAcerola);
        panel.add(numeroArvoresAcerola);
        //goiaba
        panel.add(adicionarArvoreGoiaba);
        panel.add(removerArvoreGoiaba);
        panel.add(numeroArvoresGoiaba);
        //frutas ouro
        panel.add(adicionarFrutasOuroChao);
        panel.add(removerFrutasOuroChao);
        panel.add(numeroFrutasOuroChao);
        panel.add(adicionarFrutasOuroSurgir);
        panel.add(removerFrutasOuroSurgir);
        panel.add(numeroFrutasOuroSurgir);
        //frutas diversas
        panel.add(adicionarFrutasDiversas);
        panel.add(removerFrutasDiversas);
        panel.add(numeroFrutasDiversas);
        //frutas bichadas
        panel.add(adicionarBichadas);
        panel.add(removerBichadas);
        panel.add(numeroBichadas);
        //pedras
        panel.add(adicionarPedras);
        panel.add(removerPedras);
        panel.add(numeroPedras);
        //capacidade
        panel.add(adicionarCapacidade);
        panel.add(removerCapacidade);
        panel.add(numeroCapacidade);
        // dimensao
        panel.add(adicionarDimensao);
        panel.add(removerDimensao);
        panel.add(numeroDimensao);
        //background
        panel.add(background);
    }


}
