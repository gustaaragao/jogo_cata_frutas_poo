package modelo.arquivo;

import java.util.Random;


public class VerificadorMapaArquivo {

    private int dimensao;
    private int frutasOuroChao;
    private int frutasOuroASurgir;
    private int frutasOuroTotais;
    private int frutasDiversas;
    private int chanceBichada;
    private int pedras;
    private int tamanhoMochila;
    private int arvCoco;
    private int arvLaranja;
    private int arvAbacate;
    private int arvAmora;
    private int arvAcerola;
    private int arvGoiaba;
    private int coco;
    private int laranja;
    private int abacate;
    private int amora;
    private int acerola;
    private int goiaba;
    private int espacoDisponivel;

    private static Random random = new Random(System.currentTimeMillis());

	public VerificadorMapaArquivo() {
        dimensao = 5;
        espacoDisponivel = dimensao*dimensao;

        pedras = 3;
        arvCoco = 1;
        arvLaranja = 1;
        arvAbacate = 1;
        arvAmora = 1;
        arvAcerola = 1;
        arvGoiaba = 1;

        frutasOuroChao = 1;
        frutasOuroASurgir = 2;
        frutasOuroTotais = frutasOuroChao + frutasOuroASurgir;

        frutasDiversas = 8;

        laranja = 0;
        abacate = 0;
        coco = 0;
        acerola = 0;
        amora = 0;
        goiaba = 0;

        chanceBichada = 25;

        tamanhoMochila = frutasOuroTotais/2 + 1;

        espacoDisponivel = espacoDisponivel - (pedras + arvLaranja + arvAbacate +
                arvAmora + arvAcerola + arvGoiaba + arvCoco + frutasOuroChao + frutasDiversas);
    }



    public int getDimensao() {
        return dimensao;
    }

    public boolean setDimensao(int dimensao) {

        int deltaEspaco = dimensao*dimensao - this.dimensao*this.dimensao;
        boolean foraDosLimites = dimensao < 5 || dimensao > 12;

        if (foraDosLimites || (espacoDisponivel + deltaEspaco) <0 ) return false;

        espacoDisponivel = espacoDisponivel + deltaEspaco;
        this.dimensao = dimensao;
        return true;
    }


    public int getFrutasOuroChao() {
        return frutasOuroChao;
    }

    public boolean setFrutasOuroChao(int frutasOuroChao) {
        int deltaFrutasOuro = frutasOuroChao - this.frutasOuroChao;
        int novoTotal = deltaFrutasOuro + frutasOuroTotais;
        //valores invalidos.
        if (frutasOuroChao<0) return false;
        if (novoTotal == 0) return false;

        //quero adicionar mais do que posso.
        if ((espacoDisponivel + deltaFrutasOuro) < 0) return false;

        espacoDisponivel = espacoDisponivel + deltaFrutasOuro;
        this.frutasOuroChao+=deltaFrutasOuro;
        return true;
    }

    public int getFrutasOuroASurgir() {
        return frutasOuroASurgir;
    }

    public boolean setFrutasOuroASurgir(int frutasOuroASurgir) {
        int deltaFrutasOuro = frutasOuroASurgir - this.frutasOuroASurgir;
        int novoTotal = deltaFrutasOuro + frutasOuroTotais;

        if (frutasOuroASurgir < 0) return false;
        if (novoTotal == 0) return false;

        espacoDisponivel += deltaFrutasOuro;
        this.frutasOuroASurgir+= deltaFrutasOuro;
        return true;
    }

    public int getFrutasOuroTotais() {
        frutasOuroTotais = frutasOuroChao + frutasOuroASurgir;
        return frutasOuroTotais;
    }

    /*
    public void setFrutasOuroTotais(int frutasOuroTotais) {
        this.frutasOuroTotais = frutasOuroTotais;
    }
     */

    public int getFrutasDiversas() {
        return frutasDiversas;
    }

    public boolean setFrutasDiversas(int frutasDiversas) {
        int deltaFrutas = frutasDiversas - this.frutasDiversas;

        // valor invalido, ou quero adicionar mais do que posso.
        if (frutasDiversas < 0 || espacoDisponivel - deltaFrutas < 0) return false;

        espacoDisponivel += deltaFrutas;
        this.frutasDiversas+= deltaFrutas;
        return true;
    }

    public int getChanceBichada() {
        return chanceBichada;
    }

