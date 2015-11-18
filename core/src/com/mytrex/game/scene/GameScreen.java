package com.mytrex.game.scene;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mytrex.game.GameWorld;
import com.mytrex.game.Tools.GameRenderer;

/**
 * Created by Goodvin1709 on 16.11.2015.
 */
public class GameScreen implements Screen
{
    private Game game;
    private Box2DDebugRenderer debuger;
    private World world;
    private GameRenderer renderer;
    private GameWorld gameWorld;
    private ShapeRenderer shapeRenderer;

    public GameScreen(Game game)
    {
        debuger = new Box2DDebugRenderer();
        debuger.setDrawVelocities(true);
        debuger.setDrawContacts(true);
        world = new GameWorld().getWorld();
        this.game = game;
        gameWorld = new GameWorld();
        renderer = new GameRenderer(gameWorld);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta)
    {
        world.step(delta,5,5);
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        //camera.update();
        //debuger.render(world,camera.combined);
        gameWorld.update(delta);
        renderer.render();
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
