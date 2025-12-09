package observador;

import javax.swing.JLabel;


public class ObservadorPuntuacion extends JLabel implements Observador {
    
    public ObservadorPuntuacion() {
        super("Puntuación: 0");
        setForeground(java.awt.Color.WHITE);
        setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
    }
    
    @Override
    public void actualizar(String evento, Object dato) {
        if (evento.equals("puntos")) {
            int puntos = (int) dato;
            setText("Puntuación: " + puntos);
        }
    }
}