import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class itemSaver implements ActionListener {

    JPasswordField passwordField = new JPasswordField();
    JTextField usernameField = new JTextField();
    JFrame frame = new JFrame();
    JButton confirmButton = new JButton("Confirmar");
    JLabel label1 = new JLabel("ID da Ferramenta: ");
    JLabel label2 = new JLabel("Nome da Ferramenta: ");
    JLabel label3 = new JLabel("Tela de Cadastro de Ferramenta");
    JButton exitButton = new JButton("Voltar");
    JButton uploadButton = new JButton("Cadastrar lista existente");

    itemSaver() {

        passwordField.setSize(100,50);
        usernameField.setSize(100,50);
        frame.setLayout(new java.awt.FlowLayout());
        //frame.add(label);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(confirmButton);

        confirmButton.addActionListener(this);

        passwordField.setBounds(135,145,200,30);
        usernameField.setBounds(135,100,200,30);
        confirmButton.setBounds(135,190,200,30);
        label1.setBounds(5,140,175,40);
        label2.setBounds(5,95,175,40);
        label3.setBounds(40,5,325,50);
        exitButton.setBounds(135,305,200,30);
        uploadButton.setBounds(135,235,200,30);
        uploadButton.addActionListener(this);
        exitButton.addActionListener(this);
        label3.setFont(new Font("Arial",Font.PLAIN,20));
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(exitButton);
        frame.add(uploadButton);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        char[] passwordChars = passwordField.getPassword();
        String usernameChars = usernameField.getText();
        String password = new String(passwordChars);
        String username = new String(usernameChars);

        
        if(e.getSource()==confirmButton){
            try (FileWriter writer = new FileWriter("lista_ferramentas.txt", true)) {
            writer.write(username + ", " + password + System.lineSeparator());
            JOptionPane.showMessageDialog(frame, "Saved!");
            frame.dispose();
            NewWindow myWindow = new NewWindow();
            }catch (IOException ex) {
               ex.printStackTrace(); 
            }
        }
        if(e.getSource()==exitButton){
            frame.dispose();
            NewWindow myWindow = new NewWindow();
        }
        if(e.getSource()==uploadButton){

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION){
                File sourceFile = fileChooser.getSelectedFile();
                File targetFile = new File("lista_ferramentas.txt");   
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
            System.out.println("wowzers!");
            }
        }
    }
}
