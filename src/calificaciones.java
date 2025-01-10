import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class calificaciones {
    private JTextField textcedula;
    private JTextField textcal1;
    public JPanel calificacionesp;
    private JTextField textnombres;
    private JButton crearButton;
    private JTextField textcal2;
    private JTextField textcal13;
    private JTextField textcal4;
    private JTextField textcal5;

    public calificaciones() {
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = textcedula.getText();
                String nombre = textnombres.getText();
                String cal1 = textcal1.getText();
                String cal2 = textcal2.getText();
                String cal3 = textcal13.getText();
                String cal4 = textcal4.getText();
                String cal5 = textcal5.getText();


                if (!validarCalificacion(cal1) || !validarCalificacion(cal2) || !validarCalificacion(cal3) ||
                        !validarCalificacion(cal4) || !validarCalificacion(cal5)) {
                    JOptionPane.showMessageDialog(null, "Las calificaciones deben estar entre 0 y 20 .");
                    return;
                }

                String url= "jdbc:mysql://localhost:3306/prueba";
                String user = "root";
                String password = "123456";

                String calificacionesba="insert into estudiantes (cedula,nombre,estudiante1,estudiante2,estudiante3,estudiante4,estudiante5)  values(?,?,?,?,?,?,?); ";

              try {
                  Connection coneccion = DriverManager.getConnection(url, user, password);
                  PreparedStatement ps = coneccion.prepareStatement(calificacionesba);
                  ps.setString(1, cedula);
                  ps.setString(2, nombre);
                  ps.setDouble(3, Double.parseDouble(cal1));
                  ps.setDouble(4, Double.parseDouble(cal2));
                  ps.setDouble(5, Double.parseDouble(cal3));
                  ps.setDouble(6, Double.parseDouble(cal4));
                  ps.setDouble(7, Double.parseDouble(cal5));

                  int result = ps.executeUpdate();
                  if (result >0){
                      JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
                      textcedula.setText("");
                      textnombres.setText("");
                      textcal1.setText("");
                      textcal2.setText("");
                      textcal13.setText("");
                      textcal4.setText("");
                      textcal5.setText("");
                  }
                  else {
                      JOptionPane.showMessageDialog(null, "Datos no actualizados correctamente");
                  }

              }catch (SQLException ex){
                  JOptionPane.showMessageDialog(null, "Error en la base de datos: " );
                  ex.printStackTrace();
              }

            }
        });
    }


    private boolean validarCalificacion(String cal) {
        try {
            double calificacion = Double.parseDouble(cal);
            return calificacion >= 0 && calificacion <= 20;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
