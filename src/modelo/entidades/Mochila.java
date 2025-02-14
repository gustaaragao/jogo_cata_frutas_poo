package modelo.entidades;

import modelo.frutas.*;

import java.util.ArrayList;
import java.util.Random;

// TODO: Provavelmente vamos precisar rever essa classe aqui
public class Mochila<T> extends ArrayList<T> {
    private int qntAbacate;
    private int qntAmora;
    private int qntGoiaba;
    private int qntLaranja;
    private int qntCoco;
    private int qntAcerola;
    private int tamanhoMochila;
    private int quantidadeFrutasOuro;

    public Mochila(int tamanhoMochila, int quantidadeFrutasOuro){
        this.tamanhoMochila = tamanhoMochila;
        this.quantidadeFrutasOuro = quantidadeFrutasOuro;
        qntAcerola = 0;
        qntAbacate = 0;
        qntCoco = 0;
        qntAmora = 0;
        qntGoiaba = 0;
        qntLaranja = 0;
    }

    public boolean armazenarFruta(T fruta) {
        if (this.size() < tamanhoMochila){
            this.add(fruta);
            if (fruta instanceof Maracuja) {
                quantidadeFrutasOuro++;
            }
            if (fruta instanceof Acerola)
                qntAcerola++;
            if (fruta instanceof Abacate)
                qntAbacate++;
            if (fruta instanceof Amora)
                qntAmora++;
            if (fruta instanceof Coco)
                qntCoco++;
            if (fruta instanceof Laranja)
                qntLaranja++;
            if (fruta instanceof Goiaba)
                qntGoiaba++;
            return true;
        }
        return false;
    }

    public Fruta retirarFrutaTipo(Fruta fruta) {
        int index = this.indexOf(fruta);
        if(index != -1){
            if (fruta instanceof Acerola)
                qntAcerola--;
            if (fruta instanceof Abacate)
                qntAbacate--;
            if (fruta instanceof Amora)
                qntAmora--;
            if (fruta instanceof Coco)
                qntCoco--;
            if (fruta instanceof Laranja)
                qntLaranja--;
            if (fruta instanceof Goiaba)
                qntGoiaba--;
            return (Fruta) this.remove(index);
        }
        return null;
    }

    public ArrayList<Fruta> removeNFrutas(int quantidadeFrutas){
        ArrayList<Fruta> frutasRemovidas = new ArrayList<>();
        Random r = new Random();
        for(int n = 0; n < quantidadeFrutas && !this.isEmpty();n++){
            frutasRemovidas.add((Fruta) this.remove(r.nextInt(this.size())));
        }
        return frutasRemovidas;
    }

    public int getTamanhoMochila(){
        return tamanhoMochila;
    }

    public int getQuantidadeFrutasOuro(){
        return quantidadeFrutasOuro;
    }

    public Fruta getFruta(String nomeFruta) {
        for (int i = 0; i < this.size(); i++) {
            Fruta fruta = (Fruta) this.get(i);
            if (fruta.getNome().equals(nomeFruta)) {
                return fruta;
            }
        }
        return null;
    }

    public int getQntAbacate() {
        return qntAbacate;
    }

    public int getQntAmora() {
        return qntAmora;
    }

    public int getQntGoiaba() {
        return qntGoiaba;
    }

    public int getQntLaranja() {
        return qntLaranja;
    }

    public int getQntCoco() {
        return qntCoco;
    }

    public int getQntAcerola() {
        return qntAcerola;
    }
}
