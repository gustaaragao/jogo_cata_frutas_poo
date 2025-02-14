package interfaceVisual.componentes;

import modelo.Partida;
import modelo.entidades.Fruta;
import modelo.entidades.Grama;
import modelo.frutas.Abacate;
import modelo.frutas.Coco;
import modelo.frutas.Laranja;
import modelo.frutas.Maracuja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A classe {@code PainelInterfaceJogador} estende {@link JPanel} e é utilizada para representar a interface do jogador,
 * onde futuramente será exibido o menu do jogador.
 * Atualmente, o painel é configurado de forma temporária com um fundo azul e um layout absoluto.
 */
public class PainelInterfaceJogador extends JPanel {
    private Partida partida;
    private PainelMapa painelMapa;
    private boolean controle;
    private JButton botaoFrutaAtiva;
    private Fruta frutaAtiva;
    private JLabel pontosDeMovimento;
    private JLabel numeroCapacidadeMochila;
    private JLabel numeroFrutasOuro;
    private JLabel player1;
    private JLabel turno;
    private JLabel jogador;
    private JLabel efeitoAgilidade, efeitoBichada, efeitoForca;
    private JLabel valorForca;
    private ImageIcon anterior;
    private JButton consumirAbacate, consumirAcerola, consumirAmora, consumirCoco, consumirGoiaba, consumirLaranja, consumirMaracuja;

    /**
     * Construtor padrão que inicializa o painel com configurações temporárias.
     * O painel tem fundo azul e está posicionado à direita do mapa com um layout absoluto.
     * No futuro, essa área será usada para o menu do jogador.
     */
    public PainelInterfaceJogador(Partida partida, PainelMapa painel) {
        this.partida = partida;
        painelMapa = painel;
        controle = true;
        frutaAtiva = null;

        // Define o layout absoluto (null layout)
        setLayout(null);

        //define a cor do painel
        setBackground(Color.decode("#EF5C00")); // Temporário

        // Define as dimensões e posição do painel
        setBounds(624, 0, 400, 624);

        // label players
        ImageIcon players = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/players.png"));
        JLabel player = new JLabel(players);
        player.setSize(161, 149); // Ajusta para o tamanho desejado
        player.setBounds(17, 28, 161, 149); // Posição e tamanho do JLabel
        this.add(player);

        ImageIcon spritePlayer1 = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/spriteJogador1.png"));
        player1 = new JLabel(spritePlayer1);
        player1.setSize(130, 115);
        player1.setBounds(21, 32, 152, 140);
        this.add(player);
        this.add(player1);
        this.setComponentZOrder(player1, 0); // Coloca o player1 no topo
        this.setComponentZOrder(player, 1);  // Coloca o player abaixo

        // label turno
        ImageIcon turnoPlayer = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/turno.png"));
        turno = new JLabel(turnoPlayer);
        turno.setBounds(202, 28, 111, 53);
        turno.setOpaque(true);
        turno.setText(Integer.toString(partida.getTurno()));
        turno.setForeground(Color.white);
        turno.setHorizontalTextPosition(JLabel.CENTER);
        turno.setVerticalTextPosition(JLabel.CENTER);
        turno.setIconTextGap(10);
        this.add(turno);

        // label efeito forca(abacate)
        ImageIcon forca = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/efeitoForcaCinza.png"));
        efeitoForca = new JLabel(forca);
        efeitoForca.setBounds(325, 90, 48, 41);
        efeitoForca.setOpaque(true);
        efeitoForca.setBackground(Color.decode("#ffe414"));
        this.add(efeitoForca);

        // label efeito agilidade(coco)
        ImageIcon agilidade = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/efeitoAgilidadeCinza.png"));
        efeitoAgilidade = new JLabel(agilidade);
        efeitoAgilidade.setBounds(263, 90, 48, 41);
        efeitoAgilidade.setOpaque(true);
        efeitoAgilidade.setBackground(Color.decode("#2912f4"));
        this.add(efeitoAgilidade);

        // label efeito bichada
        ImageIcon bichada = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/efeitoEnvenenamentoCinza.png"));
        efeitoBichada = new JLabel(bichada);
        efeitoBichada.setBounds(200, 90, 48, 41);
        efeitoBichada.setOpaque(true);
        efeitoBichada.setBackground(Color.decode("#bc0aa4"));
        this.add(efeitoBichada);

        // label numero frutas ouro
        ImageIcon frutasOuro = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/numeroFrutasOuro.png"));
        numeroFrutasOuro = new JLabel(frutasOuro);
        numeroFrutasOuro.setBounds(200, 138, 84, 40);
        numeroFrutasOuro.setOpaque(true);
        numeroFrutasOuro.setText(Integer.toString(partida.getVez().getMochila().getQuantidadeFrutasOuro()) + "/" +
                Integer.toString(partida.getMapa().getQuantidadeFrutasOuro()/2 + 1));
        numeroFrutasOuro.setForeground(Color.white);
        numeroFrutasOuro.setHorizontalTextPosition(JLabel.CENTER);
        numeroFrutasOuro.setVerticalTextPosition(JLabel.CENTER);
        //numeroFrutasOuro.setBackground(Color.decode("#bc0aa4"));
        this.add(numeroFrutasOuro);

        // label capacidadeMochila
        ImageIcon capacidadeMochila = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/capacidadeMochila.png"));
        numeroCapacidadeMochila = new JLabel(capacidadeMochila);
        numeroCapacidadeMochila.setBounds(288, 138, 84, 40);
        numeroCapacidadeMochila.setOpaque(true);
        numeroCapacidadeMochila.setText(Integer.toString(partida.getVez().getMochila().size()) + "/" +
                Integer.toString(partida.getVez().getMochila().getTamanhoMochila()));
        numeroCapacidadeMochila.setForeground(Color.white);
        numeroCapacidadeMochila.setHorizontalTextPosition(JLabel.CENTER);
        numeroCapacidadeMochila.setVerticalTextPosition(JLabel.CENTER);
        //numeroCapacidadeMochila.setBackground(Color.decode("#bc0aa4"));
        this.add(numeroCapacidadeMochila);

        // label jogador 1
        ImageIcon jogador1 = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/jogador1.png"));
        jogador = new JLabel(jogador1);
        jogador.setBounds(17, 197, 161, 31);
        jogador.setOpaque(true);
        this.add(jogador);

        // label valor força
        ImageIcon vForca = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/força.png"));
        valorForca = new JLabel(vForca);
        valorForca.setBounds(204, 197, 164, 31);
        valorForca.setText(Integer.toString(partida.getVez().calcularForca()));
        valorForca.setForeground(Color.white);
        valorForca.setHorizontalTextPosition(JLabel.CENTER);
        valorForca.setVerticalTextPosition(JLabel.CENTER);
        valorForca.setOpaque(true);
        this.add(valorForca);


        // botão consumir abacate
        ImageIcon consomeAbacate = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/consomeAbacate.png"));
        consumirAbacate = new JButton(consomeAbacate);
        consumirAbacate.setBounds(17, 239, 52, 55);
//        consumirAbacate.setBorderPainted(false);
        consumirAbacate.setFocusPainted(false);
        consumirAbacate.setContentAreaFilled(false);
        consumirAbacate.setText("x0");
        consumirAbacate.setForeground(Color.white);
        consumirAbacate.setHorizontalTextPosition(JButton.RIGHT);
        consumirAbacate.setVerticalTextPosition(JButton.BOTTOM);
        consumirAbacate.setIconTextGap(-50);
        this.add(consumirAbacate);
        consumirAbacate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicarBotaoFruta(consumirAbacate, "Abacate");
            }
        });

        // botão consumir acerola
        ImageIcon consomeAcerola = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/consomeAcerola.png"));
        consumirAcerola = new JButton(consomeAcerola);
        consumirAcerola.setBounds(69, 239, 52, 55); // Posição e tamanho do botão
//        consumirAcerola.setBorderPainted(false);
        consumirAcerola.setFocusPainted(false);
        consumirAcerola.setContentAreaFilled(false);
        consumirAcerola.setText("x0");
        consumirAcerola.setForeground(Color.white);
        consumirAcerola.setHorizontalTextPosition(JButton.RIGHT);
        consumirAcerola.setVerticalTextPosition(JButton.BOTTOM);
        consumirAcerola.setIconTextGap(-50);
        this.add(consumirAcerola);
        consumirAcerola.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicarBotaoFruta(consumirAcerola, "Acerola");
            }
        });

        // botão consumir amora
        ImageIcon consomeAmora = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/consomeAmora.png"));
        consumirAmora = new JButton(consomeAmora);
        consumirAmora.setBounds(121, 239, 52, 55);
//        consumirAmora.setBorderPainted(false);
        consumirAmora.setFocusPainted(false);
        consumirAmora.setContentAreaFilled(false);
        consumirAmora.setText("x0");
        consumirAmora.setForeground(Color.white);
        consumirAmora.setHorizontalTextPosition(JButton.RIGHT);
        consumirAmora.setVerticalTextPosition(JButton.BOTTOM);
        consumirAmora.setIconTextGap(-50);
        this.add(consumirAmora);
        consumirAmora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicarBotaoFruta(consumirAmora, "Amora");
            }
        });

        // botão consumir coco
        ImageIcon consomeCoco = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/consomeCoco.png"));
        consumirCoco = new JButton(consomeCoco);
        consumirCoco.setBounds(173, 239, 52, 55);
//        consumirCoco.setBorderPainted(false);
        consumirCoco.setFocusPainted(false);
        consumirCoco.setContentAreaFilled(false);
        consumirCoco.setText("x0");
        consumirCoco.setForeground(Color.white);
        consumirCoco.setHorizontalTextPosition(JButton.RIGHT);
        consumirCoco.setVerticalTextPosition(JButton.BOTTOM);
        consumirCoco.setIconTextGap(-50);
        this.add(consumirCoco);
        consumirCoco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicarBotaoFruta(consumirCoco, "Coco");
            }
        });

        // botão consumir goiaba
        ImageIcon consomeGoiaba = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/consomeGoiaba.png"));
        consumirGoiaba = new JButton(consomeGoiaba);
        consumirGoiaba.setBounds(225, 239, 52, 55);
//        consumirGoiaba.setBorderPainted(false);
        consumirGoiaba.setFocusPainted(false);
        consumirGoiaba.setContentAreaFilled(false);
        consumirGoiaba.setText("x0");
        consumirGoiaba.setForeground(Color.white);
        consumirGoiaba.setHorizontalTextPosition(JButton.RIGHT);
        consumirGoiaba.setVerticalTextPosition(JButton.BOTTOM);
        consumirGoiaba.setIconTextGap(-50);
        this.add(consumirGoiaba);
        consumirGoiaba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicarBotaoFruta(consumirGoiaba, "Goiaba");
            }
        });

        // botão consumir laranja
        ImageIcon consomeLaranja = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/consomeLaranja.png"));
        consumirLaranja = new JButton(consomeLaranja);
        consumirLaranja.setBounds(277, 239, 52, 55);
//        consumirLaranja.setBorderPainted(false);
        consumirLaranja.setFocusPainted(false);
        consumirLaranja.setContentAreaFilled(false);
        consumirLaranja.setText("x0");
        consumirLaranja.setForeground(Color.white);
        consumirLaranja.setHorizontalTextPosition(JButton.RIGHT);
        consumirLaranja.setVerticalTextPosition(JButton.BOTTOM);
        consumirLaranja.setIconTextGap(-50);
        this.add(consumirLaranja);
        consumirLaranja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicarBotaoFruta(consumirLaranja, "Laranja");
            }
        });

        // botão consumir maracujá
        ImageIcon consomeMaracuja = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/consomeMaracuja.png"));
        consumirMaracuja = new JButton(consomeMaracuja);
        consumirMaracuja.setBounds(329, 239, 52, 55);
        //consumirMaracuja.setBorderPainted(false);
        consumirMaracuja.setFocusPainted(false);
        consumirMaracuja.setContentAreaFilled(false);
        consumirMaracuja.setText("x0");
        consumirMaracuja.setForeground(Color.white);
        consumirMaracuja.setHorizontalTextPosition(JButton.RIGHT);
        consumirMaracuja.setVerticalTextPosition(JButton.BOTTOM);
        consumirMaracuja.setIconTextGap(-50);
        this.add(consumirMaracuja);
        consumirMaracuja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicarBotaoFruta(consumirMaracuja, "Maracuja");
            }
        });

        // botão consumir fruta
        ImageIcon consumir = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/consumir.png"));
        JButton consumirFruta = new JButton(consumir);
        consumirFruta.setBounds(17, 300, 354, 46);
        //consumirFruta.setBorderPainted(false);
        consumirFruta.setFocusPainted(false);
        consumirFruta.setContentAreaFilled(false);
        this.add(consumirFruta);
        consumirFruta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frutaAtiva != null) {
                    if (partida.getVez().getPtsMovimento() < 1) {
                        JOptionPane.showMessageDialog(null,
                                "Você não possui pontos de movimento o suficiente!", "Pontos insuficientes", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (frutaAtiva instanceof Maracuja) {
                        JOptionPane.showMessageDialog(null,
                                "Não é possível consumir frutas ouro!", "Maracujá é muito azedo", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    partida.getVez().comerFruta(frutaAtiva);
                    comerFrutas(frutaAtiva);
                    atualizarNumeros();
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "Não há nenhuma fruta selecionada.", "Selecione uma fruta", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        ImageIcon pontos = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/pontosDeMovimento.png"));
        pontosDeMovimento = new JLabel(pontos);
        pontosDeMovimento.setBounds(17, 370, 140, 43);
        this.add(pontosDeMovimento);

        // botão jogarDados
        ImageIcon dados = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/dados.png"));
        JButton jogarDados = new JButton(dados);
        jogarDados.setBounds(170, 370, 202, 43);
        //jogarDados.setBorderPainted(false);
        jogarDados.setFocusPainted(false);
        jogarDados.setContentAreaFilled(false);
        this.add(jogarDados);
        jogarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controle == true) {
                    int dados = partida.jogarDados();
                    setControle(false);
                    pontosDeMovimento.setText(Integer.toString(dados));
                    pontosDeMovimento.setHorizontalTextPosition(JLabel.RIGHT);
                    pontosDeMovimento.setVerticalTextPosition(JLabel.CENTER);
                    pontosDeMovimento.setIconTextGap(-40);
                    pontosDeMovimento.setForeground(Color.white);
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "O jogador já jogou os dados.", "Opção Inválida", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        //botão passar a vez
        ImageIcon pularVez = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/proximoTurno.png"));
        JButton passarVez = new JButton(pularVez);
        passarVez.setBounds(17, 550, 354, 49);
        //passarVez.setBorderPainted(false);
        passarVez.setFocusPainted(false);
        passarVez.setContentAreaFilled(false);
        this.add(passarVez);
        passarVez.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proximoTurno();
            }
        });

        //botão pegar fruta
        ImageIcon pegaFruta = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/coletarFruta.png"));
        JButton pegarFruta = new JButton(pegaFruta);
        pegarFruta.setBounds(17, 420, 353, 32);
        //pegarFruta.setBorderPainted(false);
        pegarFruta.setFocusPainted(false);
        pegarFruta.setContentAreaFilled(false);
        this.add(pegarFruta);
        pegarFruta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (partida.getVez().getCelulaOcupada() instanceof Grama){
                    Grama grama = (Grama) partida.getVez().getCelulaOcupada();
                    if (grama.frutaOcupante != null) {
                        if (partida.getVez().getPtsMovimento() == null) {
                            JOptionPane.showMessageDialog(null,
                                    "Pontos de movimento inválidos! Jogue os dados.", "Jogue os dados", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        if (partida.getVez().getPtsMovimento() != 0 && partida.getVez().getPtsMovimento() != null) {
                            boolean coleta = partida.getVez().coletarFruta(grama.frutaOcupante);
                            if (coleta == true) {
                                grama.frutaOcupante = null;
                                painelMapa.atualizarMapa();
                                atualizarNumeros();
                            }
                            else {
                                JOptionPane.showMessageDialog(null,
                                        "A mochila do jogador está cheia!", "Mochila cheia", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,
                                    "Pontos de movimento insuficientes ou inválidos!", "Pontos Insuficientes", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null,
                                "Não há fruta nessa célula.", "Opção Inválida", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "Não há fruta nessa célula.", "Opção Inválida", JOptionPane.INFORMATION_MESSAGE);
                }
                partida.getVez().getCelulaOcupada();
            }
        });
    }

    private void setControle(boolean valor) {
        controle = valor;
    }

    public void comerFrutas(Fruta fruta) {
        if (fruta instanceof Laranja) {
            ImageIcon bichada = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/efeitoEnvenenamentoCinza.png"));
            efeitoBichada.setIcon(bichada);
        }
        if (fruta instanceof Coco) {
            ImageIcon agilidade = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/efeitoAgilidade.png"));
            efeitoAgilidade.setIcon(agilidade);
            partida.getVez().setPtsMovimento(partida.getVez().getPtsMovimento()*2);
            atualizarNumeros();
        }
        if (fruta instanceof Abacate) {
            ImageIcon forca = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/efeitoForca.png"));
            efeitoForca.setIcon(forca);
            atualizarNumeros();
        }
        frutaAtiva = null;
        botaoFrutaAtiva.setIcon(anterior);
        botaoFrutaAtiva = null;
    }

    public void atualizarPontos(int pontos) {
        setControle(false);
        pontosDeMovimento.setText(Integer.toString(pontos));
        pontosDeMovimento.setHorizontalTextPosition(JLabel.RIGHT);
        pontosDeMovimento.setVerticalTextPosition(JLabel.CENTER);
        pontosDeMovimento.setIconTextGap(-40);
        pontosDeMovimento.setForeground(Color.white);
    }

    public void proximoTurno() {
        setControle(true);
        partida.proximoTurno();

        turno.setText(Integer.toString(partida.getTurno()));

        ImageIcon spritePlayer = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/spriteJogador" + partida.getVez().getNome().charAt(1) + ".png"));
        player1.setIcon(spritePlayer);
        pontosDeMovimento.setText("");

        ImageIcon jogadorVez = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/jogador" + partida.getVez().getNome().charAt(1) + ".png"));
        jogador.setIcon(jogadorVez);

        ImageIcon forca = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/efeitoForcaCinza.png"));
        efeitoForca.setIcon(forca);
        ImageIcon agilidade = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/efeitoAgilidadeCinza.png"));
        efeitoAgilidade.setIcon(agilidade);
        ImageIcon bichada = new ImageIcon(this.getClass().getResource("/interfaceVisual/imagens/menuJogador/efeitoEnvenenamentoCinza.png"));
        efeitoBichada.setIcon(bichada);

        atualizarNumeros();
    }

    public void atualizarNumeros() {
        numeroFrutasOuro.setText(Integer.toString(partida.getVez().getMochila().getQuantidadeFrutasOuro()) + "/" +
                Integer.toString(partida.getMapa().getQuantidadeFrutasOuro()/2 + 1));
        numeroCapacidadeMochila.setText(Integer.toString(partida.getVez().getMochila().size()) + "/" +
                Integer.toString(partida.getVez().getMochila().getTamanhoMochila()));
        valorForca.setText(Integer.toString(partida.getVez().calcularForca()));
        if (partida.getVez().getPtsMovimento() != null)
            pontosDeMovimento.setText(Integer.toString(partida.getVez().getPtsMovimento()));
        consumirAbacate.setText("x" + Integer.toString(partida.getVez().getMochila().getQntAbacate()));
        consumirAcerola.setText("x" + Integer.toString(partida.getVez().getMochila().getQntAcerola()));
        consumirAmora.setText("x" + Integer.toString(partida.getVez().getMochila().getQntAmora()));
        consumirCoco.setText("x" + Integer.toString(partida.getVez().getMochila().getQntCoco()));
        consumirGoiaba.setText("x" + Integer.toString(partida.getVez().getMochila().getQntGoiaba()));
        consumirLaranja.setText("x" + Integer.toString(partida.getVez().getMochila().getQntLaranja()));
        consumirMaracuja.setText("x" + Integer.toString(partida.getVez().getMochila().getQuantidadeFrutasOuro()));
    }

    public void clicarBotaoFruta(JButton botaoFruta, String fruta) {
        if (frutaAtiva == null && botaoFrutaAtiva == null) {
            ImageIcon frutaIcon = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/consome" + fruta +"Ativo.png"));
            botaoFruta.setIcon(frutaIcon);
            frutaAtiva = partida.getVez().getMochila().getFruta(fruta);

            if (frutaAtiva == null) {
                frutaIcon = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/consome" + fruta + ".png"));
                botaoFruta.setIcon(frutaIcon);
                botaoFrutaAtiva = null;
                return;
            }

            anterior = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/consome" + fruta +".png"));
            botaoFrutaAtiva = botaoFruta;
            return;
        }
        if (frutaAtiva != null && botaoFrutaAtiva != null) {
            if (botaoFrutaAtiva == botaoFruta) {
                ImageIcon frutaIcon = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/consome" + fruta + ".png"));
                botaoFruta.setIcon(frutaIcon);
                frutaAtiva = null;
                botaoFrutaAtiva = null;
            }
            else {
                ImageIcon frutaIcon;
                frutaIcon = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/consome" + frutaAtiva.getNome() + ".png"));
                botaoFrutaAtiva.setIcon(frutaIcon);

                frutaIcon = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/consome" + fruta +"Ativo.png"));
                botaoFruta.setIcon(frutaIcon);
                frutaAtiva = partida.getVez().getMochila().getFruta(fruta);

                if (frutaAtiva == null) {
                    frutaIcon = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/consome" + fruta + ".png"));
                    botaoFruta.setIcon(frutaIcon);
                    botaoFrutaAtiva = null;
                    return;
                }

                anterior = new ImageIcon(getClass().getResource("/interfaceVisual/imagens/menuJogador/consome" + fruta +".png"));
                botaoFrutaAtiva = botaoFruta;
            }
        }
    }
}
