package backend.dados;
import backend.dados.drone.*;
import backend.dados.transporte.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class App {
    
    private Queue<Transporte> filaDeTransporte;
    private ArrayList<Drone> listaDrones;
    private ArrayList<Transporte> listaTransportes;


    public App(){
        this.filaDeTransporte = new LinkedList<>();
        this.listaDrones = new ArrayList<Drone>();
        this.listaTransportes = new ArrayList<Transporte>();

    }



    // PASSO 1:
    //CADASTRAR UM DRONE RECEBIDO PELO PARAMETRO -> PRECISAMOS CRIAR UM AUXILIAR NO GUI;
    // SE JÁ TIVER CODIGO, LANÇA ERRO!
    //ADICONA NA LISTA DE DRONES
    public boolean cadastraDrone(Drone drone) throws Exception{
        for (Drone suporte : listaDrones) {
            if(suporte.getCodigo() == drone.getCodigo()){
                throw new Exception("Não foi possível adicionar drone "+ drone.getCodigo()+";, código já existente!");
            }
        }
        listaDrones.add(drone);
        listaDrones.sort(Comparator.comparing(Drone::getCodigo)); //mantem os drones ordenados
        return true;
    }



    // PASSO 2:
    //CADASTRAR UM TRANSPORTE RECEBIDO PELO PARAMETRO -> PRECISAMOS CRIAR UM AUXILIAR NO GUI;
    // SE JÁ TIVER NUMERO, LANÇA ERRO!
    //ADICIONA NA LISTA DE TRANSPORTE E NA FILA DE TRANSPORTES PENDENTES
    public void cadastraTransporte(Transporte transporte) throws Exception{
        for (Transporte suporte : listaTransportes) {
            if(transporte.getNumero() == suporte.getNumero()){
                throw new Exception("Não foi possível adicionar transporte, número já existente!");
            }
        }
        listaTransportes.add(transporte);
        filaDeTransporte.add(transporte);
    }



    //PASSO 3:
    //REMOVE O TRANSPORTE DA FILA E VE SE TEM UM DRONE DISPONIVEL -> ESSA CHECAGEM É FEITA PELO METODO VERIFICACOMBATIBILIDADE
    //SOMENTE UM ELEMENTO DA FILA POR VEZ
    //SE FOR POSSIVEL, ELE MANDA MENSAGEM DE SUCESSO
    //SE NÃO FOR, ELE RETORNA PRO FINAL DA FILA E MANDA UMA EXCEPTION
    //TEMOS QUE IMPLEMENTAR UM WHILE COM TRY CATCH PARA LANÇAR VÁRIAS EXCEPTIONS DE VARIOS TIPOS
    public String processaTransportePendentesUnico() throws Exception{
        int size = filaDeTransporte.size();
        if(size == 0){
            throw new Exception("Não existe nenhum transporte pendente na fila!");
        }

        StringBuilder resultado = new StringBuilder();
        while(size > 0){
            Transporte transporte = filaDeTransporte.poll();
            if(verificaCompatibildade(transporte)){
                resultado.append("Transporte ").append(transporte.getNumero()).append(" , foi alocado com sucesso!\n"); //DEPENDE DO METODO DE IMPLEMENTAÇÃO
            }else{
                filaDeTransporte.add(transporte);
            }
            size--;
        }

        if(resultado.length() == 0){
            throw new Exception("Não foi possível alocar nenhum transporte!");
        }else{
            return resultado.toString();
        } 
    }



    //PASSO 4: 
    //PODE IMPRIMIR TODOS OS TO STRING DOS DRONES E TRANSPORTE:
    //ESSA INFORMAÇAO PODE SER SALVA COMO STRING OU PRINTADA (FOI IMPLENTADA COMO PRINT, MAS É TRANQUILO DE MUDAR)
    //LANÇA EXCEPTION SE NAO TIVER NENHUM DRONE OU TRANSPORTE
    public String mostrarRelatorioGeral() throws Exception{

        if(listaDrones.isEmpty() && listaTransportes.isEmpty()){
            throw new Exception("Não há drones nem transportes registrados até o momento.");
        }
        
        StringBuilder resultado = new StringBuilder();
        for (Transporte transporte : listaTransportes) {
            if(transporte instanceof TransporteCargaInanimada){
                resultado.append(((TransporteCargaInanimada) transporte).imprime());
            }
            if(transporte instanceof TransporteCargaViva){
                resultado.append(((TransporteCargaViva) transporte).imprime());
            }
            if(transporte instanceof TransportePessoal){
                resultado.append(((TransportePessoal) transporte).imprime());
            }
        }

        for(Drone drone : listaDrones){
            if(drone instanceof DroneCargaInanimada){
                resultado.append(((DroneCargaInanimada) drone).imprime());
            }
            if(drone instanceof DroneCargaViva){
                resultado.append(((DroneCargaViva) drone).imprime());
            }
            if(drone instanceof DronePessoal){
                resultado.append(((DronePessoal) drone).imprime());
            }
        }   
        
        return resultado.toString();
    }
    


    //PASSO 5: 
    //PODE IMPRIMIR APENAS OS TO STRING DOS TRANSPORTES:
    //LANCA EXCEPTION SE NAO TIVER TRANSPORTES
    public String mostrarTransportes()throws Exception{
        
        if(listaTransportes.isEmpty()){
            throw new Exception("Não há transportes registrados até o momento.");
        }

        StringBuilder resultado = new StringBuilder();
        for (Transporte transporte : listaTransportes) {
            if(transporte instanceof TransporteCargaInanimada){
                resultado.append(((TransporteCargaInanimada) transporte).imprime());
            }
            if(transporte instanceof TransporteCargaViva){
                resultado.append(((TransporteCargaViva) transporte).imprime());
            }
            if(transporte instanceof TransportePessoal){
                resultado.append(((TransportePessoal) transporte).imprime());
            }
        }

        return resultado.toString();
    }



    //PASSO 6:
    //ESSE METODO ENVIA TRUE PARA CONFIRMAÇAO DA ALTERACAO DA SITUAÇAO DO TRANSPORTE E FALSE PARA INVALIDA
    //RECEBE COMO PARAMETRO O CODIGO DO TRANSPORTE PARA PROCURA E UM INDEX DE VALIDACAO
    //TRANSPORTES NA SITUACAO TERMINADO E CANCELADO SAO IMUTEVEIS
    //PENDENTE SO PODE MUDAR PARA CANCELADO OU, QUANDO RECEBE UM DRONE, PARA ALOCADO
    //ALOCADO PODE SER CANCELADO OU TERMINADO E DEPENDE DO INDEX (1 = CANCELADO) (2 = TERMINADO)
    //O INDEX SERA DEFINIDO A PARTIR DOS BOTÕES DO GUI 
    public boolean alteraSituacaoTransporte(int codigo, int index){

       for (Transporte transporte : listaTransportes) {
           if(codigo == transporte.getNumero()){
                if(transporte.getEstado() == Estado.PENDENTE && transporte.getDrone() != null && index == 0){
                    transporte.setEstadoParaAlocado();
                    return true;
                }
                if(transporte.getEstado() == Estado.PENDENTE && index == 1){
                    transporte.setEstadoParaCancelado();
                    return true;
                }
                if(transporte.getEstado() == Estado.ALOCADO && index == 1){
                    transporte.setEstadoParaCancelado();
                    return true;
                }
                if(transporte.getEstado() == Estado.ALOCADO && index == 2){
                    transporte.setEstadoParaTerminado();
                    return true;
                }
                throw new IllegalArgumentException("Não foi possível alterar a situação do transporte!");
           }
       }
        return false;
    }



    //ESSE METODO FAZ PARTE DO PASSO 3 E SERVE PARA ALINHAR O TRANSPORTE COM SEU RESPECTIVO TIPO DE DRONE
    //VALIDA SE O PESO DO TRANSPORTE E SUPORTADO PELO DRONE
    //CALCULA A QUANTIDADE DE PESSOAS PERMITIDAS, NO CASO DO DRONE PESSOAL
    //VALIDA SE A DISTANCIA EM KM É MENOR QUE A AUTONOMIA DO DRONE
    public boolean verificaCompatibildade(Transporte transporte){
        for(Drone drone : listaDrones){

            if(drone instanceof DroneCargaViva && transporte instanceof TransporteCargaViva){
                if(((DroneCargaViva) drone).getPesoMaximo() >= ((TransporteCargaViva) transporte).getPeso()
                        && drone.getAutonomia()>= transporte.calculaDistanciaKm()){
                        transporte.setDrone(drone);
                        drone.addHistorico(transporte);
                        transporte.setEstadoParaAlocado();
                        return true;
                    }
                }

            if(drone instanceof DroneCargaInanimada && transporte instanceof TransporteCargaInanimada){
                    if(((DroneCargaInanimada) drone).getPesoMaximo() >= ((TransporteCargaInanimada) transporte).getPeso()
                        && drone.getAutonomia()>= transporte.calculaDistanciaKm()){
                        transporte.setDrone(drone);
                        drone.addHistorico(transporte);
                        transporte.setEstadoParaAlocado();
                        return true;
                    }
            }

            if(drone instanceof DronePessoal && transporte instanceof TransportePessoal){
                if(drone.getAutonomia()>= transporte.calculaDistanciaKm() &&
                        ((DronePessoal) drone).getQtndPessoas() >= ((TransportePessoal) transporte).getQtndPessoas()){
                        transporte.setDrone(drone);
                        drone.addHistorico(transporte);
                        transporte.setEstadoParaAlocado();
                        System.out.println("teste");
                        return true;
                    }
            }

        }
        return false;
    }

    public String mostraTransportePorCodigo(int codigo) throws Exception{
        for (Transporte transporte : listaTransportes) {
            if(transporte.getNumero() == codigo){
                return transporte.imprime();
            }
        }
        throw new Exception("Transporte não encontrado!");
    }

    public ArrayList<Drone> getListadeDrones(){
        return this.listaDrones;
    }
    
    public ArrayList<Transporte> getListadeTransportes(){
        return this.listaTransportes;
    }
}
