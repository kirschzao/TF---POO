package backend.dados.drone;

public class DronePessoal extends Drone {
    
    private final int qtdMaxPessoas;

    public DronePessoal(int codigo, double custoFixo, double autonomia, int qtdMaxPessoas){
        super(codigo,custoFixo,autonomia);
        this.qtdMaxPessoas = qtdMaxPessoas;
    }

    @Override
    public double calculaCustoKm(){
        return (super.getCustoFixo() + (2.0*qtdMaxPessoas));
    }

    public double getQtndPessoas(){
        return qtdMaxPessoas;
    }

    // IMPRIME OS VALORES DOS DRONES
    @Override
    public String imprime(){
        StringBuilder resultado = new StringBuilder();

        resultado.append(super.imprime());
        resultado.append("Preço do Km: R$").append(calculaCustoKm()).append("\n");
        resultado.append("Quantidade Máxima de Pessoas: ").append(getQtndPessoas()).append("\n");
        resultado.append("----------------------------------------------------------------------------------------------\n");

        return resultado.toString();
    }
}