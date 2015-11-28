package com.mytrex.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.GameWorld;
import com.mytrex.game.PlayerInputProcessor;
import com.mytrex.game.Tools.BodyEditorLoader;
import net.dermetfan.gdx.physics.box2d.Box2DMapObjectParser;

import java.util.ArrayList;
import java.util.TreeMap;

import static com.mytrex.game.Tools.B2DVars.PPM;


public class TestMapScene implements Screen {


    TiledMap map;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    TiledMapTileLayer layer;
    private Box2DDebugRenderer debuger;
    private GameWorld gameWorld;

    public TestMapScene() {
        ArrayList<Integer> noPassBlocks = new ArrayList<>();
        noPassBlocks.add(136);
        noPassBlocks.add(137);
        noPassBlocks.add(138);
        noPassBlocks.add(691);
        noPassBlocks.add(692);
        noPassBlocks.add(693);
        noPassBlocks.add(694);
        noPassBlocks.add(718);
        noPassBlocks.add(719);
        noPassBlocks.add(720);
        noPassBlocks.add(721);
        noPassBlocks.add(666);
        noPassBlocks.add(667);
        noPassBlocks.add(639);
        noPassBlocks.add(640);
        noPassBlocks.add(1);

        map = new TmxMapLoader().load("core/assets/map1.tmx");
        camera = new OrthographicCamera(16 / PPM, 16 / PPM);
        camera.setToOrtho(false, 256 / PPM, 256 / PPM);
        camera.position.set(128 / PPM, 128 / PPM, 0);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map,  0.0625f);
        layer = (TiledMapTileLayer) map.getLayers().get(0);
        gameWorld = new GameWorld();


        for (int i = 0; i < layer.getHeight(); i++) {
            for (int j = 0; j < layer.getWidth(); j++) {
                TiledMapTileLayer.Cell cell = layer.getCell(i, j);
                if (noPassBlocks.contains(cell.getTile().getId())) {
                    gameWorld.createGroundBlocks(i, j);
                }
            }
        }

        System.out.println("Layer Heigth = " + layer.getHeight());
        System.out.println("Layer Width = " + layer.getWidth());
        System.out.println("Layer tile Heigth = " + layer.getTileHeight());
        System.out.println("Layer tile Width = " + layer.getTileWidth());

        debuger = new Box2DDebugRenderer();
        Gdx.input.setInputProcessor(new PlayerInputProcessor(gameWorld));


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        gameWorld.getWorld().step(delta, 5, 5);
        gameWorld.update();
        debuger.render(gameWorld.getWorld(), camera.combined);
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
