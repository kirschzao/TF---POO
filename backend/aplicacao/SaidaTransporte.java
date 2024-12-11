package backend.aplicacao;
import backend.transporte.*;
import backend.dados.App;
import backend.dados.transporte.*;
import java.io.File;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;



public class SaidaTransporte{
    
    private Scanner sc = new Scanner(System.in); 
    private PrintStream saidaPadrao = System.out; 
    private String nomeArquivoSaida;  // Nome do arquivo de saida de dados
    private App app;;

    public SaidaTransporte(App app, String nomeArquivo){
        this.nomeArquivoSaida = nomeArquivo + "-TRANSPORTE.CSV";
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

    public void salvaTransporte(){
        ArrayList<Transporte> lista = app.getListadeTransportes();
        System.out.println("tipo;numero;nomecliente;descricao;peso;latorigem;longorigem;latdestino;longdestino;qtdpessoas_perigosa_tempmin;tempmax");
        for (Transporte transporte : lista){
            if(transporte instanceof TransportePessoal){
                System.out.println("1;"+transporte.getNumero()+";"+transporte.getNome()+";"+transporte.getDesc()+";"+transporte.getPeso()+";"+transporte.getLatOrigem()+";"+transporte.getLonOrigem()+";"+transporte.getLatDestino()+";"+transporte.getLonDestino()+";"+((TransportePessoal) transporte).getQtndPessoas());
            }
            if(transporte instanceof TransporteCargaInanimada){
                System.out.println("2;"+transporte.getNumero()+";"+transporte.getNome()+";"+transporte.getDesc()+";"+transporte.getPeso()+";"+transporte.getLatOrigem()+";"+transporte.getLonOrigem()+";"+transporte.getLatDestino()+";"+transporte.getLonDestino()+";"+((TransporteCargaInanimada) transporte).getCargaPerigosa());
            }
            if(transporte instanceof TransporteCargaViva){
                System.out.println("3;"+transporte.getNumero()+";"+transporte.getNome()+";"+transporte.getDesc()+";"+transporte.getPeso()+";"+transporte.getLatOrigem()+";"+transporte.getLonOrigem()+";"+transporte.getLatDestino()+";"+transporte.getLonDestino()+";"+((TransporteCargaViva) transporte).getTempMin()+";"+((TransporteCargaViva) transporte).getTempMax());
            }
        }      
    }
}