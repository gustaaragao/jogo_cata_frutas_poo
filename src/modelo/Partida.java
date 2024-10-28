package modelo;

import modelo.entidades.Jogador;
import modelo.mapa.Mapa;
import modelo.tipos.Coordenada;

import java.util.Random;

public class Partida {
    private Mapa mapa;
    private int turno;
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador vez;
    private Random random;

    public Partida(Mapa mapa) {
        random = new Random(System.currentTimeMillis());
        this.mapa = mapa;
        turno = 1;
        jogador1 = mapa.getJogador(0);
        jogador2 = mapa.getJogador(1);
        vez = jogador1;
    }

    public void proximoTurno() {
        turno++;
        vez.setPtsMovimento(0);
        if (vez == jogador1)
            vez = jogador2;
        else
            vez = jogador1;

        if (turno % 2 == 0) {
            // gerar frutaOuro;
        }
    }

    public void moverJogador(int i, int j, int pts) {
        vez.setPtsMovimento(vez.getPtsMovimento()-pts);
        mapa.moverJogador(vez, i, j);
    }

    public Jogador getVez() {
        return vez;
    }

    public int jogarDados() {
        int pontos = random.nextInt(12) + 1;
        vez.setPtsMovimento(pontos);
        return pontos;
    }

    public int getTurno() {
        return turno;
    }
}
