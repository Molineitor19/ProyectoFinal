package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverDialog extends JDialog {
    private int opcionSeleccionada = -1;
    
    public GameOverDialog(JFrame parent, int puntuacion) {
        super(parent, "Game Over", true);
        setLayout(new BorderLayout());
        setSize(400, 200);
        setLocationRelativeTo(parent);
        
        // Fondo oscuro
        getContentPane().setBackground(new Color(40, 40, 60));
        
        // Panel central
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(40, 40, 60));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título
        JLabel titulo = new JLabel("¡HAS CHOCADO!", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(255, 100, 100));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Puntuación
        JLabel scoreLabel = new JLabel("Puntuación final: " + puntuacion, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Espacio
        panel.add(Box.createVerticalStrut(10));
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(scoreLabel);
        panel.add(Box.createVerticalStrut(20));
        
        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(40, 40, 60));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        
        // Botones con estilo
        JButton btnReiniciar = crearBotonSimple("Reiniciar", new Color(80, 180, 80));
        JButton btnMenu = crearBotonSimple("Volver al Menú", new Color(80, 150, 220));
        JButton btnSalir = crearBotonSimple("Salir", new Color(220, 80, 80));
        
        btnReiniciar.addActionListener(e -> { opcionSeleccionada = 0; dispose(); });
        btnMenu.addActionListener(e -> { opcionSeleccionada = 1; dispose(); });
        btnSalir.addActionListener(e -> { opcionSeleccionada = 2; dispose(); });
        
        buttonPanel.add(btnReiniciar);
        buttonPanel.add(btnMenu);
        buttonPanel.add(btnSalir);
        
        panel.add(buttonPanel);
        add(panel, BorderLayout.CENTER);
    }
    
    private JButton crearBotonSimple(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setForeground(Color.BLACK); // Texto negro
        boton.setBackground(color);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(color.darker(), 2));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(120, 35));
        
        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
            }
        });
        
        return boton;
    }
    
    public int getOpcionSeleccionada() {
        return opcionSeleccionada;
    }
}