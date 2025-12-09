package adaptadores;

import java.awt.event.MouseEvent;

public class ControlMouse {
    private MovimientoJugador jugador;
    
    public ControlMouse(MovimientoJugador jugador) {
        this.jugador = jugador;
    }
    
    public void procesarMovimiento(MouseEvent e) {
        // Solo mueve verticalmente (como en el juego original)
        // Ajustamos la posición del mouse para que funcione correctamente
        int mouseY = e.getY();
        
        // Ajuste para que el vehículo siga mejor el cursor
        // Restamos una compensación para que sea más preciso
        jugador.moverAMouse(mouseY - 60);
    }
}