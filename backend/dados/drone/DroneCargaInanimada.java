package backend.dados.drone;

public class DroneCargaInanimada extends DroneCarga{

    private boolean protecao; 

    public DroneCargaInanimada(int codigo, double custoFixo, double autonomia, double pesoMaximo, boolean protecao){
        super(codigo,custoFixo,autonomia,pesoMaximo);
        this.protecao = protecao;
    }
    
    @Override
    public double calculaCustoKm(){
        if(protecao)
            return (super.getCustoFixo() + 10);
        else
            return (super.getCustoFixo() + 5);
    }

    // IMPRIME OS VALORES DOS DRONES
    @Override
    public String imprime(){
        StringBuilder resultado = new StringBuilder();

        resultado.append(super.imprime());
        resultado.append("Peso Máximo: ").append(super.getPesoMaximo()).append(" kg\n");
        resultado.append("Preço do Km: R$").append(calculaCustoKm()).append("\n");
        resultado.append("Proteção: ").append(this.protecao == true ? "Sim\n" : "Não\n");
        resultado.append("----------------------------------------------------------------------------------------------\n");
        return resultado.toString();
    }

    public boolean getProtecao(){
        return protecao;
    }
}