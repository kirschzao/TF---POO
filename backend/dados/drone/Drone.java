package backend.dados.drone;

import backend.dados.transporte.*;
import java.util.ArrayList;

public abstract class Drone {
    
    private int codigo;
    private final double custoFixo;
    private double autonomia;
    private ArrayList<Transporte> historico;

    public Drone(int codigo, double custoFixo, double autonomia){
        this.codigo = codigo;
        this.custoFixo = custoFixo;
        this.autonomia = autonomia;
        this.historico = new ArrayList<>();
    }

    public abstract double calculaCustoKm();

    public double getCustoFixo(){
        return this.custoFixo;
    }

    public int getCodigo(){
        return this.codigo;
    }
    
    public double getAutonomia(){
        return autonomia;
    }


    // IMPRIME OS VALORES DOS DRONES
    public String imprime(){
        StringBuilder resultado = new StringBuilder();

        resultado.append("==================== DRONE =====================\n");
        resultado.append("Código: ").append(this.codigo).append("\n");
        resultado.append("Custo Fixo: R$ ").append(this.custoFixo).append("\n");
        resultado.append("Autonomia (litros): ").append(this.autonomia).append(" km\n");

        return resultado.toString();
    }

    public void addHistorico(Transporte t){
        historico.add(t);
    }
}