    public boolean setChanceBichada(int chanceBichada) {
        if (chanceBichada < 0 || chanceBichada > 100) return false;
        this.chanceBichada = chanceBichada;
        return true;
    }

    public int getPedras() {
        return pedras;
    }

    public boolean setPedras(int pedras) {
        int deltaPedra = pedras - this.pedras;
        if (pedras < 0 || espacoDisponivel + deltaPedra < 0) return false;
        espacoDisponivel += deltaPedra;
        this.pedras += deltaPedra;
        return true;
    }

    public boolean verificarTamanhoMinimo(int frutasOuroTotais, int tamanhoMochila) {
        int tamanhoMinimo = frutasOuroTotais/2 + 1;
        if (tamanhoMochila < tamanhoMinimo) {
            this.tamanhoMochila = (frutasOuroTotais/2)+1;
            return true;
        }
        return false;
    }

    public int getTamanhoMochila() {
        return tamanhoMochila;
    }

    public boolean setTamanhoMochila(int tamanhoMochila) {
        if (tamanhoMochila < (frutasOuroTotais/2 + 1)) return false;
        this.tamanhoMochila = tamanhoMochila;
        return true;
    }


    public int getCoco() {
        return coco;
    }
    public int getLaranja() {
        return laranja;
    }
    public int getAbacate() {
        return abacate;
    }
    public int getAmora() {
        return amora;
    }
    public int getAcerola() {
        return acerola;
    }
    public int getGoiaba() {
        return goiaba;
    }
    public int getArvAmora() {
        return arvAmora;
    }
    public int getArvGoiaba() {
        return arvGoiaba;
    }
    public int getArvAcerola() {
        return arvAcerola;
    }
    public int getArvCoco() {
        return arvCoco;
    }
    public int getArvLaranja() {
        return arvLaranja;
    }
    public int getArvAbacate() {
        return arvAbacate;
    }

    public boolean setArvCoco(int arvCoco) {
        int deltaArvore = arvCoco - this.arvCoco;
        if (arvCoco < 0 || espacoDisponivel + deltaArvore == 0) return false;
        this.arvCoco += deltaArvore;
        espacoDisponivel += deltaArvore;
        return true;
    }

    public boolean setArvLaranja(int arvLaranja) {
        int deltaArvore = arvLaranja - this.arvLaranja;
        if (arvLaranja < 0 || espacoDisponivel + deltaArvore == 0) return false;
        this.arvCoco += deltaArvore;
        espacoDisponivel += deltaArvore;
        return true;
    }

    public boolean setArvAbacate(int arvAbacate) {
        int deltaArvore = arvAbacate - this.arvAbacate;
        if (arvAbacate < 0 || espacoDisponivel + deltaArvore == 0) return false;
        this.arvAbacate += deltaArvore;
        espacoDisponivel += deltaArvore;
        return true;
    }

    public boolean setArvAmora(int arvAmora) {
        int deltaArvore = arvAmora - this.arvAmora;
        if (arvAmora < 0 || espacoDisponivel + deltaArvore == 0) return false;
        this.arvAmora += deltaArvore;
        espacoDisponivel += deltaArvore;
        return true;
    }

    public boolean setArvAcerola(int arvAcerola) {
        int deltaArvore = arvAcerola - this.arvAcerola;
        if (arvAcerola < 0 || espacoDisponivel + deltaArvore == 0) return false;
        this.arvAcerola += deltaArvore;
        espacoDisponivel += deltaArvore;
        return true;
    }

    public boolean setArvGoiaba(int arvGoiaba) {
        int deltaArvore = arvGoiaba - this.arvGoiaba;
        if (arvGoiaba < 0 || espacoDisponivel + deltaArvore == 0) return false;
        this.arvGoiaba += deltaArvore;
        espacoDisponivel += deltaArvore;
        return true;
    }


    public int getEspacoDisponivel() {
        return espacoDisponivel;
    }

    public void setEspacoDisponivel(int espacoDisponivel) {
        this.espacoDisponivel = espacoDisponivel;
    }


    public void distribuirFrutas() {
        for (int i = 0; i < frutasDiversas; i++) {
            switch (random.nextInt(6)) {
                case(0):
                    laranja++;
                    break;
                case(1):
                    abacate++;
                    break;
                case(2):
                    coco++;
                    break;
                case(3):
                    acerola++;
                    break;
                case(4):
                    amora++;
                    break;
                case(5):
                    goiaba++;
                    break;
                default:
                    break;
            }
        }
    }

}
