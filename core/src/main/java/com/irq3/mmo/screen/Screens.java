package com.irq3.mmo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.irq3.mmo.Main;

public abstract class Screens implements Screen {
    public static int WIDTH = 640;
    public static int HEIGHT = 360;

    private OrthographicCamera orthographicCamera;
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    private Stage stage;
    private Main main;

    public Screens(Main main) {
        this.spriteBatch = main.batch;
        this.main = main;
        orthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        viewport = new FitViewport(WIDTH,HEIGHT,orthographicCamera);
        stage = new Stage(viewport,spriteBatch);
        main.addNewInput(stage);
    }
    abstract void update();
    abstract void draw();

    @Override
    public void render(float delta) {
        update();
        ScreenUtils.clear(Color.BLUE);
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);
        orthographicCamera.update();
        stage.act();
        stage.draw();
        spriteBatch.begin();
        draw();
        spriteBatch.end();
    }


    @Override
    public void dispose() {
        stage.dispose();
        spriteBatch.dispose();

    }
    public void moveCamera(Vector2 vector2, float lerp)
    {
        orthographicCamera.position.x += (vector2.x-orthographicCamera.position.x)*lerp;
        orthographicCamera.position.y += (vector2.y-orthographicCamera.position.y)*lerp;
    }
    public void moveCamera(Vector2 vector2)
    {
        orthographicCamera.position.x += (vector2.x-orthographicCamera.position.x)*0.02f;
        orthographicCamera.position.y += (vector2.y-orthographicCamera.position.y)*0.02f;
    }
    public void resize(int width, int height) {}

    public void pause() {}
    public void resume() {}
    public void hide() {}

    public OrthographicCamera getOrthographicCamera() {
        return orthographicCamera;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public Main getMain() {
        return main;
    }
}
