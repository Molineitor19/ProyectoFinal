package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

class PanelFondo extends JPanel {
    private Image img;

    public PanelFondo() {
        try {
            img = new ImageIcon(getClass().getResource("/Imagenes/imagenes/FondoMenu.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen de fondo: " + e.getMessage());
            img = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        if (img != null) {
            // Dibujar imagen de fondo escalada
            g2.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        } else {
            // Fondo alternativo si no hay imagen
            GradientPaint gradient = new GradientPaint(
                0, 0, new Color(20, 20, 40),
                0, getHeight(), new Color(10, 10, 25)
            );
            g2.setPaint(gradient);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
        
        // Capa oscura semi-transparente para mejor contraste
        g2.setColor(new Color(0, 0, 0, 100));
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}

public class MenuPrincipal extends JFrame {

    private JButton btnIniciar, btnVehiculo, btnSalir;
    private String vehiculoSeleccionado = "carro";
    private PanelFondo fondo;
    private JLabel lblInfoVehiculo; // Referencia al label para actualizarlo

    public MenuPrincipal() {
        setTitle("Menú ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setSize(1200, 700);
        setLocationRelativeTo(null);
        
        // Crear panel de fondo
        fondo = new PanelFondo();
        fondo.setLayout(new GridBagLayout());
        setContentPane(fondo);
        
        // Crear panel central con contenido
        JPanel contenidoPanel = crearPanelContenido();
        
        // Añadir al centro
        fondo.add(contenidoPanel);
        
        setVisible(true);
    }
    
    private JPanel crearPanelContenido() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        // Título
        JLabel titulo = new JLabel("MENÚ");
        titulo.setFont(new Font("Arial", Font.BOLD, 48));
        titulo.setForeground(new Color(100, 200, 255));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Subtítulo
        JLabel subtitulo = new JLabel("NO CHOQUES");
        subtitulo.setFont(new Font("Arial", Font.ITALIC, 18));
        subtitulo.setForeground(new Color(200, 220, 255));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Panel para botones con fondo semi-transparente
        JPanel botonesPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo semi-transparente con bordes redondeados
                g2.setColor(new Color(30, 30, 50, 200));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                // Borde
                g2.setColor(new Color(100, 150, 255, 100));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            }
        };
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.Y_AXIS));
        botonesPanel.setOpaque(false);
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        botonesPanel.setMaximumSize(new Dimension(400, 350));
        
        // Crear botones
        btnIniciar = crearBotonElegante("Iniciar Juego", new Color(80, 180, 80));
        btnVehiculo = crearBotonElegante("Elegir Vehículo", new Color(80, 150, 220));
        btnSalir = crearBotonElegante("Salir", new Color(220, 80, 80));
        
        // Configurar acciones
        btnIniciar.addActionListener(e -> iniciarJuego());
        btnVehiculo.addActionListener(e -> abrirSelectorVehiculo());
        btnSalir.addActionListener(e -> System.exit(0));
        
        // Añadir elementos al panel
        panel.add(Box.createVerticalStrut(20));
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(subtitulo);
        panel.add(Box.createVerticalStrut(30));
        
        // Añadir botones al panel de botones
        botonesPanel.add(btnIniciar);
        botonesPanel.add(Box.createVerticalStrut(15));
        botonesPanel.add(btnVehiculo);
        botonesPanel.add(Box.createVerticalStrut(15));
        botonesPanel.add(btnSalir);
        botonesPanel.add(Box.createVerticalStrut(20));
        
        // Información del vehículo
        lblInfoVehiculo = new JLabel("Vehículo actual: " + vehiculoSeleccionado.toUpperCase());
        lblInfoVehiculo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblInfoVehiculo.setForeground(new Color(200, 220, 255));
        lblInfoVehiculo.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonesPanel.add(lblInfoVehiculo);
        
        // Añadir panel de botones al panel principal
        panel.add(botonesPanel);
        
        return panel;
    }
    
