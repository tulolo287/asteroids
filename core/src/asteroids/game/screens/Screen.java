package asteroids.game.screens;

import asteroids.game.controllers.ScreenController;

public abstract class Screen {
    protected ScreenController screenController;

    protected Screen(ScreenController screenController) {
        this.screenController = screenController;
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();
}
