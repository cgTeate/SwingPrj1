import java.awt.*;
import javax.swing.*;
public class LoginForm extends JFrame {
  public LoginForm(){
    super("FirstSwingPrj");
    //code to build the UI here 

    JPanel mainPanel=new JPanel();
  mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
  add(mainPanel);

  JPanel titlePanel=new JPanel();
  mainPanel.add(titlePanel);

 titlePanel.add(
    new JLabel("Please Enter your Username and Password"));

  JPanel usernamePanel=new JPanel();
  mainPanel.add(usernamePanel);

  usernamePanel.add(new JLabel("Username:")); 
  JTextField usernameTF=new JTextField(10);
  usernamePanel.add(usernameTF);

  JPanel passwordPanel=new JPanel();
  mainPanel.add(passwordPanel);

  passwordPanel.add(new JLabel("Password:")); 
  JPasswordField passwordPF=new JPasswordField(10);
  passwordPanel.add(passwordPF);

  JPanel optionsPanel=new JPanel();
  mainPanel.add(optionsPanel);

  JCheckBox newCB=new JCheckBox("New User");
  optionsPanel.add(newCB);

  JCheckBox adminCB=new JCheckBox("Admin User");
  optionsPanel.add(adminCB);

  JPanel buttonPanel=new JPanel();
  add(buttonPanel,BorderLayout.SOUTH);

  JButton loginButton=new JButton("Login");
  buttonPanel.add(loginButton);

  JButton clearButton=new JButton("Clear");
  buttonPanel.add(clearButton);

  setDefaultCloseOperation(EXIT_ON_CLOSE);
  setSize(300,400); // or pack();
  setVisible(true);
}
public static void main(String[] args) {
  new LoginForm();
  
}

}