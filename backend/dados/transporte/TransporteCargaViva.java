package backend.dados.transporte;

public class TransporteCargaViva extends Transporte{
    
    private double temperaturaMin;
    private double temperaturaMax;

    public TransporteCargaViva(int numero, String nomeCliente, String descricao, double peso, 
                                double latitudeOrigem, double longitudeOrigem, 
                                double latitudeDestino, double longitudeDestino,
                                double temperaturaMin, double temperaturaMax) {
    
        super(numero,nomeCliente,descricao,peso,latitudeOrigem,longitudeOrigem,latitudeDestino,longitudeDestino);
        this.temperaturaMax = temperaturaMax;
        this.temperaturaMin = temperaturaMin;
    }

    @Override
    public double calculaCusto(){
        if(super.getDrone() == null){
            return 0.0;
        }

        double custo;
        double intervalo = Math.abs(temperaturaMax) - Math.abs(temperaturaMin);
        if (intervalo > 10 ){
           custo = (super.getDrone()).calculaCustoKm() * calculaDistanciaKm() + 1000;
        }else{
            custo = (super.getDrone()).calculaCustoKm() *calculaDistanciaKm();
        }
        return custo;
    }
    
     // IMPRIME OS VALORES DOS TRANSPORTES
    @Override
    public String imprime(){
        StringBuilder resultado = new StringBuilder();

        resultado.append(super.imprime());
        resultado.append("Temperatura Mínima: ").append(this.temperaturaMin).append(" °C, Temperatura Máxima: ").append(this.temperaturaMax).append(" °C\n");
        resultado.append("Custo do Transporte: R$ ").append(calculaCusto()).append("\n");
        resultado.append("----------------------------------------------------------------------------------------------\n");

        return resultado.toString();
    }

    public double getTempMin(){
        return temperaturaMin;
    }

    public double getTempMax(){
        return temperaturaMax;
    }
}
