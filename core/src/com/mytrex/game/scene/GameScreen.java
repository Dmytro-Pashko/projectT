package com.mytrex.game.scene;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mytrex.game.GameWorld;
import com.mytrex.game.PlayerInputProcessor;

/**
 * Created by Goodvin1709 on 16.11.2015.
 */
public class GameScreen implements Screen
{
    private Box2DDebugRenderer debuger;
    private GameWorld gameWorld;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    public float ppuX;
    public float ppuY;
    private float correction;


    public GameScreen()
    {
        ppuX = (float) Gdx.graphics.getWidth() / 10;
        ppuY = (float) Gdx.graphics.getHeight() / 10;
        correction = 0f;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(10,10);
        //camera.setToOrtho(false, 10, 10);
        camera.position.set(5,5,0);
        debuger = new Box2DDebugRenderer();
        //gameWorld = new GameWorld();
        Gdx.input.setInputProcessor(new PlayerInputProcessor(gameWorld));
    }
    public void cameraUpdate()
    {
      if (camera.position.x+2 < gameWorld.getPlayer().getBody().getPosition().x) camera.position.set(camera.position.x + 0.1f, camera.position.y, 0);
      if (camera.position.x-2 > gameWorld.getPlayer().getBody().getPosition().x) camera.position.set(camera.position.x-0.1f,camera.position.y,0);
    }

    @Override
    public void render(float delta)
    {
        cameraUpdate();
        gameWorld.getWorld().step(delta, 6, 2);
        gameWorld.update();
        camera.update();
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        debuger.render(gameWorld.getWorld(),camera.combined);

        batch.begin();
        batch.draw(gameWorld.getPlayer().getSprite(), (gameWorld.getPlayer().getBody().getPosition().x + 0.6f) * ppuX + correction * ppuX, (gameWorld.getPlayer().getBody().getPosition().y - 0.50f) * ppuY, 1.1f * ppuX, 1.1f * ppuY);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        
    }
    @Override
    public void show() {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {}
}
