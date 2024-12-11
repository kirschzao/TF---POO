package frontend.cards;

import backend.dados.App;
import backend.dados.drone.DroneCargaInanimada;
import frontend.Gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Card3 extends JPanel implements ActionListener {
    private final Gui gui;
    private final App app;
    private final CardLayout cardLayout;
    private final JPanel painel;
    private final JTextField campoCodigo, campoCustoFixo, campoAutonomia, campoPesoMaximo;
    private final JRadioButton radioSim, radioNao;
    private final JButton volta, envia;
    private ButtonGroup group;

    public Card3(Gui gui, App app, CardLayout cardLayout, JPanel painel) {
        this.gui = gui;
        this.app = app;
        this.cardLayout = cardLayout;
        this.painel = painel;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Rótulo principal
        JPanel painelRotulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel rotulo = new JLabel("Cadastra drone de carga inanimada");
        rotulo.setFont(new Font("Arial", Font.BOLD, 18));
        painelRotulo.add(rotulo);
        this.add(painelRotulo);

        // Adicionando campos usando as funções auxiliares
        this.add(gui.criarPainelComCampo("Código:", campoCodigo = new JTextField()));
        this.add(gui.criarPainelComCampo("Custo Fixo:", campoCustoFixo = new JTextField()));
        this.add(gui.criarPainelComCampo("Autonomia:", campoAutonomia = new JTextField()));
        this.add(gui.criarPainelComCampo("Peso máximo:", campoPesoMaximo = new JTextField()));
        this.add(criarPainelComRadioButton("Proteção:", radioSim = new JRadioButton("Sim"), radioNao = new JRadioButton("Não")));

        // Botões de voltar e enviar
        JPanel botoes = new JPanel(new FlowLayout());
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
        if (e.getSource() == volta) {
            cardLayout.show(painel, "card1");

        }else if (e.getSource() == envia) {
            try{
                if(campoCodigo.getText().isEmpty() || campoCustoFixo.getText().isEmpty() || campoAutonomia.getText().isEmpty() || campoPesoMaximo.getText().isEmpty()){
                    throw new Exception("Todos os campos precisam ser preenchidos!");
                }

                int codigo = Integer.parseInt(campoCodigo.getText());
                double custoFixo = Double.parseDouble(campoCustoFixo.getText());
                double autonomia = Double.parseDouble(campoAutonomia.getText());
                double pesoMaximo = Double.parseDouble(campoPesoMaximo.getText());

                boolean protecao;
                if(radioSim.isSelected()){
                    protecao = true;
                }else if(radioNao.isSelected()){
                    protecao = false;
                }else{
                    throw new Exception("Selecione uma opção para a proteção!");
                }

                DroneCargaInanimada drone = new DroneCargaInanimada(codigo, custoFixo, autonomia, pesoMaximo, protecao);
                app.cadastraDrone(drone);

                campoCodigo.setText("");
                campoCustoFixo.setText("");
                campoAutonomia.setText("");
                campoPesoMaximo.setText("");
                group.clearSelection();
                JOptionPane.showMessageDialog(null, "Drone cadastrado com sucesso", "Cadastro de drone de carga inanimada", JOptionPane.INFORMATION_MESSAGE);
            
            }catch(NumberFormatException exception){
                JOptionPane.showMessageDialog(null, "Os campos de código, custo fixo, autonomia e peso máximo devem ser preenchidos com números válidos", "Cadastro de drone de carga inanimada", JOptionPane.ERROR_MESSAGE);
            }catch(Exception exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Cadastro de drone de carga inanimada", JOptionPane.ERROR_MESSAGE);
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
