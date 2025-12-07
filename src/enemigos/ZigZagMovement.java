package enemigos;

public class ZigZagMovement implements EnemyMovementStrategy {
    private int phase = 0;
    @Override
    public void move(Enemy e) {
        e.setX(e.getX() - e.getSpeed());
        if (phase % 40 < 20) e.setY(e.getY() - 2);
        else e.setY(e.getY() + 2);
        phase++;
    }
}
