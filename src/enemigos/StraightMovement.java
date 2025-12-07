package enemigos;

public class StraightMovement implements EnemyMovementStrategy {
    @Override
    public void move(Enemy e) {
        e.setX(e.getX() - e.getSpeed());
    }
}
