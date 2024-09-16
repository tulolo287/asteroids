package asteroids.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import asteroids.game.controllers.CustomKeys;
import asteroids.game.controllers.InputController;
import asteroids.game.controllers.ScreenController;

public class AsteroidsGame extends ApplicationAdapter {
    public static float WIDTH;
    public static float HEIGHT;
    public static Vector2 centerScreen;
    private ScreenController screenController;

    @Override
    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        centerScreen = new Vector2(WIDTH / 2, HEIGHT / 2);
        Gdx.input.setInputProcessor(new InputController());
        screenController = new ScreenController();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        screenController.update(Gdx.graphics.getDeltaTime());
        screenController.draw();
        CustomKeys.update();
    }

    @Override
    public void dispose() {
    }
}
