package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.BufferedImage;

public class SelectorVehiculo extends JFrame {

    private MenuPrincipal menuPrincipal;
    private JPanel fondo;

    public SelectorVehiculo(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;

        setTitle("Seleccionar Vehículo - Elige tu Aventura");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(1400, 900);
        setLocationRelativeTo(null);

        // Fondo animado
        fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(10, 10, 20),
                        0, getHeight(), new Color(30, 30, 50));
                g2.setPaint(gradient);
                g2.fillRect(0, 0, getWidth(), getHeight());

                g2.setColor(new Color(255, 255, 255, 50));
                for (int i = 0; i < 50; i++) {
                    int x = (int)(Math.random() * getWidth());
                    int y = (int)(Math.random() * getHeight());
                    g2.fillOval(x, y, 2, 2);
                }
            }
        };
        fondo.setLayout(new BorderLayout());
        setContentPane(fondo);

        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JButton btnRegresar = crearBotonRegresar();
        panelSuperior.add(btnRegresar, BorderLayout.WEST);

        JLabel titulo = new JLabel("SELECCIONA TU VEHÍCULO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setForeground(new Color(130, 200, 255));
        panelSuperior.add(titulo, BorderLayout.CENTER);

        fondo.add(panelSuperior, BorderLayout.NORTH);

        // Panel vehiculos
        fondo.add(crearPanelVehiculos(), BorderLayout.CENTER);

        // Panel inferior
        JPanel panelInferior = new JPanel();
        panelInferior.setOpaque(false);
        JLabel info = new JLabel("Haz clic en un vehículo para seleccionarlo");
        info.setForeground(Color.WHITE);
        info.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInferior.add(info);

        fondo.add(panelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }

    // -------------------- PANEL DE VEHÍCULOS --------------------
    private JPanel crearPanelVehiculos() {

        JPanel panel = new JPanel(new GridLayout(2, 3, 40, 40));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        panel.add(crearTarjetaVehiculo("CARRO", "carro", "carroSelect.png", new Color(80, 120, 200),
                "Velocidad media, tamaño grande"));
        panel.add(crearTarjetaVehiculo("CARRO PRO", "carroPro", "carroProSelect.png", new Color(100, 180, 255),
                "Alta velocidad, mejor control"));
        panel.add(crearTarjetaVehiculo("MOTO", "moto", "motoSelect.png", new Color(220, 100, 100),
                "Velocidad alta, tamaño pequeño"));

        panel.add(crearTarjetaVehiculo("MOTO PRO", "motoPro", "motoProSelect.png", new Color(255, 150, 100),
                "Máxima velocidad, ágil"));
        panel.add(crearTarjetaVehiculo("BICICLETA", "bicicleta", "biciSelect.png", new Color(100, 220, 100),
                "Velocidad baja, muy maniobrable"));
        panel.add(crearTarjetaVehiculo("BICICLETA PRO", "bicicletaPro", "biciProSelect.png", new Color(150, 255, 150),
                "Velocidad media, excelente control"));

        return panel;
    }

    // -------------------- TARJETAS --------------------
    private JPanel crearTarjetaVehiculo(String nombre, String tipo, String archivoImagen,
                                        Color colorBase, String descripcion) {

        JPanel tarjeta = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                GradientPaint degradado = new GradientPaint(
                        0, 0, colorBase.darker().darker(),
                        0, getHeight(), colorBase
                );

                g2.setPaint(degradado);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

                g2.setStroke(new BasicStroke(3));
                g2.setColor(colorBase.brighter());
                g2.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 25, 25);
            }
        };

        tarjeta.setOpaque(false);
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tarjeta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // IMAGEN RECORTADA Y ESCALADA
        JLabel imgLabel = new JLabel("", SwingConstants.CENTER);

        URL url = getClass().getResource("/Imagenes/imagenes/" + archivoImagen);
        if (url != null) {
            imgLabel.setIcon(recortarYEscalar(url, 320, 160));
        } else {
            imgLabel.setText("Imagen no encontrada");
            imgLabel.setForeground(Color.WHITE);
        }

        tarjeta.add(imgLabel, BorderLayout.CENTER);

        // TÍTULO
        JLabel lblNombre = new JLabel(nombre, SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 24));
        lblNombre.setForeground(Color.WHITE);
        tarjeta.add(lblNombre, BorderLayout.NORTH);

        // DESCRIPCIÓN
        JLabel lblDesc = new JLabel(descripcion, SwingConstants.CENTER);
        lblDesc.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDesc.setForeground(Color.WHITE);
        tarjeta.add(lblDesc, BorderLayout.SOUTH);

        // EVENTO CLICK
        tarjeta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menuPrincipal.setVehiculo(tipo);
                mostrarConfirmacionSeleccion(nombre);
            }
        });

        return tarjeta;
    }

    // -------------------- BOTÓN REGRESAR --------------------
    private JButton crearBotonRegresar() {
        JButton btn = new JButton("← Regresar al Menú");
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(90, 140, 220));
        btn.setFocusPainted(false);

        btn.addActionListener(e -> cerrarSelector());

        return btn;
    }

    // -------------------- CONFIRMACIÓN --------------------
    private void mostrarConfirmacionSeleccion(String vehiculo) {
        JOptionPane.showMessageDialog(this,
                "Vehículo seleccionado:\n" + vehiculo,
                "Confirmación",
                JOptionPane.INFORMATION_MESSAGE);
        cerrarSelector();
    }

    // -------------------- RECORTE + ESCALADO --------------------
    private ImageIcon recortarYEscalar(URL imageUrl, int maxAncho, int maxAlto) {

        try {
            ImageIcon original = new ImageIcon(imageUrl);
            Image img = original.getImage();
            int w = original.getIconWidth();
            int h = original.getIconHeight();

            BufferedImage buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = buffer.createGraphics();
            g2d.drawImage(img, 0, 0, null);
            g2d.dispose();

            int minX = w, minY = h, maxX = 0, maxY = 0;

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if ((buffer.getRGB(x, y) >> 24) != 0x00) {
                        if (x < minX) minX = x;
                        if (y < minY) minY = y;
                        if (x > maxX) maxX = x;
                        if (y > maxY) maxY = y;
                    }
                }
            }

            BufferedImage recortada = buffer.getSubimage(
                    minX, minY,
                    (maxX - minX + 1),
                    (maxY - minY + 1)
            );

            Image escalada = recortada.getScaledInstance(maxAncho, maxAlto, Image.SCALE_SMOOTH);
            return new ImageIcon(escalada);

        } catch (Exception e) {
            e.printStackTrace();
            return new ImageIcon(imageUrl);
        }
    }

    // -------------------- CERRAR --------------------
    private void cerrarSelector() {
        menuPrincipal.setVisible(true);
        dispose();
    }
}
