import javax.swing.*;
import java.awt.Color;
import helper_classes.*;

public class WindowBuilder {
  public static void main(String[] args) {

     JFrame frame = new JFrame("My Awesome Window");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(883, 477);
     JPanel panel = new JPanel();
     panel.setLayout(null);
     panel.setBackground(Color.decode("#1e1e1e"));

     JTextField element1 = new JTextField("");
     element1.setBounds(213, 113, 106, 21);
     element1.setFont(CustomFontLoader.loadFont("./resources/fonts/Lato.ttf", 14));
     element1.setBackground(Color.decode("#B2B2B2"));
     element1.setForeground(Color.decode("#656565"));
     element1.setBorder(new RoundedBorder(2, Color.decode("#979797"), 0));
     OnFocusEventHelper.setOnFocusText(element1, "Your Input!", Color.decode("#353535"),   Color.decode("#656565"));
     panel.add(element1);

     JPasswordField element2 = new JPasswordField("");
     element2.setBounds(213, 197, 106, 21);
     element2.setFont(CustomFontLoader.loadFont("./resources/fonts/Lato.ttf", 14));
     element2.setBackground(Color.decode("#B2B2B2"));
     element2.setForeground(Color.decode("#656565"));
     element2.setBorder(new RoundedBorder(2, Color.decode("#979797"), 0));
     OnFocusEventHelper.setOnFocusText(element2, "Your Password!", Color.decode("#353535"),   Color.decode("#656565"));
     panel.add(element2);

     JTextArea element3 = new JTextArea("");
     element3.setBounds(207, 257, 106, 41);
     element3.setFont(CustomFontLoader.loadFont("./resources/fonts/Lato.ttf", 14));
     element3.setBackground(Color.decode("#B2B2B2"));
     element3.setForeground(Color.decode("#656565"));
     element3.setBorder(new RoundedBorder(2, Color.decode("#979797"), 0));
     OnFocusEventHelper.setOnFocusText(element3, "Your long Input!", Color.decode("#353535"),   Color.decode("#656565"));
     panel.add(element3);

     frame.add(panel);
     frame.setVisible(true);

  }
}