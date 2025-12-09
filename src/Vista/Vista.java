package Vista;

import adaptadores.*;
import controlador.GameManager;
import observador.Observador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Vista extends JPanel implements ActionListener, KeyListener, MouseMotionListener, Observador {

    private Image Fondo;
    private Image[] framesJugador;
    private int frameIndex = 0;
    private Timer timer;
    private int delay;
    private int paso;
    private Random rand;
    private String tipoVehiculo;
    
    // Variables para puntuación y vidas
    private int puntuacion = 0;
    private int vidas = 1;

    private MovimientoJugador jugador;
    private ControlTeclado controlTeclado;
    private ControlMouse controlMouse;
    private boolean usandoTeclado = true;

    private ArrayList<Enemigo> enemigos;
    private GameManager gameManager;
    
    // CONSTANTES PARA LÍMITES
    private static final int LIMITE_SUPERIOR = 290;
    private static final int LIMITE_INFERIOR = 600;
    private static final int LIMITE_IZQUIERDO = 50;
    private static final int LIMITE_DERECHO = 800;
    
    // Límites para enemigos
    private static final int ENEMIGO_MIN_Y = 290;
    private static final int ENEMIGO_MAX_Y = 520;
    private static final int ENEMIGO_MIN_X = 1000; // Aparecen desde la derecha
    private static final int ENEMIGO_MAX_X = 1500; // Máxima posición inicial
    
    // Distancia mínima entre enemigos
    private static final int DISTANCIA_MINIMA_ENEMIGOS = 100;

    public Vista(String tipoSeleccionado) {
        this.tipoVehiculo = tipoSeleccionado;
        
        // Obtener instancia del GameManager
        gameManager = GameManager.getInstance();
        // Registrar esta vista como observadora
        gameManager.agregarObservador(this);

        setFocusable(true);
        addKeyListener(this);
        addMouseMotionListener(this);

        rand = new Random();

        Fondo = new ImageIcon(getClass().getResource("/Imagenes/imagenes/Fondo.jpg")).getImage();

        // Inicializa jugador con límites
        jugador = new MovimientoJugador(100, 300, LIMITE_IZQUIERDO, LIMITE_DERECHO, 
                                       LIMITE_SUPERIOR, LIMITE_INFERIOR);
        
        // Los controles se inicializan después de cargar el vehículo para tener el paso correcto
        // Primero cargamos el vehículo
        cargarVehiculoJugador();
        
        // Luego inicializamos los controles con el paso correcto
        controlTeclado = new ControlTeclado(jugador, paso);
        controlMouse = new ControlMouse(jugador);

        enemigos = new ArrayList<>();

        generarEnemigos();

        timer = new Timer(delay, this);
        timer.start();
    }

    private void cargarVehiculoJugador() {
        frameIndex = 0;

        switch (tipoVehiculo) {
            case "carro" -> {
                framesJugador = cargarFrames(
                    "carronormal1.png","carronormal2.png"
                );
                delay = 10;
                paso = 9;
            }
            case "carroPro" -> {
                framesJugador = cargarFrames(
                   "carrotuneado2.png","carrotuneado3.png"
                );
                delay = 1;
                paso = 80;
            }
            case "moto" -> {
                framesJugador = cargarFrames(
                    "motonormal1.png","motonormal2.png"
                );
                delay = 15;
                paso = 7;
            }
            case "motoPro" -> {
                framesJugador = cargarFrames(
                    "motopro1.png","motopro2.png"
                );
                delay = 12;
                paso = 9;
            }
            case "bicicleta" -> {
                framesJugador = cargarFrames(
                    "Ciclanormal1.png","Ciclanormal2.png","Ciclanormal3.png","Ciclanormal4.png"
                );
                delay = 25;
                paso = 5;
            }
            case "bicicletaPro" -> {
                framesJugador = cargarFrames(
                    "Ciclapro1.png","Ciclapro2.png","Ciclapro3.png","Ciclapro4.png"
                );
                delay = 20;
                paso = 7;
            }
        }
    }

    public static Image[] cargarFrames(String... nombres) {
        Image[] imgs = new Image[nombres.length];
        for (int i = 0; i < nombres.length; i++) {
            imgs[i] = new ImageIcon(Vista.class.getResource("/Imagenes/imagenes/" + nombres[i])).getImage();
        }
        return imgs;
    }

    private void generarEnemigos() {
        enemigos.clear();
        for (int i = 0; i < 2; i++) {
            enemigos.add(crearEnemigo());
        }
    }

    private Enemigo crearEnemigo() {
        int intentos = 0;
        int maxIntentos = 20; // Para evitar bucle infinito
        
        while (intentos < maxIntentos) {
            int yRandom = ENEMIGO_MIN_Y + rand.nextInt(ENEMIGO_MAX_Y - ENEMIGO_MIN_Y);
            int xRandom = ENEMIGO_MIN_X + rand.nextInt(ENEMIGO_MAX_X - ENEMIGO_MIN_X);
            
            // Verificar que no se superponga con otros enemigos
            boolean colisiona = false;
            
            for (Enemigo e : enemigos) {
                // Calcular distancia entre enemigos
                double distancia = Math.sqrt(
                    Math.pow(e.getX() - xRandom, 2) + 
                    Math.pow(e.getY() - yRandom, 2)
                );
                
                if (distancia < DISTANCIA_MINIMA_ENEMIGOS) {
                    colisiona = true;
                    break;
                }
            }
            
            if (!colisiona) {
                return new Enemigo(xRandom, yRandom, tipoVehiculo);
            }
            
            intentos++;
        }
        
        // Si después de muchos intentos no encuentra posición, poner en posición aleatoria
        int yRandom = ENEMIGO_MIN_Y + rand.nextInt(ENEMIGO_MAX_Y - ENEMIGO_MIN_Y);
        int xRandom = ENEMIGO_MIN_X + rand.nextInt(ENEMIGO_MAX_X - ENEMIGO_MIN_X);
        return new Enemigo(xRandom, yRandom, tipoVehiculo);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Fondo, 0, 0, getWidth(), getHeight(), this);

        g.drawImage(framesJugador[frameIndex], jugador.getX(), jugador.getY(), 200, 120, this);

        for (Enemigo e : enemigos) {
            g.drawImage(e.getFrameActual(), e.getX(), e.getY(), 200, 120, this);
        }

        // Dibujar información del juego
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Modo: " + (usandoTeclado ? "Teclado" : "Mouse") + " (Presiona M para cambiar)", 20, 30);
        g.drawString("Puntuación: " + puntuacion, 20, 55);
        g.drawString("Vidas: " + vidas, 20, 80);
        
        // (OPCIONAL) Dibujar límites para debug - cambia a true para ver los límites
        if (false) {
            g.setColor(new Color(255, 0, 0, 100));
            g.drawRect(LIMITE_IZQUIERDO, LIMITE_SUPERIOR, 
                      LIMITE_DERECHO - LIMITE_IZQUIERDO, 
                      LIMITE_INFERIOR - LIMITE_SUPERIOR);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frameIndex = (frameIndex + 1) % framesJugador.length;

        moverEnemigos();
        detectarColisiones();

        repaint();
    }

    private void moverEnemigos() {
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo enemigo = enemigos.get(i);
            enemigo.mover();
            
            if (enemigo.getX() < -200) {
                // Cuando un enemigo sale de pantalla (es esquivado)
                gameManager.enemigoEsquivado(); // ¡Suma puntos!
                
                enemigos.remove(i);
                enemigos.add(crearEnemigo());
                i--; // Ajustar índice después de remover
            }
        }
    }

    private void detectarColisiones() {
        Rectangle rJugador = new Rectangle(jugador.getX(), jugador.getY(), 10, 10);

        for (Enemigo e : enemigos) {
            Rectangle rEnemigo = new Rectangle(e.getX(), e.getY(), 100, 70);
            if (rJugador.intersects(rEnemigo)) {
                gameManager.setGameOver(true);
                timer.stop();
                
                // Crear el diálogo personalizado
                GameOverDialog dialog = new GameOverDialog((JFrame) SwingUtilities.getWindowAncestor(this), puntuacion);
                dialog.setVisible(true);
                
                int opcion = dialog.getOpcionSeleccionada();
                
                switch (opcion) {
                    case 0: // Reiniciar
                        reiniciarJuego();
                        break;
                    case 1: // Volver al Menú
                        volverAlMenu();
                        break;
                    case 2: // Salir
                        System.exit(0);
                        break;
                    default: // Si cierra la ventana
                        System.exit(0);
                        break;
                }
                return;
            }
        }
    }

    private void volverAlMenu() {
        Window ventana = SwingUtilities.getWindowAncestor(this);
        if (ventana != null) {
            ventana.dispose();
        }
        
        // Ejecutar en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }
    
    private void reiniciarJuego() {
        gameManager.setGameOver(false);
        gameManager.resetScore();
        
        jugador.setX(100);
        jugador.setY(300);
        enemigos.clear();
        generarEnemigos();
        frameIndex = 0;
        timer.start();
        repaint();
    }

    @Override
   
public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_M) {
        usandoTeclado = !usandoTeclado;
        repaint();
    } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        // Salir de pantalla completa con ESC
        salirDePantallaCompleta();
    } else if (usandoTeclado) {
        controlTeclado.procesarTecla(e);
        repaint();
    }
}

