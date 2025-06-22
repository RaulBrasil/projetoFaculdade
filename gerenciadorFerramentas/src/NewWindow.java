import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class NewWindow implements ActionListener{
    JFrame frame = new JFrame();
    JLabel tituloLabel = new JLabel("App de Gestão de Ferramentas");
    JButton returnButton = new JButton("Retornar");
    JButton cadFunButton = new JButton("Cadastrar Funcionários");
    JButton cadFerButton = new JButton("Cadastrar Ferramentas");
    JButton listaButton = new JButton("Lista de Disponibilidade");
    JButton removerDevolverButton = new JButton("Registrar Manipulação de Ferramenta");

    NewWindow(){
        cadFunButton.setBounds(100,145,200,30);
        cadFerButton.setBounds(100,100,200,30); 
        returnButton.setBounds(100,280,200,30);
        listaButton.setBounds(100,190,200,30);
        removerDevolverButton.setBounds(100,235,200,30);
        cadFunButton.addActionListener(this);
        cadFerButton.addActionListener(this);
        returnButton.addActionListener(this);
        listaButton.addActionListener(this);
        removerDevolverButton.addActionListener(this);
        frame.setLayout(new java.awt.FlowLayout());
        tituloLabel.setBounds(50,5,325,50);
        tituloLabel.setFont(new Font("Arial",Font.PLAIN,20));
        frame.add(cadFerButton);
        frame.add(cadFunButton);
        frame.add(returnButton);
        frame.add(tituloLabel);
        frame.add(listaButton);
        frame.add(removerDevolverButton);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==returnButton){
            frame.dispose();
            LaunchPage launchPage = new LaunchPage();
        }
        if(e.getSource()==cadFunButton){
            frame.dispose();
            PasswordSaver PasswordSaver = new PasswordSaver();
        }
        if(e.getSource()==cadFerButton){
            frame.dispose();
            itemSaver itemSaver = new itemSaver();
        }
        if(e.getSource()==listaButton){
            frame.dispose();
            listaPage listaPage = new listaPage();
        }
        if(e.getSource()==removerDevolverButton){
            try {
                frame.dispose();
                registrarPagina registrarPagina = new registrarPagina();
            } catch (IOException ex) {
                System.out.println("Algo aconteceu!");
            }
        }
    }
}
