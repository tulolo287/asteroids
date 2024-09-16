package asteroids.game.controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

public class InputController extends InputAdapter implements InputProcessor {
    public boolean keyUp(int key) {
        if(key == Input.Keys.UP) {
            CustomKeys.setKey(CustomKeys.UP, false);
        }
        if(key == Input.Keys.LEFT) {
            CustomKeys.setKey(CustomKeys.LEFT, false);
        }
        if(key == Input.Keys.RIGHT) {
            CustomKeys.setKey(CustomKeys.RIGHT, false);
        }
        if(key == Input.Keys.SPACE) {
            CustomKeys.setKey(CustomKeys.SPACE, false);
        }
        return true;
    }
    public boolean keyDown(int key) {
        if(key == Input.Keys.UP) {
            CustomKeys.setKey(CustomKeys.UP, true);
        }
        if(key == Input.Keys.LEFT) {
            CustomKeys.setKey(CustomKeys.LEFT, true);
        }
        if(key == Input.Keys.RIGHT) {
            CustomKeys.setKey(CustomKeys.RIGHT, true);
        }
        if(key == Input.Keys.SPACE) {
            CustomKeys.setKey(CustomKeys.SPACE, true);
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        CustomKeys.setKey(CustomKeys.UP, true);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        super.touchUp(screenX, screenY, pointer, button);
        CustomKeys.setKey(CustomKeys.UP, false);
        return true;
    }
}
