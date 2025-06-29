import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class disponPage implements ActionListener {
    JFrame frame = new JFrame();
    JLabel tituloLabel = new JLabel("Lista de Disponibilidade");
    JButton retornarButton = new JButton("Retornar");
    JTextArea textArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textArea); //Implementa uma barra de scroll no JTextArea indicado

    public disponPage() throws IOException { //Do jeito que está feito, é necessário automaticamente jogar fora IOException, para não ter problema.
        //Necessário ser public pro resto da classe ter acesso
        tituloLabel.setBounds(80,5,325,50);
        tituloLabel.setFont(new Font("Arial",Font.PLAIN,20));
        retornarButton.setBounds(100,250,200,30);
        retornarButton.addActionListener(this);
        textArea.setEditable(false); //Define se é editavel
        textArea.setLineWrap(true); //Define se o texto pode "sair" da caixa de texto
        textArea.setWrapStyleWord(true); //Define o estilo do linewrap

        scrollPane.setBounds(10, 70, 360, 150); //O JScrollPane serve como JTextArea, então tudo é implementado atraves dele
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); //Determina se a barra aparece ou não, na vertical
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); //Mesma coisa mas na horizontal

        loadTextFile("lista_disponivel.txt"); //É necessário declarar aqui para acessar na função loadTextFile.

        frame.add(tituloLabel);
        frame.add(retornarButton);
        frame.add(scrollPane);

        frame.setLayout(null);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void loadTextFile(String filePath){ //Existe para colocar o texto dentro do JTextArea, de um arquivo .txt, cujo destino foi determinado antes
        File file = new File(filePath);
        if (!file.exists()){
            try (FileWriter writer = new FileWriter("lista_disponivel.txt", true)) { //Cria o arquio se ele já não existir
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) { //Lê linha por linha
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n"); //Adiciona a linha no JTextArea até que não tenha mais
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