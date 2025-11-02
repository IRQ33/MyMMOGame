package com.irq3.mmo.game;

import com.badlogic.gdx.InputProcessor;

import java.util.HashSet;
import java.util.Set;

class GameInput implements InputProcessor {

    Set<Integer> integers;

    public GameInput() {
        integers = new HashSet<>();
    }
    public void update()
    {

    }

    @Override
    public boolean keyDown(int keycode) {
        integers.add(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        integers.remove(keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
