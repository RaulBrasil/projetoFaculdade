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
    JButton listaButton = new JButton("Lista de Ocorrências");
    JButton disponButton = new JButton("Checar Disponibilidade");
    JButton removerDevolverButton = new JButton("Registrar Mudança");

    NewWindow(){
        cadFerButton.setBounds(100,60,200,30); 
        cadFunButton.setBounds(100,105,200,30);
        listaButton.setBounds(100,150,200,30);
        removerDevolverButton.setBounds(100,195,200,30);
        disponButton.setBounds(100,240,200,30);
        returnButton.setBounds(100,285,200,30);
        cadFunButton.addActionListener(this);
        cadFerButton.addActionListener(this);
        returnButton.addActionListener(this);
        listaButton.addActionListener(this);
        disponButton.addActionListener(this);
        removerDevolverButton.addActionListener(this);
        frame.setLayout(new java.awt.FlowLayout());
        tituloLabel.setBounds(50,5,325,50);
        tituloLabel.setFont(new Font("Arial",Font.PLAIN,20));
        frame.add(cadFerButton);
        frame.add(cadFunButton);
        frame.add(returnButton);
        frame.add(tituloLabel);
        frame.add(listaButton);
        frame.add(disponButton);
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
        if(e.getSource()==removerDevolverButton){
            frame.dispose();
            registrarPagina registrarPagina = new registrarPagina();
        }
        if(e.getSource()==listaButton){
            try {
                frame.dispose();
                listaPage listaPage = new listaPage();
            } catch (IOException ex) {
                System.out.println("Algo aconteceu!");
            }
        }
        if(e.getSource()==disponButton){
            try {
                frame.dispose();
                disponPage disponPage = new disponPage();
            } catch (IOException ex) {
                System.out.println("Algo aconteceu!");
            }
        }
    }
}
