package frontend.cards;

import backend.dados.App;
import backend.dados.transporte.TransporteCargaInanimada;
import frontend.Gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Card6 extends JPanel implements ActionListener {
    private final Gui gui;
    private final App app;
    private final CardLayout cardLayout;
    private final JPanel painel;
    private final JTextField campoNumero, campoNome, campoDescricao, campoPeso, campoLatitudeOrigem, campoLatitudeDestino, campoLongitudeOrigem, campoLongitudeDestino;
    private final JRadioButton radioSim, radioNao;
    private final JButton volta, envia;
    private ButtonGroup group;

    public Card6(Gui gui, App app, CardLayout cardLayout, JPanel painel) {
        this.gui = gui;
        this.app = app;
        this.cardLayout = cardLayout;
        this.painel = painel;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Rótulo
        JPanel painelRotulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel rotulo = new JLabel("Cadastra transporte de carga inanimada");
        rotulo.setFont(new Font("Arial", Font.BOLD, 18));
        painelRotulo.add(rotulo);
        this.add(painelRotulo);

        // Adicionando os campos
        this.add(gui.criarPainelComCampo("Número:", campoNumero = new JTextField()));
        this.add(gui.criarPainelComCampo("Nome do cliente:", campoNome = new JTextField()));
        this.add(gui.criarPainelComCampo("Descrição:", campoDescricao = new JTextField()));
        this.add(gui.criarPainelComCampo("Peso:", campoPeso = new JTextField()));
        this.add(gui.criarPainelComCampo("Latitude de origem:", campoLatitudeOrigem = new JTextField()));
        this.add(gui.criarPainelComCampo("Latitude de destino:", campoLatitudeDestino = new JTextField()));
        this.add(gui.criarPainelComCampo("Longitude de origem:", campoLongitudeOrigem = new JTextField()));
        this.add(gui.criarPainelComCampo("Longitude de destino:", campoLongitudeDestino = new JTextField()));
        this.add(criarPainelComRadioButton("Carga perigosa:", radioSim = new JRadioButton("Sim"), radioNao = new JRadioButton("Não")));
        
        // Botões
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        volta = new JButton("Voltar");
        volta.addActionListener(this);
        envia = new JButton("Enviar");
        envia.addActionListener(this);
        botoes.add(volta);
        botoes.add(envia);
        this.add(botoes);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == volta) {
            cardLayout.show(painel, "card1");   

        }else if (e.getSource() == envia) {
            try {
                if(campoNumero.getText().isEmpty() || campoNome.getText().isEmpty() || campoDescricao.getText().isEmpty() || campoPeso.getText().isEmpty() || campoLatitudeOrigem.getText().isEmpty() || campoLatitudeDestino.getText().isEmpty() || campoLongitudeOrigem.getText().isEmpty() || campoLongitudeDestino.getText().isEmpty()){
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

                boolean cargaPerigosa;
                if(radioSim.isSelected()){
                    cargaPerigosa = true;
                }else if(radioNao.isSelected()){
                    cargaPerigosa = false;
                }else{
                    throw new Exception("Selecione uma opção para a carga perigosa!");
                }    

                TransporteCargaInanimada transporte = new TransporteCargaInanimada(numero, nome, descricao, peso, latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino, cargaPerigosa);
                app.cadastraTransporte(transporte);

                campoNumero.setText("");
                campoNome.setText("");
                campoDescricao.setText("");
                campoPeso.setText("");
                campoLatitudeOrigem.setText("");
                campoLatitudeDestino.setText("");
                campoLongitudeOrigem.setText("");
                campoLongitudeDestino.setText("");
                group.clearSelection();

                JOptionPane.showMessageDialog(null, "Transporte cadastrado com sucesso", "Cadastro de transporte de carga inanimada", JOptionPane.INFORMATION_MESSAGE);

            }catch(NumberFormatException exception){
                JOptionPane.showMessageDialog(null, "Os campos de número, peso, latitudes e longitudes devem ser preenchidos com números válidos", "Cadastro de transporte de carga inanimada", JOptionPane.ERROR_MESSAGE);
            }catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Cadastro de transporte de carga inanimada", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }
    // Método auxiliar para criar botões select
    private JPanel criarPainelComRadioButton(String labelText, JRadioButton radioSim, JRadioButton radioNao) {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel(labelText);
        group = new ButtonGroup();
        group.add(radioSim);
        group.add(radioNao);
        painel.add(label);
        painel.add(radioSim);
        painel.add(radioNao);
        return painel;
    }
}
