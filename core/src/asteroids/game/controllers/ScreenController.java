package asteroids.game.controllers;

import asteroids.game.screens.GameScreen;
import asteroids.game.screens.Screen;

public class ScreenController {
    private Screen gameScreen;
    public static final int MENU = 0;
    public static final int GAME = 1;

    public ScreenController() {
        setScreen(GAME);
    }

    public void setScreen(int screen) {
        if (screen == GAME) {
            gameScreen = new GameScreen(this);
        }
    }

    public void update(float dt) {
        gameScreen.update(dt);
    }

    public void draw() {
        gameScreen.draw();
    }
}
