package backend.aplicacao;
import backend.dados.App;
import backend.dados.transporte.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;



public class EntradaTransporte{
    
    private Scanner sc = new Scanner(System.in); 
    private PrintStream saidaPadrao = System.out; 
    private String nomeArquivoEntrada;  // Nome do arquivo de entrada de dados
    private App app;

    public EntradaTransporte(App app, String nomeArquivo) throws Exception{
        if(nomeArquivo.isEmpty()){throw new NullPointerException();}
        this.nomeArquivoEntrada = nomeArquivo +"-TRANSPORTE.CSV";
        this.app = app;
        redirecionaEntrada();    // Redireciona Entrada para arquivos
    }

    private void redirecionaEntrada() throws Exception{
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("arquivosDeEntradaESaida/"+nomeArquivoEntrada));
            sc = new Scanner(streamEntrada);   // Usa como entrada um arquivo
        } catch (Exception e){
            throw new NullPointerException();
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
        sc.useLocale(Locale.ENGLISH);   // Ajusta para leitura para ponto decimal
    }

    public int cadastraTransporte(){
        int fail = 0;
        int a = 0;
        while (sc.hasNextLine()) { 
            String linha = sc.nextLine();
            String[] elementos = linha.split(";"); // Separar os elementos da linha utilizando o separador ";"
            try{
                a = Integer.parseInt(elementos[0].trim());
            }catch(Exception e){a=0;}
            switch (a) {
                case 1:
                    try{    
                        if(elementos.length == 10){
                            TransportePessoal transporte = new TransportePessoal(Integer.parseInt(elementos[1].trim()),elementos[2],elementos[3],Double.parseDouble(elementos[4].trim()),Double.parseDouble(elementos[5].trim()),Double.parseDouble(elementos[6].trim()),Double.parseDouble(elementos[7].trim()),Double.parseDouble(elementos[8].trim()),Integer.parseInt(elementos[9].trim()));
                            app.cadastraTransporte(transporte);
                        }else{fail++;}  
                    }catch(Exception e){fail++;}
                    break;
                case 2:
                    try{
                        if(elementos.length == 10){
                            TransporteCargaInanimada transporte = new TransporteCargaInanimada(Integer.parseInt(elementos[1].trim()),elementos[2],elementos[3],Double.parseDouble(elementos[4].trim()),Double.parseDouble(elementos[5].trim()),Double.parseDouble(elementos[6].trim()),Double.parseDouble(elementos[7].trim()),Double.parseDouble(elementos[8].trim()),elementos[9].trim().contains("true"));
                            app.cadastraTransporte(transporte);
                        }else{fail++;}  
                    }catch(Exception e){fail++;} 
                    break;
                case 3:
                    try{
                        if(elementos.length == 11){
                            TransporteCargaViva transporte = new TransporteCargaViva(Integer.parseInt(elementos[1].trim()),elementos[2],elementos[3],Double.parseDouble(elementos[4].trim()),Double.parseDouble(elementos[5].trim()),Double.parseDouble(elementos[6].trim()),Double.parseDouble(elementos[7].trim()),Double.parseDouble(elementos[8].trim()), Double.parseDouble(elementos[9].trim()),Double.parseDouble(elementos[10].trim()));
                            app.cadastraTransporte(transporte);
                        }else{fail++;}    
                    }catch(Exception e){fail++;} 
                    break;
                default:
                    break;
            }
        }
        return fail;
    }
}