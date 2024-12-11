package backend.aplicacao;
import backend.dados.*;
import backend.dados.drone.*;
import java.io.File;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;



public class SaidaDrone{
    
    private Scanner sc = new Scanner(System.in); 
    private PrintStream saidaPadrao = System.out; 
    private String nomeArquivoSaida;  // Nome do arquivo de saida de dados
    private App app;;

    public SaidaDrone(App app, String nomeArquivo){
        this.nomeArquivoSaida = nomeArquivo + "-DRONES.CSV";
        this.app = app;
        redirecionaSaida();    // Redireciona Saida para arquivos
    }

    private void redirecionaSaida() {
        try {
            PrintStream streamSaida = new PrintStream(new File("arquivosDeEntradaESaida/"+nomeArquivoSaida), Charset.forName("UTF-8"));
            System.setOut(streamSaida);             // Usa como saida um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
    }

    public void salvaDrone(){
        ArrayList<Drone> lista = app.getListadeDrones();
        System.out.println("tipo;codigo;custofixo;autonomia;qtdmaxpessoas_pesomaximo;protecao_climatizado");
        for (Drone drone : lista){
            if(drone instanceof DronePessoal){
                int numero = (int) ((DronePessoal) drone).getQtndPessoas();
                System.out.println("1;"+drone.getCodigo()+";"+drone.getCustoFixo()+";"+drone.getAutonomia()+";"+numero);
            }
            if(drone instanceof DroneCargaInanimada){
                System.out.println("2;"+drone.getCodigo()+";"+drone.getCustoFixo()+";"+drone.getAutonomia()+";"+((DroneCargaInanimada) drone).getPesoMaximo()+";"+((DroneCargaInanimada) drone).getProtecao());
            }
            if(drone instanceof DroneCargaViva){
                System.out.println("3;"+drone.getCodigo()+";"+drone.getCustoFixo()+";"+drone.getAutonomia()+";"+((DroneCargaViva) drone).getPesoMaximo()+";"+((DroneCargaViva) drone).getClimatizado());
            }
        }      


    }
}