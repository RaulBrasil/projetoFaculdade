//Essa página simplesmente serve para mostrar os créditos, e não tem quase coisa nova sobre ela que já não foi dito em outras classes.
import java.awt.event.*;
import javax.swing.*;

public class creditosPage extends JFrame implements ActionListener {
    JFrame frame = new JFrame();
    JButton retornarButton = new JButton("Retornar");
    JTextArea textoArea = new JTextArea();
    JLabel titulo = new JLabel("Créditos");
    public creditosPage(){

        textoArea.setBounds(20,10,350,200);
        textoArea.setLineWrap(true); //Para o texto não continuar pra fora da caixa de texto.
        textoArea.setWrapStyleWord(true); //Define o tipo de textwrap
        textoArea.setEditable(false); //Deixa não-editavel
        textoArea.setText("Trabalho para a matéria de Estrutura de Dados\nProfessora: Sheila\n\nBackend e Frontend feitos por Raul Rodrigues Brasil Palheta Gomes\nMatrícula: 2240202246\nE-Mail: raulpalheta25@gmail.com\n");

        retornarButton.setBounds(80,300,200,40);
        retornarButton.addActionListener(this);

        add(titulo);
        add(retornarButton);
        add(textoArea);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==retornarButton){
            frame.dispose();
            LaunchPage launchPage = new LaunchPage();
        }
    }
}
