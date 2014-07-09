package main.client;

import main.client.listeners.LoginListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private JLabel loginLbl;
    private JLabel usernameLbl;
    private JLabel passwordLbl;
    private JTextField usernameFld;
    private JPasswordField passwordFld;
    private JButton submitBtn;

    public String usr;

    public JLabel getUsernameLbl() {
        return usernameLbl;
    }

    public void setUsernameLbl(JLabel usernameLbl) {
        this.usernameLbl = usernameLbl;
    }

    public JLabel getLoginLbl() {
        return loginLbl;
    }

    public void setLoginLbl(JLabel loginLbl) {
        this.loginLbl = loginLbl;
    }

    public JLabel getPasswordLbl() {
        return passwordLbl;
    }

    public void setPasswordLbl(JLabel passwordLbl) {
        this.passwordLbl = passwordLbl;
    }

    public JTextField getUsernameFld() {
        return usernameFld;
    }

    public void setUsernameFld(JTextField usernameFld) {
        this.usernameFld = usernameFld;
    }

    public JPasswordField getPasswordFld() {
        return passwordFld;
    }

    public void setPasswordFld(JPasswordField passwordFld) {
        this.passwordFld = passwordFld;
    }

    public JButton getSubmitBtn() {
        return submitBtn;
    }

    public void setSubmitBtn(JButton submitBtn) {
        this.submitBtn = submitBtn;
    }

    public LoginFrame(){
        loginLbl  = new JLabel("Login");
        usernameLbl  = new JLabel("Username:");
        passwordLbl  = new JLabel("Password:");

        usernameFld = new JTextField(20);
        passwordFld = new JPasswordField(20);

        submitBtn = new JButton("Login");

        String  username = usernameFld.getText();
        String  password = passwordFld.getText();

        submitBtn.addActionListener(new LoginListener(this));
        usernameFld.addActionListener(new ActionListener() {
                                          public void actionPerformed(java.awt.event.ActionEvent e) {

//                                              if (Integer.parseInt(usernameFld.getText()) <= 0) {
//                                                  JOptionPane.showMessageDialog(null,
//                                                          "Error: Please enter number bigger than 0", "Error Message",
//                                                          JOptionPane.ERROR_MESSAGE);
//                                              }
                                              usr=usernameFld.getText();
                                          }
                                      });

        JPanel panel = new JPanel();

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
