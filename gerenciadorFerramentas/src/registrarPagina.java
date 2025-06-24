import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
        String funcionario = new String((String) nomeField.getSelectedItem());
        String ferramenta = new String((String) ferrField.getSelectedItem());

        if(e.getSource()==returnButton){
            frame.dispose();
            NewWindow myWindow = new NewWindow();
        }


        if(e.getSource()==confirmButton && acaoField.getSelectedItem()=="Devolveu"){ //Devolveu
            //Registrar ocorrência na lista, que mostra na listaPage
            try (FileWriter writer = new FileWriter("lista_ocorrencias.txt", true)){
                writer.write(funcionario + " devolveu " + ferramenta + System.lineSeparator());
                JOptionPane.showMessageDialog(frame, "Salvo!");
            }catch (IOException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error appending file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            //Colocar a ferramenta de volta na lista_disponivel para poder tirar depois
            try (FileWriter writer1 = new FileWriter("lista_disponivel.txt", true)){
                writer1.write(ferramenta + System.lineSeparator());
                JOptionPane.showMessageDialog(frame, "Salvo!");
            }catch (IOException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error appending file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            System.out.println("Devolveu!");
        }


        if(e.getSource()==confirmButton && acaoField.getSelectedItem()=="Removeu"){ //Removeu
            System.out.println("Removeu!");

            //Registra a ocorreência na lista de ocorrencias
            try (FileWriter writer = new FileWriter("lista_ocorrencias.txt", true)){
                writer.write(funcionario + " removeu " + ferramenta + System.lineSeparator());
                JOptionPane.showMessageDialog(frame, "Salvo!");
            }catch (IOException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error appending file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            //Remove o item de disonibilidade
            String tobeRemoved = (String) ferrField.getSelectedItem();
            try{
                Path path = Paths.get("lista_disponivel.txt");
                List<String> lines = Files.readAllLines(path);
                boolean removed = lines.removeIf(line -> line.equals(tobeRemoved));
                if (removed){
                    Files.write(path, lines);
                }else{
                    JOptionPane.showMessageDialog(null,"File not found.");
                } 
                }catch (IOException ex){
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }

        }
        if(e.getSource()==confirmButton && acaoField.getSelectedItem()=="Desfazer Ocorrencia"){ //Desfazer
            System.out.println("Desfez!");
        }
        if(e.getSource()==uploadButton){
            System.out.println("Upload!");
        }
    }
}

