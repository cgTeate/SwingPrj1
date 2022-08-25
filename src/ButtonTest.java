import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonTest extends JFrame {

    private JButton button1;
    private JButton button2;
    public ButtonTest() {
        super("ButtonTest");
        /*
         * Step 2. Create an instance of the private nested 
         * class in the constructor where you are building 
         * the UI.
         */
        ActionHandler ah = new ActionHandler();

        JPanel buttonPanel=new JPanel();
        add(buttonPanel);

        button1=new JButton("Button 1");
        /*
         * Step 3. Add the handler to the component 
         * for which it is to handle events.
         */
        button1.addActionListener(ah);
        buttonPanel.add(button1);

        button2=new JButton("Button 2");
        button2.addActionListener(ah);
        buttonPanel.add(button2);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,400); //or pack();
        setVisible(true);

    }
        /*
         * Step 1:
         * Create a private nested class that implements a listener 
         * interface or extends an adapter class. The code in this 
         * class tells the handler what to do when the event occurs.
         */
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