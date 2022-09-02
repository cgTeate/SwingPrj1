import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Project01 extends JFrame {

    private JButton button1;
    private JButton button2;
    private JPanel panel1;
    private JPanel panel2;

    public Project01() {
        super("Project01");

        ActionHandler ah = new ActionHandler();

        JPanel buttonPanel=new JPanel();
        //helps you change the defaultlayout
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        add(buttonPanel, BorderLayout.WEST);

        button1=new JButton("Panel 1");
        button1.addActionListener(ah);
        buttonPanel.add(button1);

        button2=new JButton("Panel 2");
        button2.addActionListener(ah);
        buttonPanel.add(button2);

        JPanel mainPanel=new JPanel();
        mainPanel.setLayout(new GridLayout(2,1));
        add(mainPanel);

        panel1=new JPanel();
        panel1.setPreferredSize(new Dimension(200, 200));
        panel1.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(panel1);

        panel2=new JPanel();
        panel2.setPreferredSize(new Dimension(200, 200));
        panel2.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(panel2);

        mainPanel.add(panel1);
        mainPanel.add(panel2);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private class ActionHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==button1)
            {
                if(panel1.getBackground()==Color.LIGHT_GRAY){
                    panel1.setBackground(Color.BLUE);
                }
                else {
                    panel1.setBackground(Color.LIGHT_GRAY);
                }
            }
            else if(e.getSource()==button2)
            {
                if(panel2.getBackground()==Color.LIGHT_GRAY){
                    panel2.setBackground(Color.GREEN);
                } 
                else {
                    panel2.setBackground(Color.LIGHT_GRAY);
                }
            }
    }       
    }

    public static void main(String[] args) {
        new Project01();
    }
}
