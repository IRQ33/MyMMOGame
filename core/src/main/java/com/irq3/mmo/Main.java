package com.irq3.mmo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.irq3.mmo.screen.FirstScreen;

public class Main extends Game {
    public SpriteBatch batch;
    private InputMultiplexer inputMultiplexer;
    public final boolean DEV = true;
    public final String ip = "localhost";
    public final int port = 2200;


    @Override
    public void create() {

        batch = new SpriteBatch();
        inputMultiplexer = new InputMultiplexer();
        setScreen(new FirstScreen(this));
    }
    public void addNewInput(InputProcessor inputProcessor)
    {
        inputMultiplexer.addProcessor(inputProcessor);
    }
    public void removerInput(InputProcessor inputProcessor)
    {
        inputMultiplexer.removeProcessor(inputProcessor);
    }
}
