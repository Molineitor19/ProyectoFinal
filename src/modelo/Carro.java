/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Nelson Molina
 */
public class Carro extends DesplazamientoDecorator {
    private String tipo;

    public Carro(Desplazamiento desplazamiento, String tipo) {
        super(desplazamiento);
        this.tipo = tipo;
    }

    @Override
    public void mover() {
        super.mover();
        if(tipo.equalsIgnoreCase("normal")) {
            System.out.println("Ahora se desplaza en un carro normal.");
        } else {
            System.out.println("Ahora se desplaza en un carro de lujo.");
        }
    }
}

