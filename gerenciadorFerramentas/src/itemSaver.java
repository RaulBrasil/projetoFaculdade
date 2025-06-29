import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class itemSaver implements ActionListener {

    JTextField passwordField = new JTextField();
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
        frame.setLayout(new java.awt.FlowLayout()); //Diferente dos outros, esse utiliza um gerenciador de layout
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
        label3.setFont(new Font("Arial",Font.PLAIN,20)); //Mexe com a fonte
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
        String passwordChars = passwordField.getText();
        String usernameChars = usernameField.getText();
        String password = new String(passwordChars);
        String username = new String(usernameChars); 
        //Sinceramente, a escolha do jeito que eu coloquei esses quatro Strings foi por causa da forma antiga que eu implementei, mas agora eu não quero mudar.

        
        if(e.getSource()==confirmButton){
            try (FileWriter writer = new FileWriter("lista_ferramentas.txt", true)) { //Cria o arquivo .txt
                writer.write(username + " (" + password + ")" + System.lineSeparator()); //Adiciona o que está inserido no nome e ID nessa formatação, dentro do .txt
                JOptionPane.showMessageDialog(frame, "Ferramenta Registrada!"); //Avisa que a operação foi realizada
                frame.dispose();
                NewWindow myWindow = new NewWindow();
            }catch (IOException ex) {
               ex.printStackTrace(); 
            }
            try(FileWriter writer1 = new FileWriter("lista_disponivel.txt",true)){ //Faz a mesma coisa, porém coloca em outro .txt para saber que está disponivel logo depois de criado
                writer1.write(username + " (" + password + ")" + System.lineSeparator());
            }catch (IOException ex){
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage()); //Debug
                ex.printStackTrace(); 
            }
        }
        if(e.getSource()==exitButton){
            frame.dispose();
            NewWindow myWindow = new NewWindow();
        }
        if(e.getSource()==uploadButton){

            JFileChooser fileChooser = new JFileChooser(); //Uma  janela de seleção de arquivos
            int result = fileChooser.showOpenDialog(null); //Anota o resultado da janela de seleção de arquivos

            if (result == JFileChooser.APPROVE_OPTION){ //Quando 'Confirmar' foi selecionado
                File sourceFile = fileChooser.getSelectedFile(); //Faz um objeto de classe File com o nome do arquivo selecionado
                File targetFile = new File("lista_ferramentas.txt"); //Determina um alvo
                try (
                BufferedReader reader = new BufferedReader(new FileReader(sourceFile)); //lê linha por linha
                BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile, true)) //Escreve linha por linha, sem sobrescrever o que tinha antes
            ) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                } //Loop para escrever linha por linha

                JOptionPane.showMessageDialog(null, "Content appended successfully to: " + targetFile.getAbsolutePath()); //Debug

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage() + JOptionPane.ERROR_MESSAGE); //Debug
            }
            }
        }
    }
}
