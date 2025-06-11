import java.awt.event.*;
import javax.swing.*;

public class NewWindow implements ActionListener{
    
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Whats up!");
    JButton button = new JButton("Go Back");
    JButton button1 = new JButton("Log password");

    NewWindow(){

        button.setBounds(100,160,200,40);
        button.setFocusable(false);
        button.addActionListener(this);
        button1.setBounds(100,260,200,40);
        button1.setFocusable(false);
        button1.addActionListener(this);
        frame.add(button);
        frame.add(button1);
        label.setBounds(0,0,200,50);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==button){
            frame.dispose();
            LaunchPage launchPage = new LaunchPage();
        }
        if(e.getSource()==button1){
            frame.dispose();
            PasswordSaver PasswordSaver = new PasswordSaver();
        }
    }
}
