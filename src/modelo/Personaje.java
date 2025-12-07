/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Nelson Molina
 */
public class Personaje implements Desplazamiento {
    private String nombre;

    public Personaje(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void mover() {
        System.out.println(nombre + " se esta desplazando a pie.");
    }
}

