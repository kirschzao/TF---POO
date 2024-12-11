package backend.dados.drone;

public abstract class DroneCarga extends Drone{

    private final double pesoMaximo; 

    public DroneCarga(int codigo, double custoFixo, double autonomia, double pesoMaximo){
        super(codigo,custoFixo,autonomia);
        this.pesoMaximo = pesoMaximo;
    }
    
    @Override
    public abstract double calculaCustoKm();

    public double getPesoMaximo(){
        return pesoMaximo;
    }
}