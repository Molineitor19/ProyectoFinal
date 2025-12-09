package adaptadores;

import java.awt.event.KeyEvent;

public class ControlTeclado implements ControladorMovimiento {

    private final MovimientoJugador movimiento;
    private int paso;

    public ControlTeclado(MovimientoJugador movimiento, int paso) {
        this.movimiento = movimiento;
        this.paso = paso;
    }

    public void procesarTecla(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> moverIzquierda();
            case KeyEvent.VK_RIGHT -> moverDerecha();
            case KeyEvent.VK_UP -> moverArriba();
            case KeyEvent.VK_DOWN -> moverAbajo();
        }
    }

    @Override
    public void moverIzquierda() {
        movimiento.moverIzquierda(paso);
    }

    @Override
    public void moverDerecha() {
        movimiento.moverDerecha(paso);
    }

    @Override
    public void moverArriba() {
        movimiento.moverArriba(paso);
    }

    @Override
    public void moverAbajo() {
        movimiento.moverAbajo(paso);
    }
    
    // Método para actualizar el paso si cambia
    public void setPaso(int nuevoPaso) {
        this.paso = nuevoPaso;
    }
}