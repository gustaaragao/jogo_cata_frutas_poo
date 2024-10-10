package interfaceVisual.componentes;

import javax.swing.*;
import java.awt.*;

public class PainelInterfaceJogador extends JPanel {
    public PainelInterfaceJogador() {
        //painel interface jogador
        setLayout(null);
        setBackground(Color.decode("#b2f2bb")); // Temporário
        setBounds(624, 0, 400, 624);

        //label logo jogo
        ImageIcon icon = new ImageIcon(this.getClass().getResource("./../imagens/cata-frutas logo.png"));
        JLabel logoJogo = new JLabel(icon);
        logoJogo.setSize(150, 150);
        logoJogo.setBounds(125, 0, 150, 150);
        this.add(logoJogo);

        //label nome do jogador
        JLabel nomeJogador = new JLabel("Nome Jogador", SwingConstants.CENTER);
        nomeJogador.setBounds(60, 230, 76, 30);
        nomeJogador.setFont(new Font("Arial", Font.BOLD, 10));
        nomeJogador.setOpaque(true);  // Necessário se quiser definir uma cor de fundo
        nomeJogador.setBackground(Color.decode("#a5d8ff"));  // Define uma cor de fundo (opcional)
        nomeJogador.setForeground(Color.BLACK);  // Define a cor do texto (opcional)
        this.add(nomeJogador);

        //label circular imgJogador
        JLabel imgJogador = new JLabel("Img Jog") {
            @Override
            protected void paintComponent(Graphics g) {
                // Ativa o anti-aliasing para bordas suaves
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Define o fundo redondo
                g2.setColor(getBackground());
                g2.fillOval(0, 0, getWidth(), getHeight());

                // Desenha o texto
                g2.setColor(getForeground());
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                g2.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 4);
                // Centraliza o texto
            }

            @Override
            protected void paintBorder(Graphics g) {
                // Personaliza a borda redonda
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getForeground());
                g2.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
            }

