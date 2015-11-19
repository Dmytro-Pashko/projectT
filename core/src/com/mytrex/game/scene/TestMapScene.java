package com.mytrex.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


public class TestMapScene implements Screen
{

    TiledMap map;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    TiledMapTileLayer layer;

    public TestMapScene()
    {
        map = new TmxMapLoader().load("core/assets/map1.tmx");
        camera = new OrthographicCamera(10,10);
        camera.setToOrtho(false, 256, 256);
        camera.position.set(128,128,0);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

        layer = (TiledMapTileLayer)map.getLayers().get(0);
        System.out.println("Layer Heigth = "+layer.getHeight());
        System.out.println("Layer Width = "+layer.getWidth());
        System.out.println("Layer tile Heigth = "+layer.getTileHeight());
        System.out.println("Layer tile Width = "+layer.getTileWidth());

    }
    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
