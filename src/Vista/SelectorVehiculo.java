package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class SelectorVehiculo extends JFrame {

    private MenuPrincipal menuPrincipal;
    private JPanel fondo;

    public SelectorVehiculo(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        
        setTitle("Seleccionar Vehículo - Elige tu Aventura");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        // Tamaño grande para mostrar bien las imágenes
        setSize(1400, 900);
        setLocationRelativeTo(null);
        
        // Fondo con gradiente
        fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradiente de fondo
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(10, 10, 20),
                    0, getHeight(), new Color(30, 30, 50)
                );
                g2.setPaint(gradient);
                g2.fillRect(0, 0, getWidth(), getHeight());
                
                // Efecto de partículas o estrellas
                g2.setColor(new Color(255, 255, 255, 50));
                for (int i = 0; i < 50; i++) {
                    int x = (int)(Math.random() * getWidth());
                    int y = (int)(Math.random() * getHeight());
                    int size = (int)(Math.random() * 3) + 1;
                    g2.fillOval(x, y, size, size);
                }
            }
        };
        fondo.setLayout(new BorderLayout());
        setContentPane(fondo);
        
        // Panel superior con título y botón de regreso
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        // Botón para regresar
        JButton btnRegresar = crearBotonRegresar();
        panelSuperior.add(btnRegresar, BorderLayout.WEST);
        
        // Título
        JLabel titulo = new JLabel("SELECCIONA TU VEHÍCULO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 42));
        titulo.setForeground(new Color(100, 200, 255));
        panelSuperior.add(titulo, BorderLayout.CENTER);
        
        fondo.add(panelSuperior, BorderLayout.NORTH);
        
        // Panel central con los vehículos
        JPanel panelVehiculos = crearPanelVehiculos();
        fondo.add(panelVehiculos, BorderLayout.CENTER);
        
        // Panel inferior con información
        JPanel panelInferior = new JPanel();
        panelInferior.setOpaque(false);
        panelInferior.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        
        JLabel instruccion = new JLabel("Haz clic en un vehículo para seleccionarlo", SwingConstants.CENTER);
        instruccion.setFont(new Font("Arial", Font.ITALIC, 16));
        instruccion.setForeground(new Color(200, 220, 255));
        panelInferior.add(instruccion);
        
        fondo.add(panelInferior, BorderLayout.SOUTH);
        
        setVisible(true);
        
        // Permitir salir con ESC
        fondo.setFocusable(true);
        fondo.requestFocus();
        fondo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cerrarSelector();
                }
            }
        });
    }
    
    private JPanel crearPanelVehiculos() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 40, 40));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));
        
        // Crear tarjetas de vehículos
        panel.add(crearTarjetaMejorada("CARRO", "carro", "carroSelect.png", 
                new Color(80, 120, 200), "Velocidad media, tamaño grande"));
        panel.add(crearTarjetaMejorada("CARRO PRO", "carroPro", "carroProSelect.png",
                new Color(100, 180, 255), "Alta velocidad, mejor control"));
        panel.add(crearTarjetaMejorada("MOTO", "moto", "motoSelect.png",
                new Color(220, 100, 100), "Velocidad alta, tamaño pequeño"));
        panel.add(crearTarjetaMejorada("MOTO PRO", "motoPro", "motoProSelect.png",
                new Color(255, 150, 100), "Máxima velocidad, ágil"));
        panel.add(crearTarjetaMejorada("BICICLETA", "bicicleta", "biciSelect.png",
                new Color(100, 220, 100), "Velocidad baja, muy maniobrable"));
        panel.add(crearTarjetaMejorada("BICICLETA PRO", "bicicletaPro", "biciProSelect.png",
                new Color(150, 255, 150), "Velocidad media, excelente control"));
        
        return panel;
    }
    
    private JPanel crearTarjetaMejorada(String nombre, String tipo, String imagen, Color colorBase, String descripcion) {
        JPanel tarjeta = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo con gradiente
                GradientPaint gradient = new GradientPaint(
                    0, 0, colorBase.darker().darker(),
                    0, getHeight(), colorBase
                );
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                
                // Borde con efecto de brillo
                g2.setStroke(new BasicStroke(3));
                g2.setColor(colorBase.brighter());
                g2.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 25, 25);
                
                // Sombra interior
                g2.setColor(new Color(255, 255, 255, 30));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(3, 3, getWidth()-6, getHeight()-6, 22, 22);
            }
        };
        
        tarjeta.setLayout(new BorderLayout(10, 10));
        tarjeta.setOpaque(false);
        tarjeta.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tarjeta.setPreferredSize(new Dimension(350, 450)); // Tamaño aumentado
        
        // Panel para la imagen (con mejor manejo de escalado)
        JPanel imagenPanel = new JPanel(new BorderLayout());
        imagenPanel.setOpaque(false);
        imagenPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Cargar imagen manteniendo proporciones
        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setVerticalAlignment(SwingConstants.CENTER);
        
        try {
            ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/Imagenes/imagenes/" + imagen));
            if (iconoOriginal.getIconWidth() > 0) {
                // Mantener proporciones originales de la imagen
                int anchoDeseado = 250;  // Ancho fijo
                int altoDeseado = 180;   // Alto fijo
                
                // Escalar manteniendo proporciones
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                    anchoDeseado, altoDeseado, Image.SCALE_SMOOTH
                );
                
                lblImagen.setIcon(new ImageIcon(imagenEscalada));
                lblImagen.setToolTipText("Imagen de " + nombre);
            } else {
                // Si no hay imagen, mostrar placeholder
                lblImagen.setText("🚗");
                lblImagen.setFont(new Font("Arial", Font.BOLD, 48));
                lblImagen.setForeground(Color.WHITE);
            }
        } catch (Exception e) {
            lblImagen.setText("❌");
            lblImagen.setFont(new Font("Arial", Font.BOLD, 48));
            lblImagen.setForeground(Color.RED);
            lblImagen.setToolTipText("Error cargando imagen: " + imagen);
        }
        
        imagenPanel.add(lblImagen, BorderLayout.CENTER);
        
        // Panel para el nombre
        JPanel nombrePanel = new JPanel();
        nombrePanel.setOpaque(false);
        nombrePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        JLabel lblNombre = new JLabel(nombre, SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 24));
        lblNombre.setForeground(Color.WHITE);
        nombrePanel.add(lblNombre);
        
        // Panel para la descripción
        JPanel descPanel = new JPanel();
        descPanel.setOpaque(false);
        descPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        // Usar JTextArea para texto con ajuste automático
        JTextArea txtDesc = new JTextArea(descripcion);
        txtDesc.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDesc.setForeground(new Color(220, 220, 220));
        txtDesc.setBackground(new Color(0, 0, 0, 0));
        txtDesc.setEditable(false);
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        txtDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtDesc.setAlignmentY(Component.CENTER_ALIGNMENT);
        txtDesc.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // Centrar el texto
        txtDesc.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        txtDesc.setFocusable(false);
        
        // Configurar el área de texto para que el texto esté centrado
        txtDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        descPanel.add(txtDesc);
        
        // Añadir componentes a la tarjeta
        tarjeta.add(imagenPanel, BorderLayout.CENTER);
        tarjeta.add(nombrePanel, BorderLayout.NORTH);
        tarjeta.add(descPanel, BorderLayout.SOUTH);
        
        // Efectos de hover
        tarjeta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Efecto de elevación
                tarjeta.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(colorBase.brighter().brighter(), 3),
                    BorderFactory.createEmptyBorder(17, 17, 17, 17)
                ));
                
                // Cambiar cursor
                tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                tarjeta.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                tarjeta.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                // Efecto de selección
                menuPrincipal.setVehiculo(tipo);
                
                // Mostrar confirmación
                mostrarConfirmacionSeleccion(nombre);
            }
        });
        
        return tarjeta;
    }
    
    private JButton crearBotonRegresar() {
        JButton btn = new JButton("← Regresar al Menú");
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(100, 150, 220));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 130, 200), 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(180, 45));
        
        // Efecto hover
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(120, 170, 240));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(100, 150, 220));
            }
        });
        
        btn.addActionListener(e -> cerrarSelector());
        
        return btn;
    }
    
    private void mostrarConfirmacionSeleccion(String vehiculoNombre) {
        // Diálogo de confirmación elegante
        JDialog dialog = new JDialog(this, "Vehículo Seleccionado", true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setUndecorated(true);
        
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(30, 30, 50),
                    0, getHeight(), new Color(50, 50, 80)
                );
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                g2.setColor(new Color(100, 255, 100));
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            }
        };
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel mensaje = new JLabel(
            "<html><center><font size='5' color='#64FF64'>✓ VEHÍCULO SELECCIONADO</font><br>" +
            "<font size='4' color='#FFFFFF'>" + vehiculoNombre + "</font><br>" +
            "<font size='3' color='#CCCCCC'>Regresando al menú...</font></center></html>",
            SwingConstants.CENTER
        );
        panel.add(mensaje, BorderLayout.CENTER);
        
        dialog.setContentPane(panel);
        dialog.setVisible(true);
        
        // Cerrar automáticamente después de 2 segundos
        Timer timer = new Timer(2000, e -> {
            dialog.dispose();
            cerrarSelector();
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    private void cerrarSelector() {
        dispose();
        menuPrincipal.setVisible(true);
    }
}