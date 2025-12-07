package controlador;

import Vista.Vista;
import javax.swing.*;

public class AplMain {
    public static void main(String[] args) {
        
        String[] opciones = {
            "carro", "carroPro",
            "moto", "motoPro",
            "bicicleta", "bicicletaPro"
        };

        
        String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona tu vehículo:",
                "Menú de Vehículos",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        
        // Si el usuario cancela, salir
        if (seleccion == null) {
            return;
        }

        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Simulación de Vehículos");
            Vista panel = new Vista(seleccion);  // ✔ SE ENVÍA EL TIPO SELECCIONADO
            ventana.add(panel);
            ventana.setSize(800, 500);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}
