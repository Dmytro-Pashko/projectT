package com.mytrex.game.scene;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mytrex.game.GameWorld;
import com.mytrex.game.PlayerInputProcessor;
import com.mytrex.game.models.*;

import static com.mytrex.game.Tools.B2DVars.cameraPosition;

import java.util.Iterator;

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

        //Layer = 1 Земля.Точнее полигоны земли.
        for (MapObject object : map.getLayers().get(2).getObjects()) {
            Shape shape = getPolygon((PolygonMapObject) object);
            BodyDef bd = new BodyDef();
            bd.type = BodyDef.BodyType.StaticBody;
            Body body = gameWorld.getWorld().createBody(bd);
            body.createFixture(shape, 1);
            body.setUserData("ground");
            shape.dispose();//Удаляем шейп.
        }

        for (MapObject object : map.getLayers().get(1).getObjects()) {
            Shape shape = getPolygon((PolygonMapObject) object);
            BodyDef bd = new BodyDef();
            bd.type = BodyDef.BodyType.StaticBody;
            Body body = gameWorld.getWorld().createBody(bd);
            body.createFixture(shape, 1);
            body.setUserData("obstacle");
            shape.dispose();//Удаляем шейп.
        }

        for (MapObject object : map.getLayers().get(7).getObjects()) {
            Shape shape = getPolygon((PolygonMapObject) object);
            BodyDef bd = new BodyDef();
            bd.type = BodyDef.BodyType.StaticBody;
            Body body = gameWorld.getWorld().createBody(bd);
            body.createFixture(shape, 1);
            body.setUserData("finish");
            shape.dispose();//Удаляем шейп.
        }

        for (MapObject object : map.getLayers().get(4).getObjects()) {
            RectangleMapObject rectObject = (RectangleMapObject) object;
            gameWorld.setMob(rectObject.getRectangle().getX() / PPM, rectObject.getRectangle().getY() / PPM);
        }

        for (MapObject object : map.getLayers().get(3).getObjects()) {
            RectangleMapObject rectObject = (RectangleMapObject) object;
            gameWorld.setBrick(rectObject.getRectangle().getX() / PPM, rectObject.getRectangle().getY() / PPM);
        }

        for (MapObject object : map.getLayers().get(5).getObjects()) {
            RectangleMapObject rectObject = (RectangleMapObject) object;
            gameWorld.setCoin(rectObject.getRectangle().getX() / PPM, rectObject.getRectangle().getY() / PPM);
        }

        for (MapObject object : map.getLayers().get(6).getObjects()) {
            RectangleMapObject rectObject = (RectangleMapObject) object;
            gameWorld.setBox(rectObject.getRectangle().getX() / PPM, rectObject.getRectangle().getY() / PPM);
        }

        Gdx.input.setInputProcessor(new PlayerInputProcessor(gameWorld));

        batch = new SpriteBatch();
        FontRed1 = new BitmapFont();
        FontRed1.setColor(Color.BLACK);
    }


    private PolygonShape getPolygon(PolygonMapObject polygonObject) {
        PolygonShape polygon = new PolygonShape();
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();
        float[] worldVertices = new float[vertices.length];
        for (int i = 0; i < vertices.length; i++) worldVertices[i] = vertices[i] / PPM;
        polygon.set(worldVertices);
        return polygon;
    }

    public void cameraUpdate() {
        cameraPosition = camera.position;
        if (camera.position.x + 2 < gameWorld.getPlayer().getBody().getPosition().x)
            camera.position.set(camera.position.x + 0.1f, camera.position.y, 0);
        if (camera.position.x - 2 > gameWorld.getPlayer().getBody().getPosition().x && camera.position.x >= 8.0)
            camera.position.set(camera.position.x - 0.1f, camera.position.y, 0);
        //camera.position.set(gameWorld.getPlayer().getBody().getPosition().x , camera.position.y, 0);
    }

    @Override
    public void render(float delta) {
        gameWorld.getWorld().step(delta, 1, 1);
        cameraUpdate();
        camera.update();
        stateTime += delta;
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        debuger.render(gameWorld.getWorld(), camera.combined);
        gameWorld.getPlayer().Draw(stateTime, gameWorld.getPlayer().getBody().getPosition().x * PPM - (camera.position.x - 8) * PPM, gameWorld.getPlayer().getBody().getPosition().y * PPM - (camera.position.y - 8) * PPM);


        for (Flower flower : listFlowers)
            if (gameWorld.getPlayer().getBody().getPosition().dst(flower.getBody().getPosition().x, flower.getBody().getPosition().y) < 16f)
                flower.draw(stateTime, flower.getBody().getPosition().x * PPM - (camera.position.x - 8) * PPM, flower.getBody().getPosition().y * PPM - (camera.position.y - 8) * PPM);


        for (Mashroom mashroom : listMashrooms)
            if (gameWorld.getPlayer().getBody().getPosition().dst(mashroom.getBody().getPosition().x, mashroom.getBody().getPosition().y) < 16f)
                mashroom.draw(mashroom.getBody().getPosition().x * PPM - (camera.position.x - 8) * PPM, mashroom.getBody().getPosition().y * PPM - (camera.position.y - 8) * PPM);



        for (OrdinaryMob mob : listMobs) {
            if (gameWorld.getPlayer().getBody().getPosition().dst(mob.getBody().getPosition().x, mob.getBody().getPosition().y) < 16f) {
                mob.draw(stateTime, mob.getBody().getPosition().x * PPM - (camera.position.x - 8) * PPM, mob.getBody().getPosition().y * PPM - (camera.position.y - 8) * PPM);
            }
        }
        for (Brick brick : listBricks) {
            if (gameWorld.getPlayer().getBody().getPosition().dst(brick.getBody().getPosition().x,
                    brick.getBody().getPosition().y) < 20f) {
                if (brick.getBody() != null) {
                    brick.draw(brick.getBody().getPosition().x * PPM - (camera.position.x - 8) * PPM,
                            brick.getBody().getPosition().y * PPM - (camera.position.y - 8) * PPM);
                }else {
                    listBricks.remove(brick);
                }
            }
        }
        for (Coin coin : listCoins) {
            if (gameWorld.getPlayer().getBody().getPosition().dst(coin.getBody().getPosition().x, coin.getBody().getPosition().y) < 20f) {
                coin.draw(stateTime, coin.getBody().getPosition().x * PPM - (camera.position.x - 8) * PPM, coin.getBody().getPosition().y * PPM - (camera.position.y - 8) * PPM);
            }
        }
        for (SecretBox secretBox : listSecretBox) {
            if (gameWorld.getPlayer().getBody().getPosition().dst(secretBox.getBody().getPosition().x, secretBox.getBody().getPosition().y) < 20f) {
                secretBox.draw(stateTime, secretBox.getBody().getPosition().x * PPM - (camera.position.x - 8) * PPM, secretBox.getBody().getPosition().y * PPM - (camera.position.y - 8) * PPM);
            }
        }
        for (Animation animation : listAnimation) {
            if (!animation.getEffect().isComplete()) {
                sb.begin();
                animation.getEffect().draw(sb, delta);
                sb.end();
            } else {
                listAnimation.remove(animation);
                break;
            }
        }
        if (complete){
            game.setScreen(new Menu(game));
        }

        batch.begin();
        FontRed1.draw(batch, "Your score: " + score, 380, 500);
        batch.end();
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
