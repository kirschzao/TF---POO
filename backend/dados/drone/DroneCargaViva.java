package backend.dados.drone;

public class DroneCargaViva extends DroneCarga{

    private boolean climatizado; 

    public DroneCargaViva(int codigo, double custoFixo, double autonomia, double pesoMaximo, boolean climatizado){
        super(codigo,custoFixo,autonomia,pesoMaximo);
        this.climatizado = climatizado;
    }
    
    @Override
    public double calculaCustoKm(){
        if(climatizado)
            return (super.getCustoFixo() + 20);
        else
            return (super.getCustoFixo() + 10);
        
    }

    // IMPRIME OS VALORES DOS DRONES
    @Override
    public String imprime(){
        StringBuilder resultado = new StringBuilder();

        resultado.append(super.imprime());
        resultado.append("Peso Máximo: ").append(super.getPesoMaximo()).append(" kg\n");
        resultado.append("Preço do Km: R$").append(calculaCustoKm()).append("\n");
        resultado.append("Climatizado: ").append(this.climatizado == true ? "Sim\n" : "Não\n");
        resultado.append("----------------------------------------------------------------------------------------------\n");

        return resultado.toString();
    }

    public boolean getClimatizado(){
        return climatizado; 
    }

}