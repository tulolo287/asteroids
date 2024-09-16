package asteroids.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

import asteroids.game.AsteroidsGame;
import asteroids.game.screens.GameScreen;

public class Player extends Entity {

    private boolean left;
    private boolean right;
    private boolean forward;
    private float[] flameX;
    private float[] flameY;
    private float flameSize;
    private ArrayList<Bullet> bullets;

    public Player() {

        this.bullets = new ArrayList<Bullet>();
        x = AsteroidsGame.centerScreen.x;
        y = AsteroidsGame.centerScreen.y;

        rotationSpeed = 4;
        speed = 1;
        acceleration = 100;
        deceleration = 3;
        angle = MathUtils.PI / 2;

        shapeX = new float[4];
        shapeY = new float[4];
        flameX = new float[3];
        flameY = new float[3];
        flameSize = .2f;
    }

    public void createShip() {
        shapeX[0] = x + MathUtils.cos(angle) * 8;
        shapeY[0] = y + MathUtils.sin(angle) * 8;

        shapeX[1] = x + MathUtils.cos(angle - 4 * MathUtils.PI / 5) * 8;
        shapeY[1] = y + MathUtils.sin(angle - 4 * MathUtils.PI / 5) * 8;

        shapeX[2] = x + MathUtils.cos(angle + MathUtils.PI) * 5;
        shapeY[2] = y + MathUtils.sin(angle + MathUtils.PI) * 5;

        shapeX[3] = x + MathUtils.cos(angle + 4 * MathUtils.PI / 5) * 8;
        shapeY[3] = y + MathUtils.sin(angle + 4 * MathUtils.PI / 5) * 8;
    }

    public void createShipFlame() {
        flameX[0] = x + MathUtils.cos(angle - 5 * MathUtils.PI / 6) * 10;
        flameY[0] = y + MathUtils.sin(angle - 5 * MathUtils.PI / 6) * 10;

        flameX[1] = x + MathUtils.cos(angle + MathUtils.PI) - dx * flameSize;
        flameY[1] = y + MathUtils.sin(angle + MathUtils.PI) - dy * flameSize;

        flameX[2] = x + MathUtils.cos(angle + 5 * MathUtils.PI / 6) * 10;
        flameY[2] = y + MathUtils.sin(angle + 5 * MathUtils.PI / 6) * 10;
    }

    public void turnLeft(boolean flag) {
        left = flag;
    }

    public void turnRight(boolean flag) {
        right = flag;
    }

    public void moveForward(boolean flag) {
        forward = flag;
    }

    public void fire(boolean flag) {
        if (flag) {
            this.bullets.add(new Bullet(x, y, angle));
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void update(float dt) {
        float accelY = Gdx.input.getAccelerometerY();
        float speed = (float) Math.sqrt(dx * dx + dy * dy);
        double test = (float) Math.sqrt(16);
        if (left) {
            angle += rotationSpeed * dt;
        }
        if (right) {
            angle -= rotationSpeed * dt;
        }
        if (forward) {
            dx = acceleration * MathUtils.cos(angle);
            dy = acceleration * MathUtils.sin(angle);
        }

        if (speed > 0 && !forward) {
            //acceleration = 0;
            dx = deceleration * MathUtils.cos(angle);
            dy = deceleration * MathUtils.sin(angle);
            // dx -= MathUtils.cos(angle) * deceler2ation;
            //  dy -= MathUtils.sin(angle) * deceleration;
            //dx -= (dx / speed) * deceleration;
            //dy -= (dy / speed) * deceleration;
        }
        System.out.println(speed);
        //angle -= rotationSpeed * accelY * dt;
        // dx += MathUtils.cos(angle) * acceleration;
        //dy += MathUtils.sin(angle) * acceleration;

        x += dx * dt;
        y += dy * dt;

        createShip();
        createShipFlame();

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update(dt, dx, dy);
        }
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setProjectionMatrix(GameScreen.cam.combined);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0, j = shapeX.length - 1; i < shapeX.length; j = i++) {
            shapeRenderer.line(shapeX[i], shapeY[i], shapeX[j], shapeY[j]);
        }
        if (forward) {
            for (int i = 0, j = flameX.length - 1; i < flameX.length; j = i++) {
                shapeRenderer.line(flameX[i], flameY[i], flameX[j], flameY[j]);
            }
        }
        if (!bullets.isEmpty()) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).draw(shapeRenderer, x, y);
            }
        }
        shapeRenderer.end();
    }
}
