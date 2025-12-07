/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        movimiento.mover(-5, 0);
    }

    @Override
    public void moverDerecha() {
        movimiento.mover(5, 0);
    }

    @Override
    public void moverArriba() {
        movimiento.mover(0, -20);
    }

    @Override
    public void moverAbajo() {
        movimiento.mover(0, 20);
    }
}

