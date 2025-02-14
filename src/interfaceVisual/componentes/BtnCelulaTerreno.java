package interfaceVisual.componentes;

import modelo.MovimentoJogador.GrafoJogador;
import modelo.Partida;
import modelo.entidades.CelulaTerreno;
import modelo.entidades.Grama;
import modelo.utils.Imagem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * A classe {@code BtnCelulaTerreno} estende {@link JButton} e é utilizada para criar botões que representam
 * células de terreno em um mapa. Ela parametriza o botão, definindo o ícone da célula e ajustando o comportamento
 * visual do botão quando o mouse interage com ele.
 */
public class BtnCelulaTerreno extends JButton {
    private PainelMapa painelMapa;
    private final int posicaoX;
    private final int posicaoY;
    private  CelulaTerreno celulaTerreno;
    private ImageIcon celulaIcon;
    private Partida partida;
    private int[] tupla;
    private String pacoteTextura;

    /**
     * Construtor que cria um botão personalizado para representar uma célula de terreno no mapa.
     * O botão utiliza a imagem correspondente à célula, recebida como parâmetro.
     *
     * @param celulaTerreno  A instância de {@link CelulaTerreno} que contém as informações da célula que será
     *                       representada pelo botão.
     * @param pacoteTextura  O caminho para o pacote de texturas que contém a imagem a ser usada como ícone do botão.
     */
    public BtnCelulaTerreno(CelulaTerreno celulaTerreno, String pacoteTextura,
                            PainelMapa painel, int posicaoX, int posicaoY) {
        super();

        this.painelMapa = painel;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.celulaTerreno = celulaTerreno;

        // Posicionando o botão
        this.setBounds(posicaoX, posicaoY, 50, 50);

        // Define o ícone do botão com base na célula do terreno e no pacote de texturas fornecido
        this.celulaIcon = celulaTerreno.toImageIcon(pacoteTextura);
        this.pacoteTextura = pacoteTextura;

        setIcon(celulaIcon);

        // Remove margens do botão para ajustar ao tamanho da célula
        setMargin(new Insets(0, 0, 0, 0));

        // Remove a borda padrão do botão
        setBorder(BorderFactory.createEmptyBorder());
    }

    public BtnCelulaTerreno(CelulaTerreno celulaTerreno, String pacoteTextura, PainelMapa painel,
                            PainelInterfaceJogador painelInterface, int posicaoX, int posicaoY, Partida partida, int i, int j) {
        this(celulaTerreno, pacoteTextura, painel, posicaoX, posicaoY);

        this.partida = partida;
        this.tupla = new int[2];
        tupla[0] = i;
        tupla[1] = j;

        // Adiciona um listener para mudar a borda quando o mouse interage com o botão
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Define uma borda preta fina quando o mouse está sobre o botão
                setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                if (celulaTerreno.getJogadorOcupante() != null && partida.getVez() == celulaTerreno.getJogadorOcupante()
                && partida.getVez().getPtsMovimento() != null) {
                    GrafoJogador grafoJogador = new GrafoJogador(painelMapa.getMapa());
                    grafoJogador.preencherMatriz(painelMapa.getMapa(), celulaTerreno.getJogadorOcupante().getCoordenada());
                    painel.mostrarPesos(grafoJogador.getMatrizCaminhos(), partida.getVez().getPtsMovimento());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Remove a borda quando o mouse sai do botão
                setBorder(BorderFactory.createEmptyBorder());
                if (celulaTerreno.getJogadorOcupante() != null) {
                    painel.removerPesos();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                GrafoJogador grafoJogador = new GrafoJogador(painelMapa.getMapa());
                grafoJogador.preencherMatriz(painelMapa.getMapa(), partida.getVez().getCoordenada());
                Integer[][] matrizCaminhos = grafoJogador.getMatrizCaminhos();
                if (partida.getVez().getPtsMovimento() == null) {
                    JOptionPane.showMessageDialog(null,
                            "Jogue os dados para ganhar pontos de movimento.", "Jogue os dados", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (partida.getVez().getPtsMovimento() >= matrizCaminhos[tupla[0]][tupla[1]]) {
                        partida.moverJogador(tupla[0], tupla[1], matrizCaminhos[tupla[0]][tupla[1]]);
                        painel.atualizarMapa();
                        painelInterface.atualizarPontos(partida.getVez().getPtsMovimento());
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "Pontos de movimento insuficientes.", "Pontos Insuficientes", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public void atualizar(CelulaTerreno celulaTerreno) {
        this.celulaTerreno = celulaTerreno;
        this.celulaIcon = celulaTerreno.toImageIcon(pacoteTextura);
        setIcon(celulaIcon);
    }

    public void atualizarPeso(int peso) {
        if (peso > 0) {
            try {
                String caminhoPeso = "/interfaceVisual/imagens/blocos/pesos/peso" + peso + ".png";
                ImageIcon iconPeso = new ImageIcon(this.getClass().getResource(caminhoPeso));
                BufferedImage imagemCombinada = Imagem.combinarImagens(celulaIcon, iconPeso);
                this.setIcon(new ImageIcon(imagemCombinada));
                this.setText(Integer.toString(peso));
                this.setForeground(Color.white);
                this.setHorizontalTextPosition(JButton.RIGHT);
                this.setVerticalTextPosition(JButton.TOP);
                this.setIconTextGap(-50);
            }
            catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public void removerPeso() {
        this.setIcon(celulaIcon);
        this.setText("");
    }

    /**
     * Método para adicionar o ícone de uma flor sobre a célula do terreno na posição especificada.
     *
     * @param x A coordenada X onde a flor será posicionada.
     * @param y A coordenada Y onde a flor será posicionada.
     */
    public void posicionarFlor(int x, int y, String cor) {
        String caminhoFlor = "/interfaceVisual/imagens/flores/flor_" + cor + ".png";

        // Carrega a imagem da flor
        ImageIcon florIcon = new ImageIcon(this.getClass().getResource(caminhoFlor));

        // Utiliza o método combinarImagens da classe Imagem para combinar a célula e a flor na posição especificada
        BufferedImage imagemCombinada = Imagem.combinarImagens(celulaIcon, florIcon, x, y);

        // Define a nova imagem combinada como o ícone do botão
        this.celulaIcon = new ImageIcon(imagemCombinada);
    }

    public PainelMapa getPainelMapa() {
        return painelMapa;
    }

    public int getPosicaoX() {
        return posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public CelulaTerreno getCelulaTerreno() {
        return celulaTerreno;
    }

    public ImageIcon getCelulaIcon() {
        return celulaIcon;
    }
}
