package frontend.cards;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import backend.dados.App;
import backend.dados.transporte.TransportePessoal;
import frontend.Gui;

public class Card5 extends JPanel implements ActionListener {
    private final Gui gui;
    private final App app;
    private final CardLayout cardLayout;
    private final JPanel painel;
    private final JTextField campoNumero, campoNome, campoDescricao, campoPeso, campoLatitudeOrigem, campoLatitudeDestino, campoLongitudeOrigem, campoLongitudeDestino, campoQuantidadePessoas;
    private final JButton volta, envia;

    public Card5(Gui gui, App app, CardLayout cardLayout, JPanel painel) {
        this.gui = gui;
        this.app = app;
        this.cardLayout = cardLayout;
        this.painel = painel;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Rótulo principal
        JPanel painelRotulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel rotulo = new JLabel("Cadastra transporte pessoal");
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
        this.add(gui.criarPainelComCampo("Quantidade de pessoas:", campoQuantidadePessoas = new JTextField()));
        
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
            try {
                if(campoNumero.getText().isEmpty() || campoNome.getText().isEmpty() || campoDescricao.getText().isEmpty() || campoPeso.getText().isEmpty() || campoLatitudeOrigem.getText().isEmpty() || campoLatitudeDestino.getText().isEmpty() || campoLongitudeOrigem.getText().isEmpty() || campoLongitudeDestino.getText().isEmpty() || campoQuantidadePessoas.getText().isEmpty()){
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
                int quantidadePessoas = Integer.parseInt(campoQuantidadePessoas.getText());

                TransportePessoal transporte = new TransportePessoal(numero, nome, descricao, peso, latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino, quantidadePessoas);
                app.cadastraTransporte(transporte);

                campoNumero.setText("");
                campoNome.setText("");
                campoDescricao.setText("");
                campoPeso.setText("");
                campoLatitudeOrigem.setText("");
                campoLatitudeDestino.setText("");
                campoLongitudeOrigem.setText("");
                campoLongitudeDestino.setText("");
                campoQuantidadePessoas.setText("");

                JOptionPane.showMessageDialog(null, "Transporte cadastrado com sucesso", "Cadastro de transporte pessoal", JOptionPane.INFORMATION_MESSAGE);

            }catch(NumberFormatException exception){
                JOptionPane.showMessageDialog(null, "Os campos de número, peso, latitudes, longitudes e quantidade máxima de pessoas devem ser preenchidos com números válidos", "Cadastro de transporte pessoal", JOptionPane.ERROR_MESSAGE);
            }catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Cadastro de transporte pessoal", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
