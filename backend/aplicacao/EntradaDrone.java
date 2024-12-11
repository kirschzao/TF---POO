package backend.aplicacao;
import backend.dados.App;
import backend.dados.drone.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;



public class EntradaDrone{
    
    private Scanner sc = new Scanner(System.in); 
    private PrintStream saidaPadrao = System.out; 
    private String nomeArquivoEntrada;  // Nome do arquivo de entrada de dados
    private App app;

    public EntradaDrone(App app, String nomeArquivo) throws Exception{
        if(nomeArquivo.isEmpty()){throw new NullPointerException();}
        this.nomeArquivoEntrada = nomeArquivo +"-DRONES.CSV";
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

    public int cadastraDrone(){
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
                        if(elementos.length == 5){
                            DronePessoal drone = new DronePessoal(Integer.parseInt(elementos[1].trim()), Double.parseDouble(elementos[2].trim()), Double.parseDouble(elementos[3].trim()), Integer.parseInt(elementos[4].trim()));
                            app.cadastraDrone(drone);
                        }else{fail++;}  
                    }catch(Exception e){fail++;}
                    break;
                case 2:
                    try{
                        if(elementos.length == 6){
                            DroneCargaInanimada drone = new DroneCargaInanimada(Integer.parseInt(elementos[1].trim()), Double.parseDouble(elementos[2].trim()), Double.parseDouble(elementos[3].trim()), Double.parseDouble(elementos[4].trim()),elementos[5].trim().contains("true"));
                            app.cadastraDrone(drone);
                        }else{fail++;}  
                    }catch(Exception e){fail++;} 
                    break;
                case 3:
                    try{
                        if(elementos.length == 6){
                            DroneCargaViva drone = new DroneCargaViva(Integer.parseInt(elementos[1].trim()), Double.parseDouble(elementos[2].trim()), Double.parseDouble(elementos[3].trim()), Double.parseDouble(elementos[4].trim()), elementos[5].trim().contains("true"));
                            app.cadastraDrone(drone);
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