package frontend.cards;

import backend.aplicacao.EntradaDrone;
import backend.aplicacao.EntradaTransporte;
import backend.aplicacao.SaidaDrone;
import backend.aplicacao.SaidaTransporte;
import backend.dados.App;
import frontend.Gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Card1 extends JPanel implements ActionListener {
    private final Gui gui;
    private final App app;
    private final CardLayout cardLayout;
    private final JPanel painel;
    private final JButton botaoCadastraDroneP, botaoCadastraDroneCargaI, botaoCadastraDroneCargaV, botaoCadastraTransporteP, botaoCadastraTransporteCargaI, botaoCadastraTransporteCargaV, botaoProcessaTransportesPendentes, botaoMostraRelatorioGeral, botaoMostraTransportes, botaoAlteraSituacaoTransporte, botaoRealizaLeituraDrone, botaoRealizaLeituraTransporte, botaoSalvaDados, botaoCarregaDados, botaoFinaliza;

    public Card1(Gui gui, App app, CardLayout cardLayout, JPanel painel) {
        this.gui = gui;
        this.app = app;
        this.cardLayout = cardLayout;
        this.painel = painel;


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));

        // Titulo
        JPanel painelRotulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel rotulo = new JLabel("ACMEAirDrones ");
        rotulo.setFont(new Font("Arial", Font.BOLD, 26));
        painelRotulo.add(rotulo);


        // Texto e funções
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        JLabel funcao = new JLabel("Funções de controle: ");
        funcao.setFont(new Font("Arial", Font.BOLD, 16));
        funcao.setAlignmentX(Component.CENTER_ALIGNMENT);
        funcao.setBorder(new EmptyBorder(0, 0, 20, 0));
        painelBotoes.add(funcao);

        botaoCadastraDroneP = new JButton("Cadastrar drone de pessoas");
        botaoCadastraDroneCargaI = new JButton("Cadastrar drone de carga inanimada");
        botaoCadastraDroneCargaV = new JButton("Cadastrar drone de carga viva");
        botaoCadastraTransporteP = new JButton("Cadastrar transporte pessoal");
        botaoCadastraTransporteCargaI = new JButton("Cadastrar transporte de carga inanimada");
        botaoCadastraTransporteCargaV = new JButton("Cadastrar transporte de carga viva");
        botaoProcessaTransportesPendentes = new JButton("Processar transportes pendentes");
        botaoMostraRelatorioGeral = new JButton("Mostrar relatório geral");
        botaoMostraTransportes = new JButton("Mostrar todos os transportes");
        botaoAlteraSituacaoTransporte = new JButton("Alterar a situação de um transporte");
        botaoRealizaLeituraDrone = new JButton("Realizar simulação de dados de drone");
        botaoRealizaLeituraTransporte = new JButton("Realizar simulação de dados de transporte");
        botaoSalvaDados = new JButton("Salvar dados");
        botaoCarregaDados = new JButton("Carregar dados");
        botaoFinaliza = new JButton("Finalizar sistema");
        
        //BOTOES DE CADASTRO DE DRONES:
        Color verde = new Color(0, 100, 0); // Cor com valores personalizados
        botaoCadastraDroneP.setBackground(verde);  
        botaoCadastraDroneP.setForeground(Color.WHITE);
        botaoCadastraDroneCargaI.setBackground(verde);  
        botaoCadastraDroneCargaI.setForeground(Color.WHITE);
        botaoCadastraDroneCargaV.setBackground(verde);  
        botaoCadastraDroneCargaV.setForeground(Color.WHITE); 
        //BOTOES DE CADASTRO DE TRANSPORTES:  
        Color azul = new Color(0, 127, 255); 
        botaoCadastraTransporteP.setBackground(azul);  
        botaoCadastraTransporteP.setForeground(Color.WHITE);
        botaoCadastraTransporteCargaI.setBackground(azul);  
        botaoCadastraTransporteCargaI.setForeground(Color.WHITE);
        botaoCadastraTransporteCargaV.setBackground(azul);  
        botaoCadastraTransporteCargaV.setForeground(Color.WHITE); 
        //BOTOES DO SISTEMA:
        Color amarelo = new Color(238, 173, 45); 
        botaoProcessaTransportesPendentes.setBackground(amarelo);  
        botaoProcessaTransportesPendentes.setForeground(Color.WHITE);
        botaoMostraRelatorioGeral.setBackground(amarelo);  
        botaoMostraRelatorioGeral.setForeground(Color.WHITE);
        botaoMostraTransportes.setBackground(amarelo);  
        botaoMostraTransportes.setForeground(Color.WHITE);
        botaoAlteraSituacaoTransporte.setBackground(amarelo);  
        botaoAlteraSituacaoTransporte.setForeground(Color.WHITE); 
        //BOTOES DE DADOS:
        Color lilas = new Color(182, 149, 192); 
        botaoRealizaLeituraDrone.setBackground(lilas);  
        botaoRealizaLeituraDrone.setForeground(Color.WHITE);
        botaoRealizaLeituraTransporte.setBackground(lilas);  
        botaoRealizaLeituraTransporte.setForeground(Color.WHITE);
        botaoSalvaDados.setBackground(lilas);  
        botaoSalvaDados.setForeground(Color.WHITE);
        botaoCarregaDados.setBackground(lilas);  
        botaoCarregaDados.setForeground(Color.WHITE);
        //BOTÃO DE SAIDA:
        botaoFinaliza.setBackground(Color.RED);  
        

        
        // função para facilitar a vida
        Dimension buttonSize = new Dimension(300, 30);
        for (JButton botao : new JButton[]{botaoCadastraDroneP, botaoCadastraDroneCargaI, botaoCadastraDroneCargaV, botaoCadastraTransporteP, botaoCadastraTransporteCargaI, botaoCadastraTransporteCargaV, botaoProcessaTransportesPendentes, botaoMostraRelatorioGeral, botaoMostraTransportes, botaoAlteraSituacaoTransporte, botaoRealizaLeituraDrone, botaoRealizaLeituraTransporte, botaoSalvaDados, botaoCarregaDados, botaoFinaliza}) {
            botao.setPreferredSize(buttonSize);
            botao.setMaximumSize(buttonSize);
            botao.setAlignmentX(Component.CENTER_ALIGNMENT);
            botao.addActionListener(this);
            painelBotoes.add(botao);
            painelBotoes.add(Box.createVerticalStrut(5));
        }

        // para centralizar os botoes
        JPanel painelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelCentral.add(painelBotoes);

        // adiciona no card1
        painelPrincipal.add(painelRotulo);
        painelPrincipal.add(painelCentral);

        this.setLayout(new BorderLayout());
        this.add(painelPrincipal, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == botaoCadastraDroneP){
            cardLayout.show(painel, "card2");

        } else if(e.getSource() == botaoCadastraDroneCargaI){
            cardLayout.show(painel, "card3");

        } else if(e.getSource() == botaoCadastraDroneCargaV){
            cardLayout.show(painel, "card4");

        } else if(e.getSource() == botaoCadastraTransporteP){
            cardLayout.show(painel, "card5");

        } else if(e.getSource() == botaoCadastraTransporteCargaI){
            cardLayout.show(painel, "card6");

        } else if(e.getSource() == botaoCadastraTransporteCargaV){
            cardLayout.show(painel, "card7");

        } else if(e.getSource() == botaoProcessaTransportesPendentes){
            try {
                String resultado = app.processaTransportePendentesUnico();
                JTextArea textArea = new JTextArea(resultado);
                textArea.setEditable(false);
                textArea.setWrapStyleWord(true);
                textArea.setLineWrap(true);
                                
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(400, 300));

                JOptionPane.showMessageDialog(null, scrollPane, "Resultado do processamento", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Resultado do processamento", JOptionPane.ERROR_MESSAGE);
            }
        } else if(e.getSource() == botaoMostraRelatorioGeral){
            try {
                String resultado = app.mostrarRelatorioGeral();
                JTextArea textArea = new JTextArea(resultado);
                textArea.setEditable(false);
                textArea.setWrapStyleWord(true);
                textArea.setLineWrap(true);
                                
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(400, 300));
                                
                JOptionPane.showMessageDialog(null, scrollPane, "Relatório Geral", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Relatório Geral", JOptionPane.ERROR_MESSAGE);
            }

        } else if(e.getSource() == botaoMostraTransportes){
            try {
                String resultado = app.mostrarTransportes();
                JTextArea textArea = new JTextArea(resultado);
                textArea.setEditable(false);
                textArea.setWrapStyleWord(true);
                textArea.setLineWrap(true);
                                
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(400, 300));
                                
                JOptionPane.showMessageDialog(null, scrollPane, "Mostra todos os transportes", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Mostra todos os transportes", JOptionPane.ERROR_MESSAGE);
            }

        } else if(e.getSource() == botaoAlteraSituacaoTransporte){
            int res, codigo;
            String codigoStr;

            try {
                do{
                codigoStr = JOptionPane.showInputDialog(null, "Digite o código do transporte:", "Alterar a situação de um transporte", JOptionPane.PLAIN_MESSAGE);
                if(codigoStr == null){
                    return;
                }

                codigo = Integer.parseInt(codigoStr);
                String resultado = app.mostraTransportePorCodigo(codigo);
                res = JOptionPane.showConfirmDialog(null, "Esse é o transporte correto?\n\n"+resultado, "Informações do transporte", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                }while(res == JOptionPane.NO_OPTION);

                String[] opcoesSituacao = {"ALOCADO", "CANCELADO", "TERMINADO"};
                int opcaoSelecionada = JOptionPane.showOptionDialog(null, "Selecione a nova situação do transporte:", "Alterar Situação de um transporte", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesSituacao,opcoesSituacao[0]);
                
                app.alteraSituacaoTransporte(codigo, opcaoSelecionada);

                JOptionPane.showMessageDialog(null, "Situação alterada com sucesso", "Alterar a situação de um transporte", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "O campo de código precisa ser preenchido com um número válido", "Alterar a situação de um transporte", JOptionPane.ERROR_MESSAGE);
            }catch(Exception exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Alterar a situação de um transporte", JOptionPane.ERROR_MESSAGE);
            }

        } else if(e.getSource() == botaoRealizaLeituraDrone){
            int fail = 0;
            String arquivo;
            try {
                arquivo = JOptionPane.showInputDialog(null, "Digite o nome do arquivo, sem extensão:", "Leitura de dados de simulação, de drones", JOptionPane.PLAIN_MESSAGE);
                if(arquivo == null){
                    return;
                }
                fail = new EntradaDrone(app, arquivo).cadastraDrone();
                JOptionPane.showMessageDialog(null, "Arquivo encontrado com sucesso!", "Leitura de dados de simulação, de drones", JOptionPane.INFORMATION_MESSAGE);   
                
                if(fail == 0){
                    JOptionPane.showMessageDialog(null, "Arquivo lido sucesso e sem erros!", "Leitura de dados de simulação, de drones", JOptionPane.INFORMATION_MESSAGE);
                }else if(fail == 1){
                    JOptionPane.showMessageDialog(null, "Arquivo lido com problema! \n" + "Erro ao ler um drone!\n", "Leitura de dados de simulação, de drones", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Arquivo lido com problemas! \n" + "Erro ao ler "+ fail +" drones!\n", "Leitura de dados de simulação, de drones", JOptionPane.INFORMATION_MESSAGE);}

            } catch (Exception NullpointerException) {
                JOptionPane.showMessageDialog(null, "Erro ao ler arquivo\n" , "Leitura de dados de simulação, de drones", JOptionPane.ERROR_MESSAGE);
            } 

        } else if(e.getSource() == botaoRealizaLeituraTransporte){
            int fail = 0;
            String arquivo;
            try {
                arquivo = JOptionPane.showInputDialog(null, "Digite o nome do arquivo, sem extensão:", "Leitura de dados de simulação, de transportes", JOptionPane.PLAIN_MESSAGE);
                if(arquivo == null){
                    return;
                }

                fail = new EntradaTransporte(app, arquivo).cadastraTransporte();
                JOptionPane.showMessageDialog(null, "Arquivo encontrado com sucesso!", "Leitura de dados de simulação, de transporte", JOptionPane.INFORMATION_MESSAGE);   
    
                if(fail == 0){
                    JOptionPane.showMessageDialog(null, "Arquivo lido sucesso e sem erros!", "Leitura de dados de simulação, de transporte", JOptionPane.INFORMATION_MESSAGE);
                }else if(fail == 1){
                    JOptionPane.showMessageDialog(null, "Arquivo lido com problema! \n" + "Erro ao ler um transporte!\n", "Leitura de dados de simulação, de transporte", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Arquivo lido com problemas! \n" + "Erro ao ler "+ fail +" transportes!\n", "Leitura de dados de simulação, de transportes", JOptionPane.INFORMATION_MESSAGE);}
    
            } catch (Exception NullPointerException) {
                JOptionPane.showMessageDialog(null, "Erro ao ler arquivo", "Leitura de dados de simulação, de transportes", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(e.getSource() == botaoSalvaDados){
            String arquivo;
            try {
                arquivo = JOptionPane.showInputDialog(null, "Digite o nome do arquivo que quer salvar, sem extensão:\n" + "Drones e transportes são salvos em arquivos diferentes*", "Salvamento de dados", JOptionPane.PLAIN_MESSAGE);
                if(arquivo == null){
                    return;
                }
                int opcaoSelecionada = 0;

                //SALVAR COMO CSV
                if(opcaoSelecionada == 0){
                    new SaidaDrone(app, arquivo).salvaDrone();
                    JOptionPane.showMessageDialog(null, "Dados de drones salvos com sucesso no arquivo: "+ arquivo +"-DRONES.CSV", "Salvamento de dados", JOptionPane.INFORMATION_MESSAGE);
                    new SaidaTransporte(app, arquivo).salvaTransporte();
                    JOptionPane.showMessageDialog(null, "Dados de transportes salvos com sucesso no arquivo: "+ arquivo +"-TRANSPORTES.CSV", "Salvamento de dados", JOptionPane.INFORMATION_MESSAGE);
                }


            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Salvamento de dados", JOptionPane.ERROR_MESSAGE);
            }

        } else if(e.getSource() == botaoCarregaDados){
            String arquivo;
            try {
                arquivo = JOptionPane.showInputDialog(null, "Digite o nome do arquivo que quer carregar, sem extensão:", "Carregamento de dados", JOptionPane.PLAIN_MESSAGE);
                if(arquivo == null){
                    return;
                }
                int opcaoSelecionada2 = 0;

                //SALVAR COMO CSV
                if(opcaoSelecionada2 == 0){
                    new EntradaDrone(app, arquivo).cadastraDrone();
                    JOptionPane.showMessageDialog(null, "Dados de drones carregados com sucesso no arquivo: "+ arquivo +"-DRONES.CSV", "Carregamento de dados", JOptionPane.INFORMATION_MESSAGE);
                    new EntradaTransporte(app, arquivo).cadastraTransporte();
                    JOptionPane.showMessageDialog(null, "Dados de transportes carregados com sucesso no arquivo: "+ arquivo +"-TRANSPORTES.CSV", "Carregamento de dados", JOptionPane.INFORMATION_MESSAGE);
                }
                
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Carregamento de dados", JOptionPane.ERROR_MESSAGE);
            }

        } else if(e.getSource() == botaoFinaliza){
            System.exit(0);
       }
    }


}

