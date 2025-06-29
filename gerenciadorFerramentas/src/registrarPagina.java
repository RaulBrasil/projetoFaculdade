import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*; //Para mexer com arquivos
import java.sql.Timestamp; //Para mexer com tempo
import java.util.*;
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
    JComboBox nomeField = new JComboBox(); //ComboBox é uma caixa de seleção dropdown que mostra várias opçõs
    JComboBox ferrField = new JComboBox();
    JComboBox acaoField = new JComboBox();
    java.util.Date date= new java.util.Date(); //Para saber a data e horário
    private final String filePath = "lista_ocorrencias.txt"; //Constante imutável com o arquivo desejado
    
    public registrarPagina() {
        acaoField.setBounds(100,165,200,30);

        acaoField.addItem("Removeu");
        acaoField.addItem("Devolveu");
        acaoField.addItem("Desfazer Ocorrencia"); //Adiciona esses itens à combobox desejada

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
        nomeField = new JComboBox<>(lines.toArray(new String[0])); //O Array e trycatch antes disso serve para poder usar cada linha de um array como uma entrada em uma ComboBox.
        
        ArrayList<String> lines1 = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("lista_ferramentas.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                lines1.add(line);
            }
        } catch (IOException e){
            JOptionPane.showMessageDialog(acaoField, this, "Error reading file: " + e.getMessage(), 0);
        }
        ferrField = new JComboBox<>(lines1.toArray(new String[0])); //Mesma coisa que o outro
        
        nomeField.setBounds(100,85,200,30);
        ferrField.setBounds(100,125,200,30);
        frame.add(nomeField);
        frame.add(ferrField);
        frame.add(acaoField);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String funcionario = new String((String) nomeField.getSelectedItem());
        String ferramenta = new String((String) ferrField.getSelectedItem()); //Um jeito bem extenso de transformar o item selecionado em uma string utilizavel

        if(e.getSource()==returnButton){
            frame.dispose();
            NewWindow myWindow = new NewWindow();
        }


        if(e.getSource()==confirmButton && acaoField.getSelectedItem()=="Devolveu"){ //Devolveu
            //Registrar ocorrência na lista, que mostra na listaPage
            try (FileWriter writer = new FileWriter("lista_ocorrencias.txt", true)){
                writer.write("[" + new Timestamp(date.getTime()) +"] " + funcionario + " devolveu " + ferramenta + System.lineSeparator());
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

            //Registra a ocorrência na lista de ocorrencias
            try (FileWriter writer = new FileWriter("lista_ocorrencias.txt", true)){
                writer.write("[" + new Timestamp(date.getTime()) + "] " + funcionario + " removeu " + ferramenta + System.lineSeparator());
                JOptionPane.showMessageDialog(frame, "Salvo!");
            }catch (IOException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error appending file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            //Remove o item de disponibilidade
            String tobeRemoved = (String) ferrField.getSelectedItem();
            try{
                Path path = Paths.get("lista_disponivel.txt");
                List<String> lines = Files.readAllLines(path);
                boolean removed = lines.removeIf(line -> line.equals(tobeRemoved)); 
                if (removed){
                    Files.write(path, lines);
                }else{
                    JOptionPane.showMessageDialog(null,"Não removido da disponibilidade. É possível que a ferramenta não exista, ou não esteje disponível.");
                } 
                }catch (IOException ex){
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
        if(e.getSource()==confirmButton && acaoField.getSelectedItem()=="Desfazer Ocorrencia"){ //Desfaz a ultima ocorrencia, lendo o arquivo inteiro e selecionando a penultima (a ultima é vazia) e a deletando

            try {
                Path path = Paths.get(filePath);
                List<String> lines = Files.readAllLines(path);
                if (!lines.isEmpty()) {
                    lines.remove(lines.size() - 1);
                    Files.write(path, lines);
                    JOptionPane.showMessageDialog(null,"Ultima ocorrência foi desfeita.");
                }else{
                    System.out.println("HUH?!"); //Debug
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
            System.out.println("Desfez!");
        }
        if(e.getSource()==uploadButton){ //Mesma coisa que os outros uploads vistos em PasswordSaver.java e itemSaver.java

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION){
                File sourceFile = fileChooser.getSelectedFile();
                File targetFile = new File("lista_ocorrencias.txt");   
                try (
                BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile, true))
            ) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }

                JOptionPane.showMessageDialog(null, "Content appended successfully to: " + targetFile.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error appending file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            System.out.println("Upload!");
        }
    }
}
}
