import java.awt.Font; //Para manipular a fonte de componentes de texto.
import java.awt.event.*; //Preciso do ActionListener e ActionEvent, que permite que eu saiba quando um botão for clicado.
import javax.swing.*; //o * serve para importar todos os componentes de javax.swing

public class LaunchPage extends JFrame implements ActionListener{ //extender JFrame permite que eu não precise digitar "frame." para alterar frame. actionlistener é para poder usar 'this' quando adicionar actionlisteners
    JFrame frame = new JFrame(); //JFrame é uma janela, e todos os outros componentes aqui são construidos imediatamente
    JButton myButton = new JButton("Iniciar"); //JButton é um botão simples
    JButton informacao = new JButton("Créditos"); 
    JLabel titulo1 = new JLabel("App de Gestão de Ferramentas"); //JLabel é só texto livreforme, sem caixa 
    JLabel titulo2 = new JLabel("para saber onde estão suas ferramentas");
    JLabel titulo3 = new JLabel("e quem mexeu nelas!");

    LaunchPage(){

        myButton.setBounds(100,150,200,40); //Determinar a posição e limite de um componente usa .setBounds
        myButton.addActionListener(this); //Adiciona funcionalidade para que esse componente saiba quando for clicado
        informacao.setBounds(100,225,200,40);
        informacao.addActionListener(this);

        titulo1.setBounds(8,5,500,50);
        titulo1.setFont(new Font("Arial",Font.BOLD,25)); //Altera a fonte, seu tamanho, e formatação, nesse caso, negrito
        titulo2.setBounds(15,35,500,50);
        titulo2.setFont(new Font("Arial",Font.PLAIN,20));
        titulo3.setBounds(100,50,500,50);
        titulo3.setFont(new Font("Arial",Font.PLAIN,20));

        add(myButton);
        add(informacao);
        add(titulo1);
        add(titulo2);
        add(titulo3); //Todos os componentes precisam ser adicionados, se não, não aparecem quando a janela for renderizada.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Quando fechado, a janela fecha.
        setSize(400,400); //O tamanho da janela
        setLayout(null); //LayoutManagers permitem que você manipule os componentes diferentemente na janela, mas é complicado demais para meu uso.
        setResizable(false); //Boolean para permitir ou não que a janela seje manipulada.
        setVisible(true); //"Renderiza" a janela no computador.
    }
    @Override
    public void actionPerformed(ActionEvent e){ //A função que verifica qual interação ocorreu.
        if(e.getSource()==myButton){ //Verifica qual é a fonte da interação.
            NewWindow myWindow = new NewWindow();
            frame.dispose(); //Remove a janela anterior.
        }
        if(e.getSource()==informacao){
            frame.dispose();
            creditosPage creditosPage = new creditosPage();
        }
    }
}