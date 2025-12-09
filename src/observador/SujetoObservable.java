/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observador;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nelson Molina Ramos
 */
public class SujetoObservable {
    private List<Observador> observadores = new ArrayList<>();
    
    public void agregarObservador(Observador o) {
        observadores.add(o);
    }
    
    public void eliminarObservador(Observador o) {
        observadores.remove(o);
    }
    
    public void notificar(String evento, Object dato) {
        for (Observador o : observadores) {
            o.actualizar(evento, dato);
        }
    }
}
