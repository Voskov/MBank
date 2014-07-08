package main.client;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    public LoginFrame(){
        JLabel loginLbl  = new JLabel("Login");
        JLabel usernameLbl  = new JLabel("Username:");
        JLabel passwordLbl  = new JLabel("Password:");

        JTextField usernameFld = new JTextField(20);
        JPasswordField passwordFld = new JPasswordField(20);

        JButton submitBtn = new JButton("Login");
//        submitBtn.

        JPanel panel =  new JPanel();

        panel.add(loginLbl);
        panel.add(usernameLbl);
        panel.add(usernameFld);
        panel.add(passwordLbl);
        panel.add(passwordFld);
        panel.add(submitBtn);

//        this.getContentPane().add(loginLbl);
//        this.getContentPane().add(usernameLbl);
//        this.getContentPane().add(usernameFld);
//        this.getContentPane().add(passwordLbl);
//        this.getContentPane().add(passwordFld);

        this.getContentPane().add(panel);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
    }

    public static void main(String[] args) {
        LoginFrame f = new LoginFrame();
        f.setSize(new Dimension(1000,300));
    }
}
