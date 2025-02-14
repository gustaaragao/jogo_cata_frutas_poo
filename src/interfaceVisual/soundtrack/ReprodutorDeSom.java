package interfaceVisual.soundtrack;

import javax.sound.sampled.*;
import java.net.URL;
import java.io.IOException;

public class ReprodutorDeSom {
    private Clip clip;
    private Clip botao;
    boolean isAtivo;

    public ReprodutorDeSom() {
        try {
            URL musica = getClass().getClassLoader().getResource("interfaceVisual/soundtrack/CataFrutasOST_1.wav");
            if (musica == null) {
                System.out.println("Erro ao carregar o arquivo de música.");
                return;
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musica);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            isAtivo = true;

        }
        catch (Exception e) {
            e.getMessage();
        }
    }

    public void pausarMusica() {
        if (clip.isActive()) {
            clip.stop();
        }
    }

    public void reproduzirMusica() {
        if (!clip.isActive()) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void tocarBotao() {
        try {
            URL botao = getClass().getClassLoader().getResource("interfaceVisual/soundtrack/pressionarBotao.wav");
            if (botao == null) {
                System.out.println("Erro ao carregar o arquivo de botão.");
                return;
            }

            AudioInputStream audioInputStreamBotao = AudioSystem.getAudioInputStream(botao);
            this.botao = AudioSystem.getClip();
            this.botao.open(audioInputStreamBotao);
            this.botao.start();
        }
        catch (Exception e) {
            e.getMessage();
        }
    }

    public boolean getIsAtivo() {
        return isAtivo;
    }

    public void setIsAtivo(boolean ativo) {
        isAtivo = ativo;
    }
}
