package adaptadores;

public class MovimientoJugador {
    private int x, y;
    private final int minY, maxY; // Límites verticales
    private final int minX, maxX; // Límites horizontales
    
    public MovimientoJugador(int x, int y) {
        this.x = x;
        this.y = y;
        // Límites por defecto (se sobrescriben desde Vista)
        this.minX = 0;
        this.maxX = 1000;
        this.minY = 0;
        this.maxY = 1000;
    }
    
    // Constructor con límites personalizables
    public MovimientoJugador(int x, int y, int minX, int maxX, int minY, int maxY) {
        this.x = x;
        this.y = y;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }
    
    // Método para mover con desplazamiento específico
    public void mover(int dx, int dy) {
        int nuevaX = x + dx;
        int nuevaY = y + dy;
        
        // Aplicar límites
        if (nuevaX < minX) nuevaX = minX;
        if (nuevaX > maxX) nuevaX = maxX;
        if (nuevaY < minY) nuevaY = minY;
        if (nuevaY > maxY) nuevaY = maxY;
        
        this.x = nuevaX;
        this.y = nuevaY;
    }
    
    // Métodos individuales para cada dirección (para ControlTeclado)
    public void moverIzquierda(int paso) {
        mover(-paso, 0);
    }
    
    public void moverDerecha(int paso) {
        mover(paso, 0);
    }
    
    public void moverArriba(int paso) {
        mover(0, -paso);
    }
    
    public void moverAbajo(int paso) {
        mover(0, paso);
    }
    
    // Método para mover con mouse (también respeta límites)
    public void moverAMouse(int mouseY) {
        int nuevaY = mouseY;
        if (nuevaY < minY) nuevaY = minY;
        if (nuevaY > maxY) nuevaY = maxY;
        this.y = nuevaY;
    }
    
    // Getters y setters
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { 
        if (x >= minX && x <= maxX) {
            this.x = x;
        } else if (x < minX) {
            this.x = minX;
        } else {
            this.x = maxX;
        }
    }
    public void setY(int y) { 
        if (y >= minY && y <= maxY) {
            this.y = y;
        } else if (y < minY) {
            this.y = minY;
        } else {
            this.y = maxY;
        }
    }
}