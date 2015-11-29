package com.mytrex.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mytrex.game.GameWorld;
import com.mytrex.game.PlayerInputProcessor;

import java.util.ArrayList;

import static com.mytrex.game.Tools.B2DVars.PPM;


public class TestMapScene implements Screen {


    TiledMap map;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    TiledMapTileLayer layer;
    TiledMapTileLayer layer2;
    private Box2DDebugRenderer debuger;
    private GameWorld gameWorld;
    Label poslabel;

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
        noPassBlocks.add(207);
        noPassBlocks.add(208);
        noPassBlocks.add(140);
        noPassBlocks.add(190);

        map = new TmxMapLoader().load("core/assets/map2.tmx");
        camera = new OrthographicCamera(256 / PPM, 16 / PPM);
        camera.setToOrtho(false, 256 / PPM, 256 / PPM);
        camera.position.set(128 / PPM, 128 / PPM, 0);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map,  0.0625f);
        layer = (TiledMapTileLayer) map.getLayers().get(0);
        layer2 = (TiledMapTileLayer) map.getLayers().get(1);
        gameWorld = new GameWorld();


        for (int i = 0; i < layer.getWidth(); i++) {
            for (int j = 0; j < layer.getHeight(); j++) {
                TiledMapTileLayer.Cell cell = layer.getCell(i, j);
                TiledMapTileLayer.Cell cell2 = layer2.getCell(i, j);
                try{
                    if (noPassBlocks.contains(cell.getTile().getId())) gameWorld.createGroundBlocks(i, j);
                }
                catch (NullPointerException e){}
                try{
                    if (cell2.getTile().getId() == 109) gameWorld.setPlayer(i, j);
                }
                catch (NullPointerException e){}
            }
        }


        System.out.println("Layer Heigth = " + layer.getHeight());
        System.out.println("Layer Width = " + layer.getWidth());
        System.out.println("Layer tile Heigth = " + layer.getTileHeight());
        System.out.println("Layer tile Width = " + layer.getTileWidth());

        debuger = new Box2DDebugRenderer();
        Gdx.input.setInputProcessor(new PlayerInputProcessor(gameWorld));

    }

    public void cameraUpdate()
    {
        if (camera.position.x+2 < gameWorld.getPlayer().getBody().getPosition().x) camera.position.set(camera.position.x + 0.1f, camera.position.y, 0);
        if (camera.position.x-2 > gameWorld.getPlayer().getBody().getPosition().x) camera.position.set(camera.position.x-0.1f,camera.position.y,0);
    }

    public void setTiledCoord(){
        for (int i = 0; i < layer.getWidth(); i++) {
            for (int j = 0; j < layer.getHeight(); j++) {
                TiledMapTileLayer.Cell cell2 = layer2.getCell(i, j);
                try{
                    if (cell2.getTile().getId() == 109){
                        cell2.getTile().setOffsetX(gameWorld.player.getBody().getPosition().x * PPM);
                        cell2.getTile().setOffsetY(gameWorld.player.getBody().getPosition().y * PPM);
                    }

                }
                catch (NullPointerException e){}
            }
        }
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
        setTiledCoord();
        cameraUpdate();

        System.out.println(1/delta);
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
