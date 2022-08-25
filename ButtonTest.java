import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonTest extends JFrame {

    private JButton button1;
    private JButton button2;
    public ButtonTest() {
        super("ButtonTest");

        ActionHandler ah = new ActionHandler();

        JPanel buttonPanel=new JPanel();
        add(buttonPanel);

        button1=new JButton("Button 1");
        button1.addActionListener(ah);
        buttonPanel.add(button1);

        button2=new JButton("Button 2");
        button2.addActionListener(ah);
        buttonPanel.add(button2);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,400); //or pack();
        setVisible(true);

    }

    private class ActionHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
          {
            if(e.getSource()==button1)
            {
              System.out.println("Button 1 clicked");
            }
            else if(e.getSource()==button2)
            {
              System.out.println("Button 2 clicked");
            }
} 
}
    public static void main(String[] args) {
        new ButtonTest();
        
      }
}