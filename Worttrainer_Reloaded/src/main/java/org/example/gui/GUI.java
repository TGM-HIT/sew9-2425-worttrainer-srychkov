package org.example.gui;
import javax.swing.*;

public class GUI {
    JFrame f;
    GUI(){
        f=new JFrame();
        String name = JOptionPane.showInputDialog(f,"Was ist das? :)");
        System.out.println(name);
    }
}