private void salirDePantallaCompleta() {
    Window ventana = SwingUtilities.getWindowAncestor(this);
    if (ventana instanceof JFrame) {
        JFrame frame = (JFrame) ventana;
        
        // Preguntar si quiere salir
        int opcion = JOptionPane.showConfirmDialog(
            this,
            "¿Deseas salir de la pantalla completa?",
            "Salir de pantalla completa",
            JOptionPane.YES_NO_OPTION
        );
        
        if (opcion == JOptionPane.YES_OPTION) {
            frame.dispose(); // Cerrar ventana actual
            
            // Volver al menú principal
            SwingUtilities.invokeLater(() -> {
                new MenuPrincipal().setVisible(true);
            });
        }
    }
}

    @Override 
    public void keyReleased(KeyEvent e) {}
    
    @Override 
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!usandoTeclado) {
            controlMouse.procesarMovimiento(e);
            repaint();
        }
    }

    @Override 
    public void mouseDragged(MouseEvent e) {}
    
    // ===== IMPLEMENTACIÓN DE OBSERVADOR =====
    @Override
    public void actualizar(String evento, Object dato) {
        switch (evento) {
            case "puntos":
                puntuacion = (int) dato;
                break;
            case "vidas":
                vidas = (int) dato;
                break;
            case "gameOver":
                // Podrías manejar gameOver aquí si quieres
                break;
        }
        repaint(); // Vuelve a dibujar para mostrar cambios
    }
}

