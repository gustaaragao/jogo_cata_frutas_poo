package modelo.entidades;

// TODO: Provavelmente essa é uma das classes mais complexas!! Vamos precisar voltar aqui mais vezes...
// Definitivamente não está nem perto de finalizar

import modelo.tipos.Coordenada;
import modelo.utils.Efeitos;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que lida com o jogador.
 */
public class Jogador extends ElementoDinamico {

    private final String nome;
    private final Mochila<Fruta> mochila;
    private int ptsMovimento;
    private int forca = 0;
    private ArrayList<Efeitos> efeitos = new ArrayList<>(List.of(Efeitos.NEUTRO));

    public Jogador(String nome, Coordenada coordenada, int tamanhoMochila) {
        super(coordenada);
        this.nome = nome;
        this.mochila = new Mochila(tamanhoMochila);
    }


    // getters & setters ------------------------------
    public String getNome() {
        return nome;
    }

    public int getForca() {
        return this.forca;
    }
    public int getPtsMovimento() {
        return this.ptsMovimento;
    }
    public void setPtsMovimento(int pontos) {
        this.ptsMovimento = pontos;
    }
    // ---------------------------------------------------

    public boolean ColetarFruta(Fruta fruta){
        return this.mochila.armazenarFruta(fruta);
    }

    public boolean ComerFruta(Fruta fruta){
        Fruta frutaMochila = this.mochila.retirarFrutaTipo(fruta);
        if (frutaMochila != null){
            frutaMochila.causarEfeito();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        // TODO: Isso aqui é temporário...
        return "J" + this.nome;
    }



    public void calcularForca(){
        this.forca = mochila.size();
    }

    // Verificar a classe Jogador Utils;

}
