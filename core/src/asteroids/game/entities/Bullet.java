package asteroids.game.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends Entity {
    private float bulletTime;
    private boolean delete;

    public Bullet(float x, float y, float angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.width = 12;
        this.height = 2;
        acceleration = 5;
    }

    public void update(float dt, float playerDx, float playerDy) {
        dx += MathUtils.cos(this.angle) * acceleration + playerDx;
        dy += MathUtils.sin(this.angle) * acceleration + playerDy;
        x += dx * dt;
        y += dy * dt;
    }

    public void draw(ShapeRenderer shapeRenderer, float dx, float dy) {
        shapeRenderer.circle(x, y, 2);
    }
}
