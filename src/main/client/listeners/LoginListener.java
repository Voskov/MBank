package main.client.listeners;

import main.client.LoginFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Einstine on 08/07/2014.
 */
public class LoginListener implements ActionListener {

    private String username;

    public LoginListener (LoginFrame st){
        String  username = st.usr;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.print(username);
    }
}
