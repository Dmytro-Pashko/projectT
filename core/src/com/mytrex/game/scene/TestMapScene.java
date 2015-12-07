package com.mytrex.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.GameWorld;
import com.mytrex.game.MyContactListener;
import com.mytrex.game.PlayerInputProcessor;
import static com.mytrex.game.Tools.B2DVars.PPM;


public class TestMapScene implements Screen {


    TiledMap map;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    private Box2DDebugRenderer debuger;
    private GameWorld gameWorld;
    private float stateTime = 0f;

    public TestMapScene() {
        debuger = new Box2DDebugRenderer(true,true,true,true,true,true);
        ;
        map = new TmxMapLoader().load("core/assets/stage1.tmx");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() * 0.5f / PPM, Gdx.graphics.getHeight() * 0.5f / PPM);
        camera.position.set(128 / PPM, 128 / PPM, 0);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map, 0.0625f);
        gameWorld = new GameWorld();
        gameWorld.setPlayer(2, 1);
        gameWorld.setMob(5, 1);
        camera.position.set(8, 8, 0);

        //Layer = 1 Земля.Точнее полигоны земли.
        for (MapObject object : map.getLayers().get(2).getObjects()) {
            Shape shape = getPolygon((PolygonMapObject) object);
            BodyDef bd = new BodyDef();
            bd.type = BodyDef.BodyType.StaticBody;
            Body body = gameWorld.getWorld().createBody(bd);
            body.createFixture(shape,1);
            shape.dispose();//Удаляем шейп.
        }

        for (MapObject object : map.getLayers().get(1).getObjects()) {
            Shape shape = getPolygon((PolygonMapObject) object);
            BodyDef bd = new BodyDef();
            bd.type = BodyDef.BodyType.StaticBody;
            Body body = gameWorld.getWorld().createBody(bd);
            body.createFixture(shape,1).setUserData("obstacle");
            shape.dispose();//Удаляем шейп.
        }

        for (MapObject object : map.getLayers().get(4).getObjects())
        {
            RectangleMapObject rectObject = (RectangleMapObject)object;
            gameWorld.setMob(rectObject.getRectangle().getX()/PPM,rectObject.getRectangle().getY()/PPM);
        }


        Gdx.input.setInputProcessor(new PlayerInputProcessor(gameWorld));
    }


    private  PolygonShape getPolygon(PolygonMapObject polygonObject) {
        PolygonShape polygon = new PolygonShape();
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();
        float[] worldVertices = new float[vertices.length];
        for (int i = 0; i < vertices.length; i++) worldVertices[i] = vertices[i] / PPM;
        polygon.set(worldVertices);
        return polygon;
    }

    public void cameraUpdate() {
        if (camera.position.x + 2 < gameWorld.getPlayer().getBody().getPosition().x)
            camera.position.set(camera.position.x + 0.1f, camera.position.y, 0);
        if (camera.position.x - 2 > gameWorld.getPlayer().getBody().getPosition().x && camera.position.x >= 8.0)
            camera.position.set(camera.position.x - 0.1f, camera.position.y, 0);
    }

    @Override
    public void render(float delta) {
        gameWorld.getWorld().step(delta, 3, 3);
        gameWorld.update();
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
        gameWorld.getMob().Draw(stateTime, gameWorld.getMob().getBody().getPosition().x * PPM - (camera.position.x - 8) * PPM, gameWorld.getMob().getBody().getPosition().y * PPM - (camera.position.y - 8) * PPM);
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
