package com.mytrex.game.scene;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mytrex.game.GameWorld;

/**
 * Created by Goodvin1709 on 16.11.2015.
 */
public class GameScreen implements Screen
{
    private Game game;
    private GameWorld world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debuger;

    public GameScreen(Game game)
    {
        debuger = new Box2DDebugRenderer(true,true,true,true,true,true);
        world = new GameWorld();
        camera = new OrthographicCamera(10,10);
        this.game = game;
        camera.position.set(10,10,10);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        debuger.render(world.getWorld(),camera.combined);
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
