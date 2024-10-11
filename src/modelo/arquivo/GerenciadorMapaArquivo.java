package modelo.arquivo;

import modelo.tipos.QuantidadeFrutas;
import modelo.mapa.MapaConfiguracao;
import java.util.Arrays;
import java.io.*;

/**
 * A classe {@code GerenciadorMapaArquivo} fornece métodos para importar e exportar configurações
 * de mapas de terreno de arquivos de texto. Ela permite ler as configurações de um arquivo e criar
 * um objeto {@link MapaConfiguracao} correspondente, bem como exportar a configuração de um mapa
 * para um arquivo de texto.
 * <p>
 * As funções utilizam {@link BufferedReader} e {@link BufferedWriter} para ler e escrever nos
 * arquivos de maneira eficiente.
 * </p>
 */
public class GerenciadorMapaArquivo {

    /**
     * Importa um arquivo de texto contendo as configurações de um mapa de terreno.
     * <p>
     * O arquivo deve ter configurações como dimensão, quantidade de pedras, probabilidade de frutas bichadas,
     * tamanho da mochila e a quantidade de frutas específicas. A leitura será feita linha por linha, 
     * com cada configuração separada por espaços.
     * </p>
     *
     * @param caminhoArquivo O caminho do arquivo a ser importado.
     * @return Um {@link MapaConfiguracao} contendo as configurações lidas do arquivo, ou {@code null}
     *         caso ocorra algum erro durante a leitura do arquivo.
     */
    public static MapaConfiguracao importarArquivoTerreno(String caminhoArquivo) {
        BufferedReader br = null;
        MapaConfiguracao configMapa = new MapaConfiguracao();
        try {

            br = new BufferedReader(new FileReader(caminhoArquivo));

            String[] FrutasValidas = {"maracuja", "laranja", "abacate", "coco", "acerola", "amora", "goiaba" };

            while (br.ready()) {
                String linha = br.readLine();
                String[] partesLinhas = linha.split(" ");

                switch (partesLinhas[0].toLowerCase()) {
                    case "dimensao":
                        configMapa.setDimensao(Integer.parseInt(partesLinhas[1]));
                        break;
                    case "pedras":
                        configMapa.setQtdPedras(Integer.parseInt(partesLinhas[1]));
                        break;
                    case "bichadas":
                        configMapa.setProbabilidadeBichadas(Integer.parseInt(partesLinhas[1]));
                        break;
                    case "mochila":
                        configMapa.setTamanhoMochila(Integer.parseInt(partesLinhas[1]));
                        break;
                    default: 
                        if (Arrays.asList(FrutasValidas).contains(partesLinhas[0].toLowerCase())) {
                            configMapa.qntFrutasPorTipo.put(partesLinhas[0].toLowerCase(), new QuantidadeFrutas(Integer.parseInt(partesLinhas[1]), Integer.parseInt(partesLinhas[2])));
                        }
                }
            }

            return configMapa;

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + caminhoArquivo);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + caminhoArquivo);
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo.");
                    e.printStackTrace();
                }
            }
        }

        return null; // Retorna null caso haja erro na leitura do arquivo
    }

    /**
     * Exporta as configurações de um {@link MapaConfiguracao} para um arquivo de texto.
     * <p>
     * As configurações do mapa serão convertidas em texto e escritas no arquivo especificado.
     * O arquivo criado poderá ser lido posteriormente utilizando o método {@link #importarArquivoTerreno(String)}.
     * </p>
     *
     * @param caminhoArquivo       O caminho do arquivo para exportar as configurações.
     * @param configuracaoDoMapa   Um objeto {@link MapaConfiguracao} que contém as configurações a serem exportadas.
     */
    public static void exportarArquivoTerreno(String caminhoArquivo, MapaConfiguracao configuracaoDoMapa) {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(caminhoArquivo));

            bw.write(configuracaoDoMapa.toString());

            System.out.println("Arquivo exportado com sucesso para: " + caminhoArquivo);

        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + caminhoArquivo);
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo.");
                    e.printStackTrace();
                }
            }
        }
    }
}
