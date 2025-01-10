import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class Main {
    public static void main(String[] args) {


        JFrame marco = new JFrame("Login");
        marco.setContentPane(new login().login);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(800, 600);
        marco.setPreferredSize( new Dimension(800, 600) );
        marco.pack();
        marco.setVisible(true);




    }
}