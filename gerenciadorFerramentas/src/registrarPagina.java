import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class registrarPagina implements ActionListener{
    JFrame frame = new JFrame();
    JLabel tituloLabel = new JLabel("Registrar Manipulação de");
    JLabel tituloLabel2 = new JLabel("Ferramentas");
    JLabel nomeLabel = new JLabel("Funcionário:");
    JLabel ferrLabel = new JLabel("Ferramenta:");
    JLabel acaoLabel = new JLabel("Ação: ");
    JButton confirmButton = new JButton("Confirmar");
    JButton returnButton = new JButton("Retornar");
    JButton uploadButton = new JButton("Cadastrar lista existente");
    JComboBox nomeField = new JComboBox();
    JComboBox ferrField = new JComboBox();
    JComboBox acaoField = new JComboBox();
    public registrarPagina() {
        acaoField.setBounds(100,165,200,30);

        acaoField.addItem("Removeu");
        acaoField.addItem("Devolveu");
        acaoField.addItem("Desfazer Ocorrencia");

        confirmButton.setBounds(100,205,200,30);
        returnButton.setBounds(100,285,200,30); 
        uploadButton.setBounds(100,245,200,30);
        uploadButton.addActionListener(this);
        confirmButton.addActionListener(this);
        returnButton.addActionListener(this);
        
        nomeLabel.setBounds(30,80,175,40);
        ferrLabel.setBounds(30,120,175,40);
        acaoLabel.setBounds(65,165,200,30);
        tituloLabel.setBounds(85,10,325,50);

        tituloLabel.setFont(new Font("Arial",Font.PLAIN,20));
        tituloLabel2.setBounds(145,30,325,50);
        tituloLabel2.setFont(new Font("Arial",Font.PLAIN,20));

        frame.add(nomeLabel);
        frame.add(ferrLabel);
        frame.add(acaoLabel);
        frame.add(confirmButton);
        frame.add(returnButton);
        frame.add(uploadButton);
        frame.add(tituloLabel);
        frame.add(tituloLabel2);

        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);

        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("lista_funcionarios.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e){
            JOptionPane.showMessageDialog(acaoField, this, "Error reading file: " + e.getMessage(), 0);
        }
        nomeField = new JComboBox<>(lines.toArray(new String[0]));
        
        ArrayList<String> lines1 = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("lista_ferramentas.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                lines1.add(line);
            }
        } catch (IOException e){
            JOptionPane.showMessageDialog(acaoField, this, "Error reading file: " + e.getMessage(), 0);
        }
        ferrField = new JComboBox<>(lines1.toArray(new String[0]));
        
        nomeField.setBounds(100,85,200,30);
        ferrField.setBounds(100,125,200,30);
        frame.add(nomeField);
        frame.add(ferrField);
        frame.add(acaoField);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        /* 
        char[] funcChars = nomeField.getSelectedItem();
        char[] ferrChars = ferrField.getSelectedItem();
        System.out.println(ferrChars);
        System.out.println(funcChars);
        String funcionario = new String(funcChars);
        String ferramenta = new String(ferrChars);
        System.out.println(ferramenta);
        System.out.println(funcionario);
        */

        String funcionario = new String((String) nomeField.getSelectedItem());
        String ferramenta = new String((String) ferrField.getSelectedItem());

        if(e.getSource()==returnButton){
            frame.dispose();
            NewWindow myWindow = new NewWindow();
        } //&& acaoField.getSelectedIndex()==1
        if(e.getSource()==confirmButton && acaoField.getSelectedItem()=="Devolveu"){ //Devolveu
            System.out.println("Devolveu!");
        }
        if(e.getSource()==confirmButton && acaoField.getSelectedItem()=="Removeu"){ //Removeu
            System.out.println("Removeu!");
        }
        if(e.getSource()==confirmButton && acaoField.getSelectedItem()=="Desfazer Ocorrencia"){ //Desfazer
            System.out.println("Desfez!");
        }
        if(e.getSource()==uploadButton){
            System.out.println("Upload!");
        }
    }
}

