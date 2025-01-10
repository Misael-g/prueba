import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class login {
    public JPanel login;
    private JTextField usuariotex;
    private JPasswordField contraseniatex;
    private JButton iniciarboton;


    public login() {
        iniciarboton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuariotex.getText();
                String contrasenia = new String(contraseniatex.getPassword());



                String url= "jdbc:mysql://localhost:3306/prueba";
                String user = "root";
                String password = "123456";


                String slql="select * from usuarios where username = ? and password = ?";

                try {
                    Connection coneccion = DriverManager.getConnection(url, user, password);
                    PreparedStatement ps = coneccion.prepareStatement(slql);
                    ps.setString(1, usuario);
                    ps.setString(2, contrasenia);
                    ResultSet resultado = ps.executeQuery();

                   if (resultado.next()) {
                       JOptionPane.showMessageDialog(null, "Iniciio Exitoso");

                       usuariotex.setText("");
                       contraseniatex.setText("");

                       JFrame marco = new JFrame("Calificaciones");
                       marco.setContentPane(new calificaciones().calificacionesp);
                       marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                       marco.setSize(800, 600);
                       marco.setPreferredSize( new Dimension(800, 600) );
                       marco.pack();
                       marco.setVisible(true);
                       ((JFrame) iniciarboton.getTopLevelAncestor()).dispose();

                   }
                   else {
                       JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
                   }
                }catch (SQLException ex){
                    System.out.println("no se puede conectar con la base de datos");
                    ex.printStackTrace();
                }


            }
        });
    }
}
