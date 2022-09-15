// import java.awt.*;
// import java.awt.event.*;
// import java.awt.image.*;
// import javax.swing.*;
// import javax.imageio.*;
// import java.io.*;

// public class Project03Ip extends JFrame {
//         private GamePanel gamePanel;
//         private int[] bx;
//         private int[] by;
//         private int[] sx;
//         private int[] sy;
//         private int xoff;
//         private int yoff;
//         private int origx;
//         private int origy;
//         private BufferedImage redBallBI;
//         private int selected=-1;
//         private boolean[] gone;
//         private int[] ia;


//     public Project03Ip() {
//         super("Project03");

//         MouseHandler mh = new MouseHandler();
//         MouseMotionHandler mmh = new MouseMotionHandler();
        
//         gamePanel=new GamePanel();
//         gamePanel.setPreferredSize(new Dimension(280,300));
//         gamePanel.addMouseListener(mh);
//         gamePanel.addMouseMotionListener(mmh);
//         add(gamePanel);


//         bx=new int[10];
//         by=new int[10];
//         sx=new int[10];
//         sy=new int[10];
//         gone=new boolean[10];
//         ia=new int[10];

        
//        for (int i=0; i<ia.length; i++) ia[i]=i;

//         for(int i=0; i<ia.length; i++){
//             int index = (int) (Math.random() * ia.length);
//             int index2 = (int) (Math.random() * ia.length);
//             int tmp=ia[index];
//             ia[index]=ia[index2];
//             ia[index2]=tmp;
//         }

//         for(int i=0; i<bx.length; i++){
//             bx[i]=40+(i%5)*50;
//             by[i]=40+(i/5)*50;
//             sx[i]=40+(i%5)*50;
//             sy[i]=200+(i/5)*50;
//         }

        

//         try {
//             redBallBI=ImageIO.read(new File("red.png"));
//         } catch (IOException ioe) {
//             System.out.println(ioe);
//         }

//         toolkit=getToolkit();
//         setDefaultCloseOperation(EXIT_ON_CLOSE);
//         pack();
//         setVisible(true);
//     }


//     //view
//     private class GamePanel extends JPanel {
//         public void paintComponent(Graphics g) {
//             super.paintComponent(g);

//             //draws the squares at the center of each
//             for(int i=0;i<bx.length;i++){
//                 g.setColor(Color.BLACK);
//                 g.fillRect(sx[i]-20,sy[i]-20,40,40);
//                 g.setColor(Color.WHITE);
//                 g.drawString(""+ia[i],sx[i]-3,sy[i]+4);
//             }
//             //draws the red balls at the center of each
//             for(int i=0;i<bx.length;i++){
//                 if(gone[i]) continue;
//                 if(i==selected)continue;
//                 g.drawImage(redBallBI,bx[i]-20,by[i]-20,null);
//                 g.setColor(Color.WHITE);
//                 g.drawString(""+ia[i],bx[i]-3,by[i]+4);
//             }
//             //draws selected ball over the others 
//             if(selected!=-1){
//                 g.drawImage(redBallBI,bx[selected]-20,by[selected]+-20,null);
//                 g.setColor(Color.WHITE);
//                 g.drawString(""+ia[selected],bx[selected]-3,by[selected]+4);
//             }
            
//         }
//     }

//     private class AnimationThread extends Thread{
//         private int index;
//         private float startx;
//         private float starty;
//         private float endx;
//         private float endy;
//         private int steps;
//         private float deltax;
//         private float deltay;
//         private Toolkit toolkit;

//         // public GameThread(int index, float startx, float starty, float endx, float endy) { 
//         //     this.index=index;
//         //     this.startx=startx;
//         //     this.starty=starty;
//         //     this.endx=endx;
//         //     this.endy=endy;
//         //     steps=(int)(Math.sqrt((endx-startx)*(endx-startx)
//         //                         +(endy-starty)*(endy-starty))/10f);
//         //     deltax=(endx-startx)/steps;
//         //     deltay=(endy-starty)/steps;
//         // }

//         public void run(){
//             try {
//                 for(int i=0;i<steps;i++){
//                     sleep(20);
//                     bx[index]+=(int)(startx+i*deltax);
//                     by[index]+=(int)(starty+i*deltay);
//                     gamePanel.repaint();
//                     toolkit.sync();
//                 }
//                 bx[index]=(int)endx;
//                 by[index]=(int)endy;
                
//             } catch (InterruptedException ie) {
                
//             }
//         }
//     }

//     private class MouseHandler extends MouseAdapter 
//     {
//         public void mousePressed(MouseEvent e) 
//         {
//             //tells which ball is selected
//             for(int i=0;i<bx.length;i++){
                
//                 //if the mouse is within the ball
//                 if(Math.abs(e.getX()-bx[i])<20 && Math.abs(e.getY()-by[i])<20)
//                 {
//                     //holds the original position of the selected ball
//                     origx=bx[i];
//                     origy=by[i];
//                     //becomes the new x,y positions
//                     xoff=e.getX()-bx[i];
//                     yoff=e.getY()-by[i];
//                     selected=i;
//                 }
                
//             }
//         }
//         public void mouseReleased(MouseEvent e) {

//             if(selected==-1) return;
//             //check to see if you're within the matching square
//             for(int i=0;i<bx.length;i++){
//             if((Math.abs(e.getX()-sx[i]))<20 && (Math.abs(e.getY()-sy[i]))<20 ) {
//                 if(i==selected){
//                     gone[i]=true;
//                 }
//             }

//             if(!gone[selected]){
//                 AnimationThread(selected, bx[selected], by[selected], origx, origy).start();
//             }
//             }
            
//             //reset the position of the ball once released
//             bx[selected]=origx;
//             by[selected]=origy;
//             gamePanel.repaint();
//             //resets selected when mouse is released
//             selected=-1;
//         }
//     }

//     private class MouseMotionHandler extends MouseMotionAdapter {
//         public void mouseDragged(MouseEvent e) {
//             if(selected==-1) return;
//             //sets the new offset positions while dragging
//             bx[selected]=e.getX()-xoff; 
//             by[selected]=e.getY()-yoff;
//             gamePanel.repaint();

//     }
// }

//     public static void main(String[] args) {
//         new Project03Ip();
//     }
// }


