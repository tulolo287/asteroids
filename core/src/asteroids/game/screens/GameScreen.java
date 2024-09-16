package asteroids.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

import asteroids.game.AsteroidsGame;
import asteroids.game.controllers.CustomKeys;
import asteroids.game.controllers.ScreenController;
import asteroids.game.entities.Bullet;
import asteroids.game.entities.Player;

public class GameScreen extends Screen {
    private ShapeRenderer shapeRenderer;
    private Player player;
    private ArrayList<Bullet> bullets;
    public static OrthographicCamera cam;
    private Array<Vector2> stars;

    public GameScreen(ScreenController screenController) {
        super(screenController);
        stars = new Array<>();
        int camWidth = 800;
        cam = new OrthographicCamera(camWidth, camWidth * (AsteroidsGame.HEIGHT / AsteroidsGame.WIDTH));
        FitViewport fitViewport = new FitViewport(AsteroidsGame.WIDTH, AsteroidsGame.HEIGHT, cam);
        //  cam.setToOrtho(false, fitViewport.getScreenWidth(), fitViewport.getScreenHeight());
        init();
    }

    @Override
    public void init() {
        player = new Player();
        shapeRenderer = new ShapeRenderer();
        for (int i = 0; i < 1000; i++) {
            stars.add(new Vector2(MathUtils.random(1, AsteroidsGame.WIDTH * 10), MathUtils.random(1, AsteroidsGame.HEIGHT * 10)));
        }

    }

    @Override
    public void update(float dt) {
        int deviceAngle = Gdx.input.getRotation();

        player.update(dt);
        GameScreen.cam.position.set(player.getX(), player.getY(), 0);
        GameScreen.cam.update();
        handleInput();
    }

    @Override
    public void draw() {
        drawStars();
        player.draw(shapeRenderer);
    }

    private void drawStars() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < stars.size; i++) {
            shapeRenderer.circle(stars.get(i).x, stars.get(i).y, 2);
        }
        shapeRenderer.end();
    }

    @Override
    public void handleInput() {
        player.turnLeft(CustomKeys.isKeyDown(CustomKeys.LEFT));
        player.turnRight(CustomKeys.isKeyDown(CustomKeys.RIGHT));
        player.moveForward(CustomKeys.isKeyDown(CustomKeys.UP));
        player.fire(CustomKeys.isKeyPressed(CustomKeys.SPACE));
    }

    @Override
    public void dispose() {

    }
}
