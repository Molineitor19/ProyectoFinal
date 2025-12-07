/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Nelson Molina
 */
public class Moto extends DesplazamientoDecorator {
    private String cilindraje;

    public Moto(Desplazamiento desplazamiento, String cilindraje) {
        super(desplazamiento);
        this.cilindraje = cilindraje;
    }

    @Override
    public void mover() {
        super.mover();
        if(cilindraje.equalsIgnoreCase("bajo")) {
            System.out.println("Ahora se desplaza en moto de bajo cilindraje.");
        } else {
            System.out.println("Ahora se desplaza en moto de alto cilindraje.");
        }
    }
}
