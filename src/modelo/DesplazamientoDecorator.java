/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Nelson Molina
 */
public abstract class DesplazamientoDecorator implements Desplazamiento {
    protected Desplazamiento desplazamiento;

    public DesplazamientoDecorator(Desplazamiento desplazamiento) {
        this.desplazamiento = desplazamiento;
    }

    @Override
    public void mover() {
        desplazamiento.mover();
    }
}

