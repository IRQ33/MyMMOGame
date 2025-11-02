package com.irq3.mmo.game;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


import java.util.HashMap;

public class ManagerAssets {

    private static final ManagerAssets instance = new ManagerAssets();
    public AssetManager assetManager;
    public HashMap<String,AssetInfo> hashMap;

    public ManagerAssets() {
        hashMap = new HashMap<>();
        this.assetManager = new AssetManager();
    }

    public static ManagerAssets getInstance(){
     return ManagerAssets.instance;
    }
    public static class AssetInfo{
        public String path;
        public Class<?> clazz;
        public AssetLoaderParameters<?> assetLoaderParameters;

        public AssetInfo(String path, Class<?> clazz, AssetLoaderParameters<?> assetLoaderParameters) {
            this.path = path;
            this.clazz = clazz;
            this.assetLoaderParameters = assetLoaderParameters;
        }
    }

    public void registerImage(String name, String path)
    {
        TextureLoader.TextureParameter params = new TextureLoader.TextureParameter();
        params.minFilter = Texture.TextureFilter.Nearest;
        params.magFilter = Texture.TextureFilter.Nearest;

        hashMap.put(name, new AssetInfo(path, Texture.class, params));
        assetManager.load(path, Texture.class, params);
    }
    public void registerImage(String name, String path, AssetLoaderParameters<Texture> assetLoaderParameters)
    {
        hashMap.put(name,new AssetInfo(path, Texture.class,assetLoaderParameters));
        assetManager.load(path, Texture.class,assetLoaderParameters);
    }

    public void registerFont(String name, String path, AssetLoaderParameters<BitmapFont> font)
    {
        hashMap.put(name,new AssetInfo(path, BitmapFont.class,font));
        assetManager.load(path, BitmapFont.class);
    }
    public void registerPixelFont(String name, String path)
    {
        BitmapFontLoader.BitmapFontParameter loader = new BitmapFontLoader.BitmapFontParameter();
        loader.minFilter= Texture.TextureFilter.Nearest;
        loader.magFilter = Texture.TextureFilter.Nearest;
        hashMap.put(name,new AssetInfo(path, BitmapFont.class,loader));
        assetManager.load(path, BitmapFont.class,loader);
    }
    public void registerDialog(String name, String path)
    {
        hashMap.put(name,new AssetInfo(path, String.class,null));
        assetManager.load(path, String.class);
    }

    public <T> T getData(String name, Class<T> tClass)
    {
        return assetManager.get(hashMap.get(name).path);
    }
    public boolean loaded()
    {
        return assetManager.isFinished();
    }
}
