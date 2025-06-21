import java.awt.event.*;
import javax.swing.*;


public class listaPage implements ActionListener{
    JFrame frame = new JFrame();
    JButton retornarButton = new JButton("Retornar");
    JLabel tituloLabel = new JLabel("Lista de Ferramentas");

    listaPage(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==retornarButton){
            frame.dispose();
            NewWindow myWindow = new NewWindow();
        }
    }
}
