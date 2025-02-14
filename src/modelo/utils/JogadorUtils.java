package modelo.utils;

import modelo.entidades.Fruta;
import modelo.entidades.Jogador;

import java.util.ArrayList;

import static java.lang.Math.*;

public class JogadorUtils {

    public static int numeroDerrubadas(Jogador jogadorAtacante, Jogador jogadorDefensor, int espacoDisponivel) {
        int fa = jogadorAtacante.calcularForca();
        int fd = jogadorDefensor.calcularForca();
        return  min((int) (round(log2(fa+1)) - round(log2(fd+1))), espacoDisponivel);
    }

    public static ArrayList<Fruta> frutasDerrubadas(Jogador jogadorAtacante, Jogador jogadorDefensor, int espacoDisponivel){
        int nDerrubadas = numeroDerrubadas(jogadorAtacante, jogadorDefensor, espacoDisponivel);
        if (nDerrubadas <= 0) return null;
        return jogadorDefensor.getMochila().removeNFrutas(nDerrubadas);
    }

    public static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }
}
