package Vista;

import adaptadores.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Vista extends JPanel implements ActionListener, KeyListener, MouseMotionListener {

    private Image Fondo;
    private Image[] framesJugador;
    private int frameIndex = 0;
    private Timer timer;
    private int delay;
    private int paso;
    private Random rand;
    private String tipoVehiculo;

    private MovimientoJugador jugador;
    private ControladorMovimiento controlador;
    private ControlTeclado controlTeclado;
    private ControlMouse controlMouse;
    private boolean usandoTeclado = true;

    private ArrayList<Enemigo> enemigos;

    public Vista(String tipoSeleccionado) {
    this.tipoVehiculo = tipoSeleccionado;

    setFocusable(true);
    addKeyListener(this);
    addMouseMotionListener(this);

    rand = new Random();

    Fondo = new ImageIcon(getClass().getResource("/Imagenes/imagenes/Fondo.jpg")).getImage();

    jugador = new MovimientoJugador(100, 300);
    controlTeclado = new ControlTeclado(jugador, paso);
    controlMouse = new ControlMouse(jugador);
    controlador = controlTeclado;

    enemigos = new ArrayList<>();

    cargarVehiculoJugador();
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
        int yRandom = 50 + rand.nextInt(350);
        return new Enemigo(800 + rand.nextInt(500), yRandom, tipoVehiculo);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Fondo, 0, 0, getWidth(), getHeight(), this);

        g.drawImage(framesJugador[frameIndex], jugador.getX(), jugador.getY(), 200, 120, this);

        for (Enemigo e : enemigos) {
            g.drawImage(e.getFrameActual(), e.getX(), e.getY(), 200, 120, this);
        }

        g.setColor(Color.WHITE);
        g.drawString("Modo: " + (usandoTeclado ? "Teclado" : "Mouse") + " (Presiona M para cambiar)", 20, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frameIndex = (frameIndex + 1) % framesJugador.length;

        moverEnemigos();
        detectarColisiones();

        repaint();
    }

    private void moverEnemigos() {
        for (Enemigo e : enemigos) {
            e.mover();
            if (e.getX() < -200) {
                enemigos.remove(e);
                enemigos.add(crearEnemigo());
                break;
            }
        }
    }

    private void detectarColisiones() {
        Rectangle rJugador = new Rectangle(jugador.getX(), jugador.getY(), 10, 10);

        for (Enemigo e : enemigos) {
            Rectangle rEnemigo = new Rectangle(e.getX(), e.getY(), 100, 70);
            if (rJugador.intersects(rEnemigo)) {

    timer.stop();  // detiene el juego mientras aparece el mensaje

    int opcion = JOptionPane.showOptionDialog(
            this,
            "¡HAS CHOCADO! Juego terminado.",
            "Colisión",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new Object[]{"Reiniciar"},
            "Reiniciar"
    );

    if (opcion == 0) {
        reiniciarJuego();
    }

    return; // Evita que siga detectando más colisiones
}

        }
    }

    
    private void reiniciarJuego() {

    // Reiniciar posición del jugador
    jugador.setX(100);
    jugador.setY(300);

    // Limpia los enemigos actuales
    enemigos.clear();

    // Genera nuevos enemigos
    generarEnemigos();

    // Reinicia animación
    frameIndex = 0;

    // Reinicia el timer del juego
    timer.start();

    repaint();
}

    
    
    
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_M) {
            usandoTeclado = !usandoTeclado;
            controlador = usandoTeclado ? controlTeclado : controlMouse;
        } else if (usandoTeclado) {
            controlTeclado.procesarTecla(e);
        }
        repaint();
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!usandoTeclado) {
            controlMouse.procesarMovimiento(e);
            repaint();
        }
    }

    @Override public void mouseDragged(MouseEvent e) {}
}

class Enemigo {
    private int x, y;
    private Image[] frames;
    private int frameIndex = 0;

    public Enemigo(int x, int y, String tipo) {
        this.x = x;
        this.y = y;
        cargarSprites(tipo);
    }

    private void cargarSprites(String tipo) {

    switch (tipo) {
        case "carro" -> frames = Vista.cargarFrames(
                "obstaculo2.png"
        );
        case "carroPro" -> frames = Vista.cargarFrames(
               "obstaculo1.png"
        );
        case "moto" -> frames = Vista.cargarFrames(
                "obstaculo3.png"
        );
        case "motoPro" -> frames = Vista.cargarFrames(
                "obstaculo5.png"
        );
        case "bicicleta" -> frames = Vista.cargarFrames(
                "obstaculo4.png"
        );
        case "bicicletaPro" -> frames = Vista.cargarFrames(
                "Ciclapro1.png", "Ciclapro2.png",
                "Ciclapro3.png", "Ciclapro4.png"
        );
    }
}


    public void mover() {
        x -= 8;
        frameIndex = (frameIndex + 1) % frames.length;
    }

    public Image getFrameActual() {
        return frames[frameIndex];
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
