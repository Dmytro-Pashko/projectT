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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.Tools.BodyEditorLoader;
import com.mytrex.game.models.Player;


public class TestMapScene implements Screen
{

    TiledMap map;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    TiledMapTileLayer layer;

    Player player;
    World world;
    private Box2DDebugRenderer debuger;

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

        world = new World(new Vector2(0, -9.8f), true);
        player = new Player(initPlayer(128, 20));
        debuger = new Box2DDebugRenderer();
    }

    public void update()
    {
        //System.out.println("Player X="+player.getBody().getPosition().x+" Player Y="+player.getBody().getPosition().y);
        if (player.getLeftMove()) player.getBody().setTransform(player.getBody().getPosition().x - 0.1f, player.getBody().getPosition().y, 0);

        if (player.getRightMove()) player.getBody().setTransform(player.getBody().getPosition().x + 0.1f, player.getBody().getPosition().y, 0);

    }

    public Body initPlayer(float x, float y){
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("core/assets/box.json"));
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(def);
        loader.attachFixture(body,"box",new FixtureDef(),1.0f);
        body.setTransform(x, y, 0);
        return body;
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

        update();
        world.step(delta, 5, 5);
        debuger.render(world,camera.combined);
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
