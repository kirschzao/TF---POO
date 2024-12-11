package frontend.cards;

import backend.dados.App;
import backend.dados.transporte.TransporteCargaViva;
import frontend.Gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Card7 extends JPanel implements ActionListener {
    private final Gui gui;
    private final App app;
    private final CardLayout cardLayout;
    private final JPanel painel;
    private final JTextField campoNumero, campoNome, campoDescricao, campoPeso, campoLatitudeOrigem, campoLatitudeDestino, campoLongitudeOrigem, campoLongitudeDestino, campoTemperaturaMinima, campoTemperaturaMaxima;
    private final JButton volta, envia;

    public Card7(Gui gui, App app, CardLayout cardLayout, JPanel painel) {
        this.gui = gui;
        this.app = app;
        this.cardLayout = cardLayout;
        this.painel = painel;
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Rótulo
        JPanel painelRotulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel rotulo = new JLabel("Cadastra transporte de carga viva");
        rotulo.setFont(new Font("Arial", Font.BOLD, 18));
        painelRotulo.add(rotulo);
        this.add(painelRotulo);

        // Painéis com campos
        this.add(gui.criarPainelComCampo("Número: ", campoNumero = new JTextField()));
        this.add(gui.criarPainelComCampo("Nome do cliente: ", campoNome = new JTextField()));
        this.add(gui.criarPainelComCampo("Descrição: ", campoDescricao = new JTextField()));
        this.add(gui.criarPainelComCampo("Peso: ", campoPeso = new JTextField()));
        this.add(gui.criarPainelComCampo("Latitude de origem: ", campoLatitudeOrigem = new JTextField()));
        this.add(gui.criarPainelComCampo("Latitude de destino: ", campoLatitudeDestino = new JTextField()));
        this.add(gui.criarPainelComCampo("Longitude de origem: ", campoLongitudeOrigem = new JTextField()));
        this.add(gui.criarPainelComCampo("Longitude de destino: ", campoLongitudeDestino = new JTextField()));
        this.add(gui.criarPainelComCampo("Temperatura mínima: ", campoTemperaturaMinima = new JTextField()));
        this.add(gui.criarPainelComCampo("Temperatura máxima: ", campoTemperaturaMaxima = new JTextField()));
        
        // Botões
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        volta = new JButton("Back");
        volta.addActionListener(this);
        envia = new JButton("Enviar");
        envia.addActionListener(this);
        botoes.add(volta);
        botoes.add(envia);
        this.add(botoes);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volta){
            cardLayout.show(painel, "card1");
        }else if(e.getSource() == envia){
            try {
                if(campoNumero.getText().isEmpty() || campoNome.getText().isEmpty() || campoDescricao.getText().isEmpty() || campoPeso.getText().isEmpty() || campoLatitudeOrigem.getText().isEmpty() || campoLatitudeDestino.getText().isEmpty() || campoLongitudeOrigem.getText().isEmpty() || campoLongitudeDestino.getText().isEmpty() || campoTemperaturaMinima.getText().isEmpty() || campoTemperaturaMaxima.getText().isEmpty()){
                    throw new Exception("Todos os campos precisam ser preenchidos!");
                }
    
                int numero = Integer.parseInt(campoNumero.getText());                
                String nome = campoNome.getText();
                String descricao = campoDescricao.getText();
                double peso = Double.parseDouble(campoPeso.getText());
                double latitudeOrigem = Double.parseDouble(campoLatitudeOrigem.getText());
                double latitudeDestino = Double.parseDouble(campoLatitudeDestino.getText());                
                double longitudeOrigem = Double.parseDouble(campoLongitudeOrigem.getText());
                double longitudeDestino = Double.parseDouble(campoLongitudeDestino.getText());
                double temperaturaMinima = Double.parseDouble(campoTemperaturaMinima.getText());
                double temperaturaMaxima = Double.parseDouble(campoTemperaturaMaxima.getText());
    
                TransporteCargaViva transporte = new TransporteCargaViva(numero, nome, descricao, peso, latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino, temperaturaMinima, temperaturaMaxima);                
                app.cadastraTransporte(transporte);
    
                campoNumero.setText("");
                campoNome.setText("");
                campoDescricao.setText("");
                campoPeso.setText("");
                campoLatitudeOrigem.setText("");
                campoLatitudeDestino.setText("");
                campoLongitudeOrigem.setText("");
                campoLongitudeDestino.setText("");
                campoTemperaturaMinima.setText("");
                campoTemperaturaMaxima.setText("");
    
                JOptionPane.showMessageDialog(null, "Transporte cadastrado com sucesso", "Cadastro de transporte de carga viva", JOptionPane.INFORMATION_MESSAGE);
    
            }catch(NumberFormatException exception){
                JOptionPane.showMessageDialog(null, "Os campos de número, peso, latitudes, longitudes e temperaturas devem ser preenchidos com números válidos", "Cadastro de transporte de carga viva", JOptionPane.ERROR_MESSAGE);
            }catch (Exception exception){ 
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Cadastro de transporte de carga viva", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