    private JButton crearBotonElegante(String texto, Color colorBase) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Determinar colores según estado
                Color colorTop, colorBottom;
                if (getModel().isPressed()) {
                    colorTop = colorBase.darker();
                    colorBottom = colorBase.darker().darker();
                } else if (getModel().isRollover()) {
                    colorTop = colorBase.brighter();
                    colorBottom = colorBase;
                } else {
                    colorTop = colorBase;
                    colorBottom = colorBase.darker();
                }
                
                // Fondo con gradiente
                GradientPaint gradient = new GradientPaint(
                    0, 0, colorTop,
                    0, getHeight(), colorBottom
                );
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                
                // Borde
                g2.setStroke(new BasicStroke(2));
                g2.setColor(colorBase.darker().darker());
                g2.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 10, 10);
                
                // Texto
                g2.setFont(new Font("Arial", Font.BOLD, 18));
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                
                // Color del texto según estado
                g2.setColor(getModel().isPressed() ? Color.WHITE : Color.BLACK);
                g2.drawString(getText(), x, y);
                
                g2.dispose();
            }
        };
        
        boton.setPreferredSize(new Dimension(300, 50));
        boton.setMaximumSize(new Dimension(300, 50));
        boton.setMinimumSize(new Dimension(300, 50));
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        return boton;
    }
    
    public static void mostrarMenu() {
        SwingUtilities.invokeLater(() -> new MenuPrincipal());
    }
    
    private void iniciarJuego() {
        System.out.println("Iniciando juego con vehículo: " + vehiculoSeleccionado);
        
        // Cerrar menú
        dispose();
        
        // Abrir juego
        SwingUtilities.invokeLater(() -> {
            JFrame ventanaJuego = new JFrame("Juego - " + vehiculoSeleccionado.toUpperCase());
            Vista panelJuego = new Vista(vehiculoSeleccionado);
            
            ventanaJuego.add(panelJuego);
            
            // Pantalla completa
            ventanaJuego.setUndecorated(true);
            ventanaJuego.setExtendedState(JFrame.MAXIMIZED_BOTH);
            
            ventanaJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaJuego.setVisible(true);
            
            // Enfocar el panel para que capte las teclas
            panelJuego.requestFocusInWindow();
        });
    }
    
    private void abrirSelectorVehiculo() {
        System.out.println("Abriendo selector de vehículo...");
        this.setVisible(false); // Ocultar menú
        new SelectorVehiculo(this); // Pasar referencia de MenuPrincipal
    }
    
    // Método CORREGIDO para actualizar el vehículo seleccionado
    public void setVehiculo(String tipoVehiculo) {
        System.out.println("Actualizando vehículo en MenuPrincipal a: " + tipoVehiculo);
        
        // Actualizar variable
        this.vehiculoSeleccionado = tipoVehiculo;
        
        // Actualizar texto del label
        if (lblInfoVehiculo != null) {
            String nombreMostrar = tipoVehiculo.toUpperCase();
            // Mejorar nombres para mostrar
            if (tipoVehiculo.equals("carro")) nombreMostrar = "CARRO";
            else if (tipoVehiculo.equals("carroPro")) nombreMostrar = "CARRO PRO";
            else if (tipoVehiculo.equals("moto")) nombreMostrar = "MOTO";
            else if (tipoVehiculo.equals("motoPro")) nombreMostrar = "MOTO PRO";
            else if (tipoVehiculo.equals("bicicleta")) nombreMostrar = "BICICLETA";
            else if (tipoVehiculo.equals("bicicletaPro")) nombreMostrar = "BICICLETA PRO";
            
            lblInfoVehiculo.setText("Vehículo actual: " + nombreMostrar);
            lblInfoVehiculo.repaint();
        }
        
        // Forzar repintado
        this.revalidate();
        this.repaint();
        
        System.out.println("Vehículo actualizado correctamente a: " + vehiculoSeleccionado);
    }
    
    // Método para obtener el vehículo seleccionado
    public String getVehiculoSeleccionado() {
        return vehiculoSeleccionado;
    }
    
    public static void main(String[] args) {
        mostrarMenu();
    }
}