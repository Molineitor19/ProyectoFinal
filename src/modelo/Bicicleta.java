/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Nelson Molina
 */
public class Bicicleta extends DesplazamientoDecorator {
    private String tipo;

    public Bicicleta(Desplazamiento desplazamiento, String tipo) {
        super(desplazamiento);
        this.tipo = tipo;
    }

    @Override
    public void mover() {
        super.mover();
        if(tipo.equalsIgnoreCase("lenta")) {
            System.out.println("Ahora se desplaza en bicicleta a velocidad baja.");
        } else {
            System.out.println("Ahora se desplaza en bicicleta a toda velocidad.");
        }
    }
}
