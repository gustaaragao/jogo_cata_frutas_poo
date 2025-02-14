package interfaceVisual.telas;

import javax.swing.*;

import interfaceVisual.soundtrack.ReprodutorDeSom;
import modelo.mapa.Mapa;

import java.awt.*;

/** Define a janela principal da interface gráfica do jogo.
 */
public class Frame{
    private Tela start;
    private Tela menu;
    private Tela configurarTerreno;
    private Tela importarTerreno;
    private Tela jogo;
    private Tela creditos;

    private Tela atual;
    private JFrame frame;
    private ReprodutorDeSom reprodutorDeSom;
    /**
     * O construtor estabelece a configuração do JFrame e inicializa as telas do jogo, bem como o
     * reprodutor musical, este último desabilitado temporariamente.
     */
    public Frame(){
        // Verificar o sistema operacional
        String os = System.getProperty("os.name").toLowerCase();
        int margemX = 0, margemY = 0;

        // Se o sistema for Windows, adicionar margem
        if (os.contains("win")) {
            // TODO: Rapha veja qual o valor exato de margem que precisamos usar aqui.
            margemX = 16;
            margemY = 35; // Ajuste a margem conforme necessário
        }

        frame = new JFrame("Cata-Frutas");
        frame.setSize(1024+margemX,624+margemY);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/interfaceVisual/imagens/icones/gameIcon.png")));

        this.start = new Start(this);
        this.menu = new Menu(this);
        this.configurarTerreno = new ConfigurarTerreno(this);
        this.importarTerreno = new ImportarTerreno(this);
        this.creditos = new Creditos(this);
        this.atual = this.start;

        this.jogo = null; // Essa tela é especial e deve ser criada apenas quando um mapa é feito

        frame.add(atual.getPanel());

        reprodutorDeSom = new ReprodutorDeSom();

        //tem terreno e comecou partida são falsos quando instanciados.
        frame.setVisible(true);
    }

    /** Altera a tela do frame pela nova passada como parâmetro.
     * @param novaTela Tela a ser pintada no frame.
     */
    public void setState(Tela novaTela){
        frame.remove(atual.getPanel());
        this.atual = novaTela;
        frame.add(atual.getPanel());
        frame.repaint();
    }

    public ReprodutorDeSom getReprodutorDeSom() {
        return reprodutorDeSom;
    }

    public Tela getStart() {
        return start;
    }

    public Tela getMenu() {
        return menu;
    }

    public Tela getConfigurarTerreno() { 
    	return configurarTerreno; 
    }
    
    public Tela getImportarTerreno() { 
    	return importarTerreno; 
    }

    public Jogo getJogo() {
        Jogo jogo = (Jogo) this.jogo;
        return jogo;
    }
    
    public void setJogo(Mapa mapa) {
    	this.jogo = new Jogo(this, mapa);
    }
    
    public Tela getCreditos() {
        return creditos;
    }
}
