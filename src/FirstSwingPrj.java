import java.awt.*;
import javax.swing.*;



public class FirstSwingPrj extends JFrame {
  public FirstSwingPrj(){
    super("FirstSwingPrj");
    //code to build the UI here 
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(300,400); // or pack();
    setVisible(true);
}
public static void main(String[] args) {
  new FirstSwingPrj();
  
}

}