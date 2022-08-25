import java.awt.*;
import javax.swing.*;

      public class DrawOnJPanel extends JFrame
      {
        private GamePanel gamePanel;
        public DrawOnJPanel()
        {
          gamePanel=new GamePanel();
          add(gamePanel);
          setDefaultCloseOperation(EXIT_ON_CLOSE);
          setSize(300,400); //or pack();
          setVisible(true);
}
        private class GamePanel extends JPanel
        {
          public void paintComponent(Graphics g)
          {
            super.paintComponent(g);
            g.setColor(Color.BLUE);
            g.drawLine(0,0,20,20);
            g.drawLine(20,0,0,20);
            g.setColor(Color.RED);
            g.drawRect(0,0,20,20);
} }
        public static void main(String[] args)
        {
          new DrawOnJPanel();
} }