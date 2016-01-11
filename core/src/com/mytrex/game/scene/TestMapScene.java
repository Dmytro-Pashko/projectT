package com.mytrex.game.scene;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mytrex.game.GameWorld;
import com.mytrex.game.PlayerInputProcessor;
import com.mytrex.game.models.*;

import java.util.ArrayList;
import java.util.Iterator;

import static com.mytrex.game.Tools.B2DVars.cameraPosition;
import static com.mytrex.game.Tools.B2DVars.*;


public class TestMapScene implements Screen {

    TiledMap map;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    private Box2DDebugRenderer debuger;
    private GameWorld gameWorld;
    private float stateTime = 0f;
    SpriteBatch sb;
    Game game;
    private SpriteBatch batch;
    private BitmapFont FontRed1;
    int oldfps;

    public TestMapScene(Game game) {
        this.game = game;
        sb = new SpriteBatch();
        debuger = new Box2DDebugRenderer(true, true, true, true, true, true);
        map = new TmxMapLoader().load("core/assets/stage1.tmx");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() * 0.5f / PPM, Gdx.graphics.getHeight() * 0.5f / PPM);
        camera.position.set(128 / PPM, 128 / PPM, 0);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map, 0.0625f);
        gameWorld = new GameWorld();
        gameWorld.setPlayer(1, 1);
        camera.position.set(8, 8, 0);
        Gdx.input.setInputProcessor(new PlayerInputProcessor(gameWorld));
        batch = new SpriteBatch();
        FontRed1 = new BitmapFont();
        FontRed1.setColor(Color.BLACK);

        long starttime = System.currentTimeMillis();
        for(MapLayer layer : map.getLayers())
        {
            if (layer.getName().equals("Obstacles"))
                for (MapObject object :layer.getObjects())
                {
                    RectangleMapObject rectObject = (RectangleMapObject) object;
                    gameWorld.setObstacle(rectObject.getRectangle().getX() / PPM, rectObject.getRectangle().getY() / PPM, rectObject.getRectangle().getHeight() / PPM, rectObject.getRectangle().getWidth() / PPM);
                }
            else if (layer.getName().equals("Ground"))
                for (MapObject object :layer.getObjects())
                {
                    RectangleMapObject rectObject = (RectangleMapObject) object;
                    gameWorld.setGround(rectObject.getRectangle().getX() / PPM, rectObject.getRectangle().getY() / PPM, rectObject.getRectangle().getHeight()/PPM, rectObject.getRectangle().getWidth()/PPM);
                }
            else if (layer.getName().equals("Blocks"))
            {
                for (MapObject object :layer.getObjects())
                {
                    RectangleMapObject rectObject = (RectangleMapObject) object;
                    gameWorld.setBrick(rectObject.getRectangle().getX() / PPM, rectObject.getRectangle().getY() / PPM, rectObject.getRectangle().getHeight() / PPM, rectObject.getRectangle().getWidth() / PPM);
                }
            }
            else if (layer.getName().equals("Mobs"))
            {
                for (MapObject object :layer.getObjects())
                {
                    RectangleMapObject rectObject = (RectangleMapObject) object;
                    gameWorld.setMob(rectObject.getRectangle().getX() / PPM, rectObject.getRectangle().getY() / PPM, rectObject.getRectangle().getHeight() / PPM, rectObject.getRectangle().getWidth() / PPM);
                }
            }
            else if (layer.getName().equals("Coins"))
            {
                for (MapObject object :layer.getObjects())
                {
                    RectangleMapObject rectObject = (RectangleMapObject) object;
                    gameWorld.setCoin(rectObject.getRectangle().getX() / PPM, rectObject.getRectangle().getY() / PPM, rectObject.getRectangle().getHeight() / PPM, rectObject.getRectangle().getWidth() / PPM);
                }
            }
            else if (layer.getName().equals("CoinBoxes"))
            {
                for (MapObject object :layer.getObjects())
                {
                    RectangleMapObject rectObject = (RectangleMapObject) object;
                    gameWorld.setCoinBox(rectObject.getRectangle().getX() / PPM, rectObject.getRectangle().getY() / PPM, rectObject.getRectangle().getHeight() / PPM, rectObject.getRectangle().getWidth() / PPM);
                }
            }

            else if (layer.getName().equals("finish"))
            {
                for (MapObject object :layer.getObjects())
                {
                    RectangleMapObject rectObject = (RectangleMapObject) object;
                    gameWorld.setFinish(rectObject.getRectangle().getX() / PPM, rectObject.getRectangle().getY() / PPM, rectObject.getRectangle().getHeight() / PPM, rectObject.getRectangle().getWidth() / PPM);
                }
            }
            else if (layer.getName().equals("SecretBoxes"))
            {
                for (MapObject object :layer.getObjects())
                {
                    RectangleMapObject rectObject = (RectangleMapObject) object;
                    if (object.getProperties().containsKey("MashroomBox"))
                    {
                        gameWorld.setSecretMashroomBox(rectObject.getRectangle().getX() / PPM, rectObject.getRectangle().getY() / PPM, rectObject.getRectangle().getHeight() / PPM, rectObject.getRectangle().getWidth() / PPM);
                    }
                    if (object.getProperties().containsKey("StarBox"))
                    {
                        gameWorld.setSecretStarBox(rectObject.getRectangle().getX() / PPM, rectObject.getRectangle().getY() / PPM, rectObject.getRectangle().getHeight() / PPM, rectObject.getRectangle().getWidth() / PPM);
                    }
                    if (object.getProperties().containsKey("CoinsBox"))
                    {
                        gameWorld.setSecretCoinsBox(rectObject.getRectangle().getX() / PPM, rectObject.getRectangle().getY() / PPM, rectObject.getRectangle().getHeight() / PPM, rectObject.getRectangle().getWidth() / PPM);
                    }
                    if (object.getProperties().containsKey("LifeBox"))
                    {
                        gameWorld.setSecretLifeBox(rectObject.getRectangle().getX() / PPM, rectObject.getRectangle().getY() / PPM, rectObject.getRectangle().getHeight() / PPM, rectObject.getRectangle().getWidth() / PPM);
                    }
                }
            }
        }
    }

    public void cameraUpdate() {
        cameraPosition = camera.position;
//      if (camera.position.x + 2 < gameWorld.getPlayer().getBody().getPosition().x)
//             //camera.position.set(camera.position.x + 0.1f, camera.position.y, 0);
 camera.position.set(gameWorld.getPlayer().getBody().getPosition().x, camera.position.y, 0);
//        if (camera.position.x - 2 > gameWorld.getPlayer().getBody().getPosition().x && camera.position.x >= 8.0)
//            //camera.position.set(camera.position.x - 0.1f, camera.position.y, 0);
//            camera.position.set(camera.position.x - 0.1f, camera.position.y, 0);
    }

    @Override
    public void render(float delta) {
        float timeStep = 1.0f / 60.f;
        int iterationCount = 10;
        gameWorld.getWorld().step(timeStep, iterationCount, 10);
        gameWorld.getWorld().clearForces();
        camera.update();
        cameraUpdate();
        stateTime += delta;
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        debuger.render(gameWorld.getWorld(), camera.combined);
        drawPlayer();
        drawFlowers();
        drawCoins();
        drawSecretCoinBoxes();
        drawBricks();
        drawCoinBoxs();
        drawMobs();
        drawMashrooms();
        drawScores(delta);
        drawAnimations(delta);
        checkFinish();
    }

    private void drawPlayer() {
        gameWorld.getPlayer().Draw(stateTime, gameWorld.getPlayer().getBody().getPosition().x * PPM - (camera.position.x - 8) * PPM, gameWorld.getPlayer().getBody().getPosition().y * PPM - (camera.position.y - 8) * PPM);
    }


    private void drawBricks() {
        Iterator<Brick> brickIterator = listBricks.iterator();
        while (brickIterator.hasNext())
        {
            Brick brick = brickIterator.next();
            if (brick.getBody().getUserData().equals("del"))
            {
                brickIterator.remove();
            }
            if ((int)gameWorld.getPlayer().distToBody(brick.getBody())<16 && brick.getBody().getUserData().equals("brick"))
                brick.draw(brick.getBody().getPosition().x * PPM - (camera.position.x - 8) * PPM,
                        brick.getBody().getPosition().y * PPM - (camera.position.y - 8) * PPM);
        }
    }

    private void drawMobs() {
        for (OrdinaryMob mob : listMobs) {
            if (gameWorld.getPlayer().distToBody(mob.getBody()) < 16f) {
                if (mob.getBody().getUserData() == "del") {
                    listMobs.remove(mob);
                    break;
                } else {
                    mob.draw(stateTime, mob.getBody().getPosition().x * PPM - (camera.position.x - 8) * PPM, mob.getBody().getPosition().y * PPM - (camera.position.y - 8) * PPM);

                }
            }
        }
    }

    private void drawCoinBoxs() {
        for (CoinBox coinBox : listCoinBoxes) {
            if (gameWorld.getPlayer().distToBody(coinBox.getBody()) < 16f) {
                if (coinBox.getBody().getUserData() == "del") {
                    listCoinBoxes.remove(coinBox);
                    break;
                } else {
                    coinBox.draw(stateTime, coinBox.getBody().getPosition().x * PPM - (camera.position.x - 8) * PPM, coinBox.getBody().getPosition().y * PPM - (camera.position.y - 8) * PPM);
                }
            }
        }
    }

    private void drawSecretCoinBoxes() {
        for (SecretCoinBox coinBox : listSecretBoxes) {
            if (gameWorld.getPlayer().distToBody(coinBox.getBody()) < 16f) {
                if (coinBox.getBody().getUserData() == "del") {
                    listCoinBoxes.remove(coinBox);
                    break;
                } else {
                    coinBox.draw(stateTime, coinBox.getBody().getPosition().x * PPM - (camera.position.x - 8) * PPM, coinBox.getBody().getPosition().y * PPM - (camera.position.y - 8) * PPM);
                }
            }
        }
    }

    private void drawFlowers()
    {
        for (Flower flower : listFlowers)
        {
            if (gameWorld.getPlayer().distToBody(flower.getBody()) < 16f) {
                if (flower.getBody().getUserData() == "del") {
                    listFlowers.remove(flower);
                    break;
                } else {
                    flower.draw(stateTime, flower.getBody().getPosition().x * PPM - (camera.position.x - 8) * PPM, flower.getBody().getPosition().y * PPM - (camera.position.y - 8) * PPM);
                }
            }
        }
    }

    private void drawMashrooms() {
        for (Mashroom mashroom : listMashrooms) {
            if (gameWorld.getPlayer().distToBody(mashroom.getBody()) < 16f) {
                if (mashroom.getBody().getUserData() == "del") {
                    listMashrooms.remove(mashroom);
                    break;
                } else {
                    mashroom.draw(mashroom.getBody().getPosition().x * PPM - (camera.position.x - 8) * PPM, mashroom.getBody().getPosition().y * PPM - (camera.position.y - 8) * PPM);
                }
            }
        }
    }

    private void drawCoins() {
        for (Coin coin : listCoins) {
            if (gameWorld.getPlayer().getBody().getPosition().dst(coin.getBody().getPosition().x,
                    coin.getBody().getPosition().y) < 20f) {
                if (coin.getBody().getUserData().equals("del"))
                {
                    listCoins.remove(coin);
                    break;
                }
                else
                {
                    coin.draw(stateTime, coin.getBody().getPosition().x * PPM - (camera.position.x - 8) * PPM, coin.getBody().getPosition().y * PPM - (camera.position.y - 8) * PPM);
                }
            }
        }
    }

    private void drawAnimations(float delta) {

        for (Animation animation : listAnimation)
        {
            if (!animation.getEffect().isComplete())
            {
                sb.begin();
                animation.getEffect().draw(sb, delta);
                sb.end();
            }
            else
            {
                listAnimation.remove(animation);
                break;
            }
        }
    }

    private void drawScores(float delta) {
        if (oldfps != (int)(1/delta)) {
            oldfps = (int) (1 / delta);
        }

        batch.begin();
        FontRed1.draw(batch,"FPS: "+oldfps,20,500);
        FontRed1.draw(batch, "Your score: " + score, 380, 500);
        batch.end();
    }

    private void checkFinish() {
        if (complete) {
            game.setScreen(new Menu(game));
        }
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

    @Override
    public void show() {
    }
}
