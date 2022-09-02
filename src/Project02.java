import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.*;
import java.awt.image.*;



public class Project02 extends JFrame {
        private GamePanel gamePanel;
        private int[] bx;
        private int[] by;
        private int[] sx;
        private int[] sy;
        private BufferedImage redBallBI;
        private int selected=-1;
        private int[] origx;
        private int[] origy;


    public Project02() {
        super("Project02");

        MouseHandler mh = new MouseHandler();
        MouseMotionHandler mmh = new MouseMotionHandler();
        
        GamePanel gamePanel=new GamePanel();
        gamePanel.setPreferredSize(new Dimension(280,300));
        gamePanel.addMouseListener(mh);
        gamePanel.addMouseMotionListener(mmh);
        add(gamePanel);


        bx=new int[10];
        by=new int[10];
        sx=new int[10];
        sy=new int[10];

        for(int i=0; i<bx.length; i++){
            bx[i]=40+(i%5)*50;
            by[i]=40+(i/5)*50;
            sx[i]=40+(i%5)*50;
            sy[i]=200+(i/5)*50;
        }

        try {
            redBallBI=ImageIO.read(new File("red.png"));
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }


    //view
    private class GamePanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.WHITE);

            for(int i=0;i<bx.length;i++){
                g.drawImage(redBallBI,bx[i]-20,by[i]-20,null);
                g.drawString(""+i,bx[i]-3,by[i]+4);
                g.setColor(Color.BLACK);
                g.fillRect(sx[i]-20,sy[i]-20,40,40);
            }
            
        }
    }

    private class MouseHandler extends MouseAdapter 
    {
        public void mousePressed(MouseEvent e) 
        {

            for(int i=0;i<bx.length;i++){
                if(Math.abs(e.getX()-bx[i])<20 && Math.abs(e.getY()-by[i])<20)
                {
                    selected=i;
                }
            }
        }
    }
    private void mouseReleased(MouseEvent e) {
        selected=-1;
    }


    private class MouseMotionHandler extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            if(selected==-1) return;
            bx[selected]=e.getX(); 
            by[selected]=e.getX(); 

            gamePanel.repaint();

    }
}

    public static void main(String[] args) {
        new Project02();
    }
}

