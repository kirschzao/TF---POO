package backend.dados.transporte;
import backend.dados.drone.*;

public abstract class Transporte {
    private int numero;
    private String nomeCliente;
    private String descricao;
    private double peso;
    private double latitudeOrigem;
    private double longitudeOrigem;
    private double latitudeDestino;
    private double longitudeDestino;
    private Estado situacao;
    private Drone droneAtual;

    public Transporte(int numero, String nomeCliente, String descricao, double peso,
                      double latitudeOrigem, double longitudeOrigem,
                      double latitudeDestino, double longitudeDestino) {
        this.numero = numero;
        this.nomeCliente = nomeCliente;
        this.descricao = descricao;
        this.peso = peso;
        this.latitudeOrigem = latitudeOrigem;
        this.longitudeOrigem = longitudeOrigem;
        this.latitudeDestino = latitudeDestino;
        this.longitudeDestino = longitudeDestino;
        this.situacao = Estado.PENDENTE;
    }

    public Transporte(int numero){
        this.numero = numero;
        this.nomeCliente = null;
        this.descricao = null;
        this.peso = 00;
        this.latitudeOrigem = 00;
        this.longitudeOrigem = 00;
        this.latitudeDestino = 00;
        this.longitudeDestino = 00;
        this.situacao = Estado.PENDENTE;
    }
    
    public abstract double calculaCusto();
    

    public Estado getEstado(){
        return this.situacao;
    }


    public void setEstadoParaCancelado(){
        if(!getEstado().equals(Estado.TERMINADO)){
           this.situacao = Estado.CANCELADO;
        }
        
    }

    public void setEstadoParaAlocado(){
           this.situacao = Estado.ALOCADO;
    }
    
    public void setEstadoParaTerminado(){
        if(getEstado().equals(Estado.ALOCADO)){
            this.situacao = Estado.TERMINADO;
        }
    }

    public double calculaDistanciaKm(){
        
        double raioTerra = 6371.0;
        double latitudeOrigem = this.latitudeOrigem;
        double longitudeOrigem = this.longitudeOrigem;
        double latitudeDestino = this.latitudeDestino;
        double longitudeDestino = this.longitudeDestino;
        
        double dLat = Math.toRadians(latitudeDestino - latitudeOrigem);
        double dLon = Math.toRadians(longitudeDestino - longitudeOrigem);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(latitudeOrigem)) * Math.cos(Math.toRadians(latitudeDestino)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return raioTerra  * c;
    }

    public void setDrone(Drone drone){
        this.droneAtual = drone;
    }

    public Drone getDrone(){
        return this.droneAtual;
    }

    public int getNumero(){
        return this.numero;
    }

    public double getPeso(){
        return peso;
    }

    // IMPRIME OS VALORES DOS TRANSPORTES
    public String imprime(){
        StringBuilder resultado = new StringBuilder();
        String distancia = String.format(" %.2f",calculaDistanciaKm());
        resultado.append("=============== TRANSPORTE ===============\n");
        resultado.append("Número do transporte: ").append(this.numero).append("\n");
        resultado.append("Nome do Cliente: ").append(this.nomeCliente).append("\n");
        resultado.append("Descrição: ").append(this.descricao).append("\n");
        resultado.append("Situação Atual: ").append(getEstado()).append("\n");
        resultado.append("Peso (kg): ").append(this.peso).append(" kg\n");
        resultado.append("Localização de Origem: (").append(this.latitudeOrigem).append(", ").append(this.longitudeOrigem).append(")\n");
        resultado.append("Localização de Destino: (").append(this.latitudeDestino).append(", ").append(this.longitudeDestino).append(")\n");
        resultado.append("Distância (km): ").append(distancia).append(" KM\n");
        resultado.append("Drone Atual: ").append(this.droneAtual != null ? this.droneAtual.getCodigo()+"\n" : "Nenhum drone atribuído\n");
    
        return resultado.toString();
    }

    public String getNome(){
        return nomeCliente;
    }

    public String getDesc(){
        return descricao;
    }

    public double getLatOrigem(){
        return latitudeOrigem;
    }

    public double getLonOrigem(){
        return longitudeOrigem;
    }

    public double getLatDestino(){
        return latitudeDestino;
    }

    public double getLonDestino(){
        return longitudeDestino;
    }
}