// Clase Enemigo mejorada con límites
class Enemigo {
    private int x, y;
    private Image[] frames;
    private int frameIndex = 0;
    private static final int VELOCIDAD = 8;
    private static final int LIMITE_IZQUIERDO = -200; // Cuando desaparece

    public Enemigo(int x, int y, String tipo) {
        this.x = x;
        this.y = y;
        cargarSprites(tipo);
    }

    private void cargarSprites(String tipo) {
        switch (tipo) {
            case "carro" -> frames = Vista.cargarFrames("obstaculo2.png");
            case "carroPro" -> frames = Vista.cargarFrames("obstaculo1.png");
            case "moto" -> frames = Vista.cargarFrames("obstaculo3.png");
            case "motoPro" -> frames = Vista.cargarFrames("obstaculo5.png");
            case "bicicleta" -> frames = Vista.cargarFrames("obstaculo4.png");
            case "bicicletaPro" -> frames = Vista.cargarFrames(
                "Ciclapro1.png", "Ciclapro2.png",
                "Ciclapro3.png", "Ciclapro4.png"
            );
        }
    }

    public void mover() {
        x -= VELOCIDAD;
        frameIndex = (frameIndex + 1) % frames.length;
    }
    
    public boolean estaFueraDePantalla() {
        return x < LIMITE_IZQUIERDO;
    }

    public Image getFrameActual() {
        return frames[frameIndex];
    }

    public int getX() { return x; }
    public int getY() { return y; }
}