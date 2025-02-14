package modelo.mapa;

import java.util.HashMap;
import java.util.Map;

import modelo.arquivo.VerificadorConfiguracao;
import modelo.tipos.QuantidadeFrutas;

/**
 * Essa classe é responsável por conter as informações básicas para a geração de um mapa.
 */
public class MapaConfiguracao {

    /**
     * Dinmensão escolhida.
     */
    protected int dimensao;

    /**
     * Quantidade de pedras no mapa
     */
    protected int qtdPedras;

    /**
     * Mapa de nomes para quantidades, essencial na geração do mapa.
     *
     * @see Mapa
     */
    public Map<String, QuantidadeFrutas> qntFrutasPorTipo = new HashMap<>();

    /**
     * Probailidade geral de uma fruta ser bichada. Todas as frutas do mapa respeitam essa propriedade.
     */
    protected int probabilidadeBichadas;

    /**
     * Tamanho da mochila máximo do jogador.
     */
    protected int tamanhoMochila;
    private int frutasOuroTotais;
    public int frutasOuroChão;

    public MapaConfiguracao() {}

    public MapaConfiguracao(VerificadorConfiguracao verificador) {
        this.dimensao = verificador.getDimensao();
        this.qtdPedras = verificador.getPedras();
        this.probabilidadeBichadas = verificador.getChanceBichada();
        this.tamanhoMochila = verificador.getTamanhoMochila();
        frutasOuroTotais = verificador.getFrutasOuroTotais();
        frutasOuroChão = verificador.getFrutasOuroChao();

        String[] FrutasValidas = {"maracuja", "laranja", "abacate", "coco", "acerola", "amora", "goiaba" };

        qntFrutasPorTipo.put("maracuja", new QuantidadeFrutas(verificador.getFrutasOuroASurgir(), verificador.getFrutasOuroChao()));
        qntFrutasPorTipo.put("laranja", new QuantidadeFrutas(verificador.getArvLaranja(), verificador.getLaranja()));
        qntFrutasPorTipo.put("abacate", new QuantidadeFrutas(verificador.getArvAbacate(), verificador.getAbacate()));
        qntFrutasPorTipo.put("coco", new QuantidadeFrutas(verificador.getArvCoco(), verificador.getCoco()));
        qntFrutasPorTipo.put("acerola", new QuantidadeFrutas(verificador.getArvAcerola(), verificador.getAcerola()));
        qntFrutasPorTipo.put("amora", new QuantidadeFrutas(verificador.getArvAmora(), verificador.getAmora()));
        qntFrutasPorTipo.put("goiaba", new QuantidadeFrutas(verificador.getArvGoiaba(), verificador.getGoiaba()));
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }
    public void setQtdPedras(int qtdPedras){
        this.qtdPedras = qtdPedras;
    }

    public void setProbabilidadeBichadas(int probabilidadeBichadas) {
        this.probabilidadeBichadas = probabilidadeBichadas;
    }

    public void setTamanhoMochila(int tamanhoMochila) {
        this.tamanhoMochila = tamanhoMochila;
    }

    public int getProbabilidadeBichadas(){return this.probabilidadeBichadas;}
    public int getFrutasOuroTotais() {
        return frutasOuroTotais;
    }

    
    /**
     * Transformar uma configuração em um mapa. Necessário para exportar um arquivo nas configurações certas.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("dimensao ").append(dimensao).append("\n");
        sb.append("pedras ").append(qtdPedras).append("\n");

        sb.append("maracuja").append(" ")
                .append(qntFrutasPorTipo.get("maracuja").arvore).append(" ")
                .append(qntFrutasPorTipo.get("maracuja").grama).append("\n");
        sb.append("laranja").append(" ")
                .append(qntFrutasPorTipo.get("laranja").arvore).append(" ")
                .append(qntFrutasPorTipo.get("laranja").grama).append("\n");
        sb.append("abacate").append(" ")
                .append(qntFrutasPorTipo.get("abacate").arvore).append(" ")
                .append(qntFrutasPorTipo.get("abacate").grama).append("\n");
        sb.append("coco").append(" ")
                .append(qntFrutasPorTipo.get("coco").arvore).append(" ")
                .append(qntFrutasPorTipo.get("coco").grama).append("\n");
        sb.append("acerola").append(" ")
                .append(qntFrutasPorTipo.get("acerola").arvore).append(" ")
                .append(qntFrutasPorTipo.get("acerola").grama).append("\n");
        sb.append("amora").append(" ")
                .append(qntFrutasPorTipo.get("amora").arvore).append(" ")
                .append(qntFrutasPorTipo.get("amora").grama).append("\n");
        sb.append("goiaba").append(" ")
                .append(qntFrutasPorTipo.get("goiaba").arvore).append(" ")
                .append(qntFrutasPorTipo.get("goiaba").grama).append("\n");

        /*
        for (Map.Entry<String, QuantidadeFrutas> entry : qntFrutasPorTipo.entrySet()) {
            sb.append(entry.getKey()).append(" ")
              .append(entry.getValue().grama).append(" ")
              .append(entry.getValue().arvore).append("\n");
        }
         */
        
        sb.append("bichadas ").append(probabilidadeBichadas).append("\n");
        sb.append("mochila ").append(tamanhoMochila);
        
        return sb.toString();
    }
}
