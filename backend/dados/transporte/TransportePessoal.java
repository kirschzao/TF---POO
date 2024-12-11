package backend.dados.transporte;

public class TransportePessoal extends Transporte{

    private final int qtdPessoas;

    public TransportePessoal (int numero, String nomeCliente, String descricao, double peso, 
                                double latitudeOrigem, double longitudeOrigem, 
                                double latitudeDestino, double longitudeDestino,
                                int qtdPessoas) {
    
        super(numero,nomeCliente,descricao,peso,latitudeOrigem,longitudeOrigem,latitudeDestino,longitudeDestino);
        this.qtdPessoas = qtdPessoas;
    }

    @Override
    public double calculaCusto(){
        if(super.getDrone() == null){
            return 0.0;
        }

        double custo = (super.getDrone()).calculaCustoKm() *calculaDistanciaKm() + 10*qtdPessoas;
        return custo;
    }

    public int getQtndPessoas(){
        return qtdPessoas;
    }

     // IMPRIME OS VALORES DOS TRANSPORTES
    @Override
    public String imprime(){
        StringBuilder resultado = new StringBuilder();

        resultado.append(super.imprime());
        resultado.append("Quantidade de Pessoas: ").append(qtdPessoas).append("\n");
        resultado.append("Custo do Transporte: R$ ").append(calculaCusto()).append("\n");
        resultado.append("----------------------------------------------------------------------------------------------\n");

        return resultado.toString();
    }

}