            @Override
            public boolean contains(int x, int y) {
                // Define a área clicável como um círculo
                double radius = getWidth() / 2.0;
                double centerX = getWidth() / 2.0;
                double centerY = getHeight() / 2.0;
                return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2);
            }
        };

        imgJogador.setBounds(60, 150, 76, 76); // Ajustado para uma posição dentro do painel
        imgJogador.setFont(new Font("Arial", Font.BOLD, 10));
        imgJogador.setOpaque(true); // Necessário para que o fundo seja desenhado
        imgJogador.setBackground(Color.decode("#a5d8ff"));  // Define uma cor de fundo (opcional)
        this.add(imgJogador);

        //label dado
        JLabel dado = new JLabel("Dado", SwingConstants.CENTER);
        dado.setBounds(5, 150, 50, 30);
        dado.setFont(new Font("Arial", Font.BOLD, 10));
        dado.setOpaque(true);  // Necessário se quiser definir uma cor de fundo
        dado.setBackground(Color.decode("#9775fa"));  // Define uma cor de fundo (opcional)
        dado.setForeground(Color.BLACK);  // Define a cor do texto (opcional)
        this.add(dado);

        //label maracujá
        JLabel maracuja = new JLabel("Maracujá", SwingConstants.CENTER);
        maracuja.setBounds(5, 196, 50, 30);
        maracuja.setFont(new Font("Arial", Font.BOLD, 10));
        maracuja.setOpaque(true);  // Necessário se quiser definir uma cor de fundo
        maracuja.setBackground(Color.decode("#9775fa"));  // Define uma cor de fundo (opcional)
        maracuja.setForeground(Color.BLACK);  // Define a cor do texto (opcional)
        this.add(maracuja);

        //label relógio
        JLabel relogio = new JLabel("Relógio", SwingConstants.CENTER);
        relogio.setBounds(141, 150, 50, 30);
        relogio.setFont(new Font("Arial", Font.BOLD, 10));
        relogio.setOpaque(true);  // Necessário se quiser definir uma cor de fundo
        relogio.setBackground(Color.decode("#9775fa"));  // Define uma cor de fundo (opcional)
        relogio.setForeground(Color.BLACK);  // Define a cor do texto (opcional)
        this.add(relogio);

        //label efeito
        JLabel efeito = new JLabel("Efeito", SwingConstants.CENTER);
        efeito.setBounds(141, 196, 50, 30);
        efeito.setFont(new Font("Arial", Font.BOLD, 10));
        efeito.setOpaque(true);  // Necessário se quiser definir uma cor de fundo
        efeito.setBackground(Color.decode("#9775fa"));  // Define uma cor de fundo (opcional)
        efeito.setForeground(Color.BLACK);  // Define a cor do texto (opcional)
        this.add(efeito);

        //label força/capacidade
        JLabel forcaCapacidade = new JLabel("<html><div style='text-align: center;'>Força" +
                "<br>Capacidade</div></html>", SwingConstants.CENTER);
        forcaCapacidade.setBounds(5, 270, 191, 60);
        forcaCapacidade.setFont(new Font("Arial", Font.BOLD, 10));
        forcaCapacidade.setOpaque(true);  // Necessário se quiser definir uma cor de fundo
        forcaCapacidade.setBackground(Color.decode("#ffec99"));  // Define uma cor de fundo (opcional)
        forcaCapacidade.setForeground(Color.BLACK);  // Define a cor do texto (opcional)
        this.add(forcaCapacidade);

        //label quantidade de frutas
        JLabel quantFrutas = new JLabel("Quant Frutas", SwingConstants.CENTER);
        quantFrutas.setBounds(5, 340, 191, 45);
        quantFrutas.setFont(new Font("Arial", Font.BOLD, 10));
        quantFrutas.setOpaque(true);  // Necessário se quiser definir uma cor de fundo
        quantFrutas.setBackground(Color.decode("#9775fa"));  // Define uma cor de fundo (opcional)
        quantFrutas.setForeground(Color.BLACK);  // Define a cor do texto (opcional)
        this.add(quantFrutas);

        //label comer
        JLabel comer = new JLabel("Comer", SwingConstants.CENTER);
        comer.setBounds(70, 395, 60, 30);
        comer.setFont(new Font("Arial", Font.BOLD, 10));
        comer.setOpaque(true);  // Necessário se quiser definir uma cor de fundo
        comer.setBackground(Color.decode("#9775fa"));  // Define uma cor de fundo (opcional)
        comer.setForeground(Color.BLACK);  // Define a cor do texto (opcional)
        this.add(comer);

        //label pegarFruta
        JLabel encrenca = new JLabel("Encrenca", SwingConstants.CENTER);
        encrenca.setBounds(15, 435, 170, 45);
        encrenca.setFont(new Font("Arial", Font.BOLD, 10));
        encrenca.setOpaque(true);  // Necessário se quiser definir uma cor de fundo
        encrenca.setBackground(Color.decode("#96f2d7"));  // Define uma cor de fundo (opcional)
        encrenca.setForeground(Color.BLACK);  // Define a cor do texto (opcional)
        this.add(encrenca);

        //label pegar fruta
        JLabel pegarFruta = new JLabel("Pegar Fruta", SwingConstants.CENTER);
        pegarFruta.setBounds(15, 490, 170, 45);
        pegarFruta.setFont(new Font("Arial", Font.BOLD, 10));
        pegarFruta.setOpaque(true);  // Necessário se quiser definir uma cor de fundo
        pegarFruta.setBackground(Color.decode("#96f2d7"));  // Define uma cor de fundo (opcional)
        pegarFruta.setForeground(Color.BLACK);  // Define a cor do texto (opcional)
        this.add(pegarFruta);

        //label ficar de boas
        JLabel ficarDeBoas = new JLabel("Ficar de boas", SwingConstants.CENTER);
        ficarDeBoas.setBounds(15, 545, 170, 45);
        ficarDeBoas.setFont(new Font("Arial", Font.BOLD, 10));
        ficarDeBoas.setOpaque(true);  // Necessário se quiser definir uma cor de fundo
        ficarDeBoas.setBackground(Color.decode("#96f2d7"));  // Define uma cor de fundo (opcional)
        ficarDeBoas.setForeground(Color.BLACK);  // Define a cor do texto (opcional)
        this.add(ficarDeBoas);
    }
}