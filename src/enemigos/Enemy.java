package enemigos;

import java.awt.Image;
import java.awt.Rectangle;

public class Enemy implements Cloneable {
    private int x, y;
    private int width = 150, height = 80;
    private int speed = 4;
    private Image sprite;
    private EnemyMovementStrategy strategy;

    public Enemy(int x, int y, Image sprite, EnemyMovementStrategy strategy) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.strategy = strategy;
    }

    public void update() {
        strategy.move(this);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public int getSpeed() { return speed; }
    public void setSpeed(int s) { speed = s; }
    public Image getSprite() { return sprite; }
    public void setStrategy(EnemyMovementStrategy s) { this.strategy = s; }

    @Override
    public Enemy clone() {
        try {
            return (Enemy) super.clone();
        } catch (CloneNotSupportedException ex) {
            return new Enemy(x,y,sprite,strategy);
        }
    }
}
