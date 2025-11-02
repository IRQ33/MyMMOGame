package com.irq3.mmo.screen;


import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.irq3.mmo.game.ManagerAssets;
import com.irq3.mmo.Main;

public class FirstScreen extends Screens {
    private BitmapFont bitmapFont;
    private ManagerAssets managerAssets;


    public FirstScreen(Main main) {
        super(main);
        managerAssets = ManagerAssets.getInstance();
    }

    @Override
    public void show() {
        initFonts();
        if(getMain().DEV){

        }

    }

    @Override
    void update() {

    }

    @Override
    void draw() {
        managerAssets.assetManager.update();
        if(managerAssets.loaded())
        {
            this.getSpriteBatch().draw(managerAssets.getData("test", Texture.class),0,0);
        }
    }

    private void initFonts() {
        BitmapFontLoader.BitmapFontParameter parameter = new BitmapFontLoader.BitmapFontParameter();
        //managerAssets.registerFont("pixel_font","fonts/font.ttf",parameter);
        managerAssets.registerImage("test","test.png");
    }
}
