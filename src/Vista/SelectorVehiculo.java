package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectorVehiculo extends JFrame {

    public SelectorVehiculo(MenuPrincipal menuPrincipal) {

        setTitle("Seleccionar Vehículo");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Fondo moderno
        JPanel fondo = new JPanel();
        fondo.setBackground(new Color(15, 15, 15));
        fondo.setLayout(new GridBagLayout());
        setContentPane(fondo);

        // Panel de 2 filas y 3 columnas
        JPanel panelVehiculos = new JPanel(new GridLayout(2, 3, 30, 30));
        panelVehiculos.setOpaque(false);
        panelVehiculos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Vehículos
        panelVehiculos.add(crearTarjeta("CARRO", "carro", "carroSelect.png", menuPrincipal));
        panelVehiculos.add(crearTarjeta("CARRO PRO", "carroPro", "carroProSelect.png", menuPrincipal));
        panelVehiculos.add(crearTarjeta("MOTO", "moto", "motoSelect.png", menuPrincipal));

        panelVehiculos.add(crearTarjeta("MOTO PRO", "motoPro", "motoProSelect.png", menuPrincipal));
        panelVehiculos.add(crearTarjeta("BICICLETA", "bicicleta", "biciSelect.png", menuPrincipal));
        panelVehiculos.add(crearTarjeta("BICICLETA PRO", "bicicletaPro", "biciProSelect.png", menuPrincipal));

        fondo.add(panelVehiculos);

        setVisible(true);
    }

    private JPanel crearTarjeta(String nombreMostrar, String vehiculoInterno, String imagen, MenuPrincipal menuPrincipal) {

        JPanel tarjeta = new JPanel();
        tarjeta.setPreferredSize(new Dimension(250, 350));
        tarjeta.setBackground(new Color(40, 40, 40));
        tarjeta.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Imagen
        JLabel img = new JLabel();
        img.setHorizontalAlignment(SwingConstants.CENTER);
        img.setIcon(new ImageIcon(getClass().getResource("/Imagenes/imagenes/" + imagen)));
        tarjeta.add(img, BorderLayout.CENTER);

        // Texto
        JLabel texto = new JLabel(nombreMostrar, SwingConstants.CENTER);
        texto.setForeground(Color.WHITE);
        texto.setFont(new Font("Arial", Font.BOLD, 20));
        tarjeta.add(texto, BorderLayout.SOUTH);

        // Efectos hover
        tarjeta.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                tarjeta.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
                tarjeta.setBackground(new Color(60, 60, 60));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tarjeta.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
                tarjeta.setBackground(new Color(40, 40, 40));
            }

            @Override
            public void mouseClicked(MouseEvent e) {

                menuPrincipal.setVehiculo(vehiculoInterno);

                JOptionPane.showMessageDialog(
                        null,
                        "Has seleccionado: " + nombreMostrar,
                        "Vehículo seleccionado",
                        JOptionPane.INFORMATION_MESSAGE
                );

                menuPrincipal.setVisible(true);
                dispose();
            }
        });

        return tarjeta;
    }
}
