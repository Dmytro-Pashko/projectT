package com.mytrex.game.scene;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mytrex.game.GameWorld;
import com.mytrex.game.PlayerInputProcessor;

public class GameScreen implements Screen
{
    private Game game;
    private Box2DDebugRenderer debuger;
    private GameWorld gameWorld;
    private OrthographicCamera camera;

    public GameScreen(Game game)
    {
        this.game = game;
        gameWorld = new GameWorld();
        debuger = new Box2DDebugRenderer();
        Gdx.input.setInputProcessor(new PlayerInputProcessor(gameWorld));
        camera = new OrthographicCamera(10,10);
        camera.position.set(5,5,0);
    }

    @Override
    public void render(float delta)
    {
        gameWorld.update();
        camera.update();
        gameWorld.getWorld().step(delta, 5, 5);
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        debuger.render(gameWorld.getWorld(),camera.combined);
    }

    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {}
    @Override
    public void show() {}
}
