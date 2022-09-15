import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

public class Project04 extends JFrame {
        private GamePanel gamePanel;
        private int[] bx;
        private int[] by;
        private int[] sx;
        private int[] sy;
        private int xoff;
        private int yoff;
        private int origx;
        private int origy;
        private BufferedImage redBallBI;
        private int selected=-1;
        private boolean[] gone;
        private int[] ia;
        private Toolkit toolkit;
        ArrayList<AnimationParams> animationList;


    public Project04() {
        super("Project04");

        MouseHandler mh = new MouseHandler();
        MouseMotionHandler mmh = new MouseMotionHandler();
        
        gamePanel=new GamePanel();
        gamePanel.setPreferredSize(new Dimension(280,300));
        gamePanel.addMouseListener(mh);
        gamePanel.addMouseMotionListener(mmh);
        add(gamePanel);


        bx=new int[10];
        by=new int[10];
        sx=new int[10];
        sy=new int[10];
        gone=new boolean[10];
        ia=new int[10];

        
       for (int i=0; i<ia.length; i++) ia[i]=i;

        for(int i=0; i<ia.length; i++){
            int index = (int) (Math.random() * ia.length);
            int index2 = (int) (Math.random() * ia.length);
            int tmp=ia[index];
            ia[index]=ia[index2];
            ia[index2]=tmp;
        }

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

        animationList=new ArrayList<AnimationParams>();

        new GameThread().start();

        toolkit=getToolkit();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }


    //view
    private class GamePanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //draws the squares at the center of each
            for(int i=0;i<bx.length;i++){
                g.setColor(Color.BLACK);
                g.fillRect(sx[i]-20,sy[i]-20,40,40);
                g.setColor(Color.WHITE);
                g.drawString(""+ia[i],sx[i]-3,sy[i]+4);
            }
            //draws the red balls at the center of each
            for(int i=0;i<bx.length;i++){
                if(gone[i]) continue;
                if(i==selected)continue;
                g.drawImage(redBallBI,bx[i]-20,by[i]-20,null);
                g.setColor(Color.WHITE);
                g.drawString(""+ia[i],bx[i]-3,by[i]+4);
            }
            //draws selected ball over the others 
            if(selected!=-1){
                g.drawImage(redBallBI,bx[selected]-20,by[selected]+-20,null);
                g.setColor(Color.WHITE);
                g.drawString(""+ia[selected],bx[selected]-3,by[selected]+4);
            }
            
        }
    }
    //animation params class 
    private class AnimationParams
      {
        private int index;
        private float sx;
        private float sy;
        private float ex;
        private float ey;
        private float dx;
        private float dy;
        private int step=0;
        private int steps;
        public AnimationParams(int index,float sx,float sy,float ex,float ey)
        {
          this.index=index;
          this.sx=sx;
          this.sy=sy;
          this.ex=ex;
          this.ey=ey;
          steps=(int)(Math.sqrt((ex-sx)*(ex-sx)+(ey-sy)*(ey-sy))/10f);
          dx=(ex-sx)/steps;
          dy=(ey-sy)/steps;
        }    
       }

    //AnimationThread
       private class GameThread extends Thread
      {
        public void run()
        { 
            try
          {
            while(true)
            {
                sleep(20);
                synchronized(animationList)
              {
                for(int i=0;i<animationList.size();i++)
                {
                  AnimationParams ap=animationList.get(i);
                  bx[ap.index]=(int)(ap.sx+ap.step*ap.dx);
                  by[ap.index]=(int)(ap.sy+ap.step*ap.dy);
                  ap.step++;
                }
                for(int i=animationList.size()-1;i>=0;i--)
                {
                  AnimationParams ap=animationList.get(i);
                  //if animation is over, move the ball to the final position
                  if(ap.step==ap.steps) 
                  {
                    bx[ap.index]=(int)ap.ex;
                    by[ap.index]=(int)ap.ey;
                    animationList.remove(i);
                  }
                } 
               }
              toolkit.sync();
              gamePanel.repaint();
            }
           }
          catch(InterruptedException ie) {}
        }
        }

    private class MouseHandler extends MouseAdapter 
    {
        public void mousePressed(MouseEvent e) 
        {
            //tells which ball is selected
            for(int i=0;i<bx.length;i++){
                
                //if the mouse is within the ball
                if(Math.abs(e.getX()-bx[i])<20 && Math.abs(e.getY()-by[i])<20)
                {
                    //holds the original position of the selected ball
                    origx=bx[i];
                    origy=by[i];
                    //becomes the new x,y positions
                    xoff=e.getX()-bx[i];
                    yoff=e.getY()-by[i];
                    selected=i;
                }
                
            }
        }
        public void mouseReleased(MouseEvent e) {

            if(selected==-1) return;
            //check to see if you're within the matching square
            for(int i=0;i<bx.length;i++){
            if((Math.abs(e.getX()-sx[i]))<20 && (Math.abs(e.getY()-sy[i]))<20 ) {
                if(i==selected){
                    gone[i]=true;
                }
            }
            
            
            }
            //reset the position of the ball once released
            if(!gone[selected]){
                synchronized(animationList)
                {
                  animationList.add(new AnimationParams(selected,bx[selected]
                                              ,by[selected],origx,origy));
                }
            }

            //resets selected when mouse is released
            selected=-1;
        }
    }

    private class MouseMotionHandler extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            if(selected==-1) return;
            int px=bx[selected];
            int py=by[selected];
            //sets the new offset positions while dragging
            bx[selected]=e.getX()-xoff; 
            by[selected]=e.getY()-yoff;
            //makes a boundary
            if(bx[selected]<20) bx[selected]=20;
            boolean coll=false;
            
            
            for(int i=0;i<bx.length;i++)
            {
                if(i==selected) continue;
                double dx=bx[selected]-bx[i];
                double dy=by[selected]-by[i];
                if(Math.sqrt(dx*dx+dy*dy)<40){
                    coll=true;
                }
            }
            if(coll){
                bx[selected]=px;
                by[selected]=py;
            }
            
            gamePanel.repaint();

    }
}

    public static void main(String[] args) {
        new Project04();
    }
}


