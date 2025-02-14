package modelo.entidades;

// TODO: Provavelmente essa é uma das classes mais complexas!! Vamos precisar voltar aqui mais vezes...
// Definitivamente não está nem perto de finalizar

import com.sun.source.tree.BreakTree;
import modelo.frutas.Laranja;
import modelo.frutas.Maracuja;
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
    private Integer ptsMovimento;
    private Efeitos efeito = Efeitos.NEUTRO;
    private CelulaTerreno celulaOcupada;
    private ArrayList<Efeitos> efeitos = new ArrayList<>(List.of(Efeitos.NEUTRO));

    public Jogador(String nome, Coordenada coordenada, int tamanhoMochila) {
        super(coordenada);
        this.nome = nome;
        this.mochila = new Mochila(tamanhoMochila, 0);
    }


    // getters & setters ------------------------------
    public CelulaTerreno getCelulaOcupada() {
        return celulaOcupada;
    }

    public void setCelulaOcupada(CelulaTerreno celulaOcupada) {
        this.celulaOcupada = celulaOcupada;
    }

    public String getNome() {
        return nome;
    }
    public Integer getPtsMovimento() {
        return this.ptsMovimento;
    }
    public void setPtsMovimento(Integer pontos) {
        this.ptsMovimento = pontos;
    }
    public Mochila getMochila(){return this.mochila;}
    // ---------------------------------------------------

    public boolean coletarFruta(Fruta fruta){
        ptsMovimento--;
        return this.mochila.armazenarFruta(fruta);
    }

    public boolean comerFruta(Fruta fruta){
        ptsMovimento--;
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



    public int calcularForca(){
        return mochila.size();
    }

    // Verificar a classe Jogador Utils;

}
