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

        int deltaEspaco = dimensao^2 - this.dimensao^2;
        boolean foraDosLimites = dimensao < 5 || dimensao > 12;

        if (foraDosLimites || deltaEspaco - espacoDisponivel < 0) return false;

        espacoDisponivel = espacoDisponivel + deltaEspaco;
        this.dimensao = dimensao;
        return true;
    }


    public int getFrutasOuroChao() {
        return frutasOuroChao;
    }

    public boolean setFrutasOuroChao(int frutasOuroChao) {
        int deltaFrutasOuro = frutasOuroChao - this.frutasOuroChao;

        //valores invalidos.
        if (frutasOuroChao<0) return false;
        if (frutasOuroChao == 0 && frutasOuroTotais == 0) return false;

        //quero adicionar mais do que posso.
        if ((espacoDisponivel + deltaFrutasOuro) <= 0) return false;
        
        this.frutasOuroChao+=deltaFrutasOuro;
        return true;
    }

    public int getFrutasOuroASurgir() {
        return frutasOuroASurgir;
    }

    public boolean setFrutasOuroASurgir(int frutasOuroASurgir) {
        if (frutasOuroASurgir < 0)
            return false;
        else if (frutasOuroASurgir == 0 && frutasOuroTotais == 0)
            return false;
        else {
            if (frutasOuroASurgir < this.frutasOuroASurgir)
                frutasOuroTotais--;
            else
                frutasOuroTotais++;
            this.frutasOuroASurgir = frutasOuroASurgir;
            return true;
        }
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
        if (espacoDisponivel < 1 && frutasDiversas > this.frutasDiversas)
            return false;
        else if (frutasDiversas < 0)
            return false;
        else {
            if (frutasDiversas < this.frutasDiversas)
                espacoDisponivel++;
            else
                espacoDisponivel--;
            this.frutasDiversas = frutasDiversas;
            return true;
        }
    }

    public int getChanceBichada() {
        return chanceBichada;
    }

    public boolean setChanceBichada(int chanceBichada) {
        if (chanceBichada < 0 || chanceBichada > 100)
            return false;
        else {
            this.chanceBichada = chanceBichada;
            return true;
        }
    }

    public int getPedras() {
        return pedras;
    }

    public boolean setPedras(int pedras) {
        if (espacoDisponivel < 1 && pedras > this.pedras)
            return false;
        else if (pedras < 0)
            return false;
        else {
            if (pedras < this.pedras)
                espacoDisponivel++;
            else
                espacoDisponivel--;
            this.pedras = pedras;
            return true;
        }
    }

    public boolean verificarTamanhoMinimo(int frutasOuroTotais, int tamanhoMochila) {
        int aux = frutasOuroTotais/2 + 1;
        if (tamanhoMochila < aux) {
            this.tamanhoMochila = (frutasOuroTotais/2)+1;
            return true;
        }
        return false;
    }

    public int getTamanhoMochila() {
        return tamanhoMochila;
    }

    public boolean setTamanhoMochila(int tamanhoMochila) {
        if (tamanhoMochila < (frutasOuroTotais/2 + 1)) {
            return false;
        }
        this.tamanhoMochila = tamanhoMochila;
        return true;
    }

    public int getArvCoco() {
        return arvCoco;
    }

    public boolean setArvCoco(int arvCoco) {
        if (espacoDisponivel < 1 && arvCoco > this.arvCoco)
            return false;
        else if (arvCoco < 0)
            return false;
        else {
            if (arvCoco < this.arvCoco)
                espacoDisponivel++;
            else
                espacoDisponivel--;
            this.arvCoco = arvCoco;
            return true;
        }
    }

    public int getArvLaranja() {
        return arvLaranja;
    }

    public boolean setArvLaranja(int arvLaranja) {
        if (espacoDisponivel < 1 && arvLaranja > this.arvLaranja)
            return false;
        else if (arvLaranja < 0)
            return false;
        else {
            if (arvLaranja < this.arvLaranja)
                espacoDisponivel++;
            else
                espacoDisponivel--;
            this.arvLaranja = arvLaranja;
            return true;
        }
    }

    public int getArvAbacate() {
        return arvAbacate;
    }

    public boolean setArvAbacate(int arvAbacate) {
        if (espacoDisponivel < 1 && arvAbacate > this.arvAbacate)
            return false;
        else if (arvAbacate < 0)
            return false;
        else {
            if (arvAbacate < this.arvAbacate)
                espacoDisponivel++;
            else
                espacoDisponivel--;
            this.arvAbacate = arvAbacate;
            return true;
        }
    }

    public int getArvAmora() {
        return arvAmora;
    }

    public boolean setArvAmora(int arvAmora) {
        if (espacoDisponivel < 1 && arvAmora > this.arvAmora)
            return false;
        else if (arvAmora < 0)
            return false;
        else {
            if (arvAmora < this.arvAmora)
                espacoDisponivel++;
            else
                espacoDisponivel--;
            this.arvAmora = arvAmora;
            return true;
        }
    }

    public int getArvAcerola() {
        return arvAcerola;
    }

    public boolean setArvAcerola(int arvAcerola) {
        if (espacoDisponivel < 1 && arvAcerola > this.arvAcerola)
            return false;
        else if (arvAcerola < 0)
            return false;
        else {
            if (arvAcerola < this.arvAcerola)
                espacoDisponivel++;
            else
                espacoDisponivel--;
            this.arvAcerola = arvAcerola;
            return true;
        }
    }

    public int getArvGoiaba() {
        return arvGoiaba;
    }

    public boolean setArvGoiaba(int arvGoiaba) {
        if (espacoDisponivel < 1 && arvGoiaba > this.arvGoiaba)
            return false;
        else if (arvGoiaba < 0)
            return false;
        else {
            if (arvGoiaba < this.arvGoiaba)
                espacoDisponivel++;
            else
                espacoDisponivel--;
            this.arvGoiaba = arvGoiaba;
            return true;
        }
    }

    public int getEspacoDisponivel() {
        return espacoDisponivel;
    }

    public void setEspacoDisponivel(int espacoDisponivel) {
        this.espacoDisponivel = espacoDisponivel;
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
