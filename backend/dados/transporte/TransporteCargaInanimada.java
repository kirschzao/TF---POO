package backend.dados.transporte;

public class TransporteCargaInanimada extends Transporte{

    private final boolean cargaPerigosa;

    public TransporteCargaInanimada  (int numero, String nomeCliente, String descricao, double peso, 
                                double latitudeOrigem, double longitudeOrigem, 
                                double latitudeDestino, double longitudeDestino,
                                boolean cargaPerigosa) {

    super(numero,nomeCliente,descricao,peso,latitudeOrigem,longitudeOrigem,latitudeDestino,longitudeDestino);
    this.cargaPerigosa = cargaPerigosa;
    }

    @Override
    public double calculaCusto(){
        if(super.getDrone() == null){
            return 0.0;
        }

        double custo;
        if(cargaPerigosa){
            custo = (super.getDrone()).calculaCustoKm() *calculaDistanciaKm() + 500.0;
        }else{
            custo = (super.getDrone()).calculaCustoKm() * calculaDistanciaKm();
        }
        return custo;
    }

     // IMPRIME OS VALORES DOS TRANSPORTES
    @Override
    public String imprime(){
        StringBuilder resultado = new StringBuilder();

        resultado.append(super.imprime());
        resultado.append("Carga Perigosa: ").append(this.cargaPerigosa == true ? "Sim\n" : "Não\n");
        resultado.append("Custo do Transporte: R$ ").append(calculaCusto()).append("\n");
        resultado.append("----------------------------------------------------------------------------------------------\n");

        return resultado.toString();
    }

    public boolean getCargaPerigosa(){
        return cargaPerigosa;
    }
}