package enemigos;

import controlador.ImageFacade;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class EnemySpawner {
    private final List<Enemy> enemies = new ArrayList<>();
    private final Random rand = new Random();
    private int spawnCooldown = 0;

    public List<Enemy> getEnemies() { return enemies; }

    public void update(int panelWidth, int panelHeight) {
        if (spawnCooldown <= 0) {
            spawn(panelWidth, panelHeight);
            spawnCooldown = 60 + rand.nextInt(90);
        } else spawnCooldown--;

        Iterator<Enemy> it = enemies.iterator();
        while (it.hasNext()) {
            Enemy e = it.next();
            e.update();
            if (e.getX() + 200 < 0) { 
                it.remove();
            }
        }
    }

    public void spawn(int panelWidth, int panelHeight) {
        int y = 50 + rand.nextInt(Math.max(1, panelHeight - 150));
        Image sprite = ImageFacade.load("/Imagenes/imagenes/obstaculo1.png");
        EnemyMovementStrategy strat = rand.nextBoolean() ? new StraightMovement() : new ZigZagMovement();
        Enemy enemy = new Enemy(panelWidth + 100, y, sprite, strat);
        enemy.setSpeed(3 + rand.nextInt(4));
        enemies.add(enemy);
    }
}
