/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import java.awt.event.MouseEvent;

public class ControlMouse implements ControladorMovimiento {

    private final adaptadores.MovimientoJugador movimiento;

    public ControlMouse(MovimientoJugador movimiento) {
        this.movimiento = movimiento;
    }

    public void procesarMovimiento(MouseEvent e) {
        movimiento.establecerPosicion(e.getX(), e.getY());
    }

    @Override
    public void moverIzquierda() {}
    @Override
    public void moverDerecha() {}
    @Override
    public void moverArriba() {}
    @Override
    public void moverAbajo() {}
}

