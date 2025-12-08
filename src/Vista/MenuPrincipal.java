package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class PanelFondo extends JPanel {
    private Image img;

    public PanelFondo() {
        img = new ImageIcon(getClass().getResource("/Imagenes/imagenes/FondoMenu.jpg")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
}

public class MenuPrincipal extends JFrame {

    private JButton btnIniciar, btnVehiculo, btnSalir;

    private String vehiculoSeleccionado = "carro";

    public void setVehiculo(String v) {
        this.vehiculoSeleccionado = v;
    }

    public MenuPrincipal() {

        setTitle("Menú Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        PanelFondo fondo = new PanelFondo();
        fondo.setLayout(new GridBagLayout());
        setContentPane(fondo);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 400));
        panel.setBackground(new Color(0, 0, 0, 160));
        panel.setLayout(new GridLayout(4, 1, 20, 20));

        Font font = new Font("Arial", Font.BOLD, 26);

        btnIniciar = new JButton("Iniciar Juego");
        btnVehiculo = new JButton("Elegir Vehículo");
        btnSalir = new JButton("Salir");

        for (JButton btn : new JButton[]{btnIniciar, btnVehiculo, btnSalir}) {
            btn.setFont(font);
            btn.setFocusPainted(false);
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(50, 50, 50));
            btn.setOpaque(true);
        }

        panel.add(new JLabel("MENÚ PRINCIPAL", SwingConstants.CENTER) {{
            setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 40));
        }});

        panel.add(btnIniciar);
        panel.add(btnVehiculo);
        panel.add(btnSalir);

        fondo.add(panel);

        btnIniciar.addActionListener(e -> iniciarJuego());
        btnVehiculo.addActionListener(e -> abrirSelectorVehiculo());
        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void iniciarJuego() {

        JFrame ventanaJuego = new JFrame("Juego");

        Vista panelJuego = new Vista(vehiculoSeleccionado);

        ventanaJuego.add(panelJuego);
        ventanaJuego.setSize(900, 500);
        ventanaJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaJuego.setLocationRelativeTo(null);
        ventanaJuego.setVisible(true);

        dispose();
    }

    private void abrirSelectorVehiculo() {
        this.setVisible(false);
        new SelectorVehiculo(this);
    }
}
