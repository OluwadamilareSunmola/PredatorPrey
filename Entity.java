import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Random;

public class Entity {
    private Circle circle;
    private double x, y;
    private boolean isPredator;
    private Random random = new Random();
    private double speed = 1.0;
    private boolean isAlive = true;

    public Entity(boolean isPredator) {
        this.isPredator = isPredator;
        this.circle = new Circle(5);
        this.x = random.nextDouble() * 800;
        this.y = random.nextDouble() * 600;
        this.circle.setCenterX(x);
        this.circle.setCenterY(y);

        if (isPredator) {
            this.circle.setFill(Color.RED);
        } else {
            this.circle.setFill(Color.GREEN);
        }
    }

    public Circle getCircle() {
        return circle;
    }

    public boolean isPredator() {
        return isPredator;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void die() {
        isAlive = false;
        circle.setVisible(false);
    }

    public void move(double speedMultiplier, Entity closestPrey) {
        if (!isAlive) return;

        if (isPredator && closestPrey != null && closestPrey.isAlive()) {
            // Move towards prey
            double dx = closestPrey.getX() - x;
            double dy = closestPrey.getY() - y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            if (distance < 10) {
                closestPrey.die(); // Predator eats the prey
            } else {
                x += (dx / distance) * speedMultiplier;
                y += (dy / distance) * speedMultiplier;
            }
        } else {
            // Simple random movement
            x += (random.nextDouble() - 0.5) * 10 * speedMultiplier;
            y += (random.nextDouble() - 0.5) * 10 * speedMultiplier;
        }

        if (x < 0) x = 800;
        if (x > 800) x = 0;
        if (y < 0) y = 600;
        if (y > 600) y = 0;

        circle.setCenterX(x);
        circle.setCenterY(y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
