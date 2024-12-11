package frontend;

import backend.dados.App;
import frontend.cards.*;
import java.awt.*;
import javax.swing.*;
public class Gui extends JFrame {

    private final App app;
    private final Card1 card1;
    private final Card2 card2;
    private final Card3 card3;
    private final Card4 card4;
    private final Card5 card5;
    private final Card6 card6;
    private final Card7 card7;
    private final CardLayout cardLayout;
    private final JPanel painelPrincipal;

    public Gui() {
        super();
        setTitle("Trabalho Final - Bernardo Kirsch e Erick Carpes");
        setSize(800,800);
        app = new App();

        // Painel principal
        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);
        
        card1 = new Card1(this, app, cardLayout, painelPrincipal);
        card2 = new Card2(this, app, cardLayout, painelPrincipal);
        card3 = new Card3(this, app, cardLayout, painelPrincipal);
        card4 = new Card4(this, app, cardLayout, painelPrincipal);
        card5 = new Card5(this, app, cardLayout, painelPrincipal);
        card6 = new Card6(this, app, cardLayout, painelPrincipal);
        card7 = new Card7(this, app, cardLayout, painelPrincipal);

        painelPrincipal.add(card1, "card1");
        painelPrincipal.add(card2, "card2");
        painelPrincipal.add(card3, "card3");
        painelPrincipal.add(card4, "card4");
        painelPrincipal.add(card5, "card5");
        painelPrincipal.add(card6, "card6");
        painelPrincipal.add(card7, "card7");

        this.add(painelPrincipal);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // Método auxiliar para criar painel
    public JPanel criarPainelComCampo(String textoRotulo, JTextField campoTexto) {
        JPanel painelCampo = new JPanel();
        painelCampo.setLayout(new FlowLayout(FlowLayout.CENTER));
    
        JLabel rotulo = new JLabel(textoRotulo);
        rotulo.setPreferredSize(new Dimension(200, 20));
        
        campoTexto.setPreferredSize(new Dimension(300, 25));
    
        painelCampo.add(rotulo);
        painelCampo.add(campoTexto);
    
        return painelCampo;
    }

}