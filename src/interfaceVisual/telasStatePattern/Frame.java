package interfaceVisual.telasStatePattern;

import javax.swing.*;
import java.awt.*;

/** Define a janela principal da interface gráfica do jogo.
 */
public class Frame{
    private Tela start;
    private Tela menu;
    private Tela configurarTerreno;
    private Tela importarTerreno;
    private Tela jogo;
    private Tela geradorTerreno;
    private Tela creditos;

    private boolean temTerreno;
    private boolean comecouPartida;

    private Tela atual;
    private JFrame frame;
    /**
     * O construtor estabelece a configuração do JFrame e inicializa as telas do jogo, bem como o
     * reprodutor musical.
     */
    public Frame(){
        frame = new JFrame("Cata-Frutas");
        frame.setSize(1024,624);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/interfaceVisual/imagens/gameIcon.png")));

        this.start = new Start(this);
        this.menu = new Menu(this);
        this.configurarTerreno = new ConfigurarTerreno(this);
        this.importarTerreno = new ImportarTerreno(this);
        this.jogo = new Jogo(this);
        this.geradorTerreno = new GeradorTerreno(this);
        this.creditos = new Creditos(this);
        this.atual = this.start;

        frame.add(atual.getPanel());
      
        // jaco mp3 player precisa receber o caminho do arquivo de som a partir do src/
        // reprodutorDeSom = new ReprodutorDeSom("src/interfaceVisual/soundtrack/CataFrutasOST_1.mp3");

        //tem terreno e comecou partida são falsos quando instanciados.
        frame.setVisible(true);
    }

    /** Altera a tela do frame pela nova passada como parâmetro.
     * @param novaTela
     */
    public void setState(Tela novaTela){
        frame.remove(atual.getPanel());
        this.atual = novaTela;
        frame.add(atual.getPanel());
        frame.repaint();
    }

    public Tela getStart() {
        return start;
    }

    public Tela getMenu() {
        return menu;
    }

    public Tela getConfigurarTerreno() { return configurarTerreno; }
    public Tela getImportarTerreno() { return importarTerreno; }

    public Tela getJogo() {
        return jogo;
    }

    public Tela getCreditos() {
        return creditos;
    }

    public boolean getTerrenoCarregado(){
        return  this.temTerreno;
    }

    public void setTerrenoCarregado(Boolean b){
        this.temTerreno = b;
    }

    public boolean getTemPartida(){
        return  this.comecouPartida;
    }

    public void setTemPartida(Boolean b){
        this.comecouPartida = b;
    }

}
