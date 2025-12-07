package adaptadores;

import java.awt.Rectangle;

public class MovimientoJugador {
    private int x;
    private int y;
    private int width = 200;
    private int height = 120;

    public MovimientoJugador(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void mover(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void establecerPosicion(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setX(int x) { this.x = x; }
public void setY(int y) { this.y = y; }


    public int getX() { return x; }
    public int getY() { return y; }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
