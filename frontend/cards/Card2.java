package frontend.cards;

import backend.dados.App;
import backend.dados.drone.DronePessoal;
import frontend.Gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Card2 extends JPanel implements ActionListener {
    private final Gui gui;
    private final App app;
    private final CardLayout cardLayout;
    private final JPanel painel;


    private final JTextField campoCodigo, campoCustoFixo, campoAutonomia, campoMaxPessoas;
    private final JButton volta, envia;

    public Card2(Gui gui, App app, CardLayout cardLayout, JPanel painel) {
        this.gui = gui;
        this.app = app;
        this.cardLayout = cardLayout;
        this.painel = painel;
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Rótulo principal
        JPanel painelRotulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel rotulo = new JLabel("Cadastra drone de carga de pessoas");
        rotulo.setFont(new Font("Arial", Font.BOLD, 18));
        painelRotulo.add(rotulo);
        this.add(painelRotulo);

        // Adicionando campos usando as funções auxiliares
        this.add(gui.criarPainelComCampo("Código:", campoCodigo = new JTextField()));
        this.add(gui.criarPainelComCampo("Custo Fixo:", campoCustoFixo = new JTextField()));
        this.add(gui.criarPainelComCampo("Autonomia:", campoAutonomia = new JTextField()));
        this.add(gui.criarPainelComCampo("Quantidade máxima de pessoas:", campoMaxPessoas = new JTextField()));

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
                if(campoCodigo.getText().isEmpty() || campoCustoFixo.getText().isEmpty() || campoAutonomia.getText().isEmpty() || campoMaxPessoas.getText().isEmpty()){
                    throw new Exception("Todos os campos precisam ser preenchidos!");
                }

                int codigo = Integer.parseInt(campoCodigo.getText());
                double custoFixo = Double.parseDouble(campoCustoFixo.getText());
                double autonomia = Double.parseDouble(campoAutonomia.getText());
                int maxPessoas = Integer.parseInt(campoMaxPessoas.getText());
                
                DronePessoal drone = new DronePessoal(codigo, custoFixo, autonomia, maxPessoas);
                app.cadastraDrone(drone);

                campoCodigo.setText("");
                campoCustoFixo.setText("");
                campoAutonomia.setText("");
                campoMaxPessoas.setText("");
                JOptionPane.showMessageDialog(null, "Drone cadastrado com sucesso", "Cadastro de drone pessoal", JOptionPane.INFORMATION_MESSAGE);
            
            }catch(NumberFormatException exception){
                JOptionPane.showMessageDialog(null, "Os campos de código, custo fixo, autonomia e máximo de pessoas devem ser preenchidos com números válidos", "Cadastro de drone pessoal", JOptionPane.ERROR_MESSAGE);
            }catch(Exception exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Cadastro de drone pessoal", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
