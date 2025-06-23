import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class listaPage implements ActionListener {
    JFrame frame = new JFrame();
    JLabel tituloLabel = new JLabel("Lista de Retiradas/Devoluções");
    JButton retornarButton = new JButton("Retornar");
    JTextArea textArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textArea);

    public listaPage() throws IOException {

        tituloLabel.setBounds(50,5,325,50);
        tituloLabel.setFont(new Font("Arial",Font.PLAIN,20));
        retornarButton.setBounds(100,250,200,30);
        retornarButton.addActionListener(this);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane.setBounds(10, 70, 360, 150);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        loadTextFile("lista_ocorrencias.txt");

        frame.add(tituloLabel);
        frame.add(retornarButton);
        frame.add(scrollPane);

        frame.setLayout(null);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

private void loadTextFile(String filePath) {
    File file = new File(filePath);
    if (!file.exists()) {
        try (FileWriter writer = new FileWriter("lista_ocorrencias.txt", true)) {
            //writer.write("Arquivo novo criado!");
        }catch (IOException ex) {
               ex.printStackTrace(); 
        }
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            textArea.append(line + "\n");
        }
    } catch (IOException e) {
        textArea.setText("Erro ao ler o arquivo: " + e.getMessage());
    }
}



    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==retornarButton){
            frame.dispose();
            NewWindow myWindow = new NewWindow();
        }
    }
}
