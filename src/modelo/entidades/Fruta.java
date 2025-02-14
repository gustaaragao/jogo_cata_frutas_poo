package modelo.entidades;

import modelo.utils.Efeitos;

import java.util.ArrayList;

/**
 * Essa classe implementa o comportamento abstrato das frutas do jogo.
 */
public abstract class Fruta {

    /**
     * Toda fruta tem a propriedade booleana de estar bichada ou não.
     */
    private boolean bichada;

    /**
     * O nome da fruta é implementado internamente apenas como identidade da fruta.
     */
    private String nome;

    public Fruta(boolean bichada, String nome) {
        this.bichada = bichada;
        this.nome = nome;
    }


    // getters & setters ------------------------------
    public void bicharFruta() {
    	bichada=true;
    }
    public boolean isBichada() {
        return bichada;
    }
    public void setBichada(boolean bichada) {
        this.bichada = bichada;
    }
    // ------------------------------------------------


    public ArrayList<Efeitos> causarEfeito(){
        ArrayList<Efeitos> efeitos = new ArrayList<>();
        if (this.bichada) efeitos.add(Efeitos.ENVENENAMENTO);
        efeitos.add(Efeitos.NEUTRO);
        return efeitos;
    }

    @Override
    public String toString(){
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
