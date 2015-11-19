package com.mytrex.game.scene;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mytrex.game.GameWorld;
import com.mytrex.game.PlayerInputProcessor;
import com.mytrex.game.models.GameObject;
import com.mytrex.game.models.GroundBlock;

/**
 * Created by Goodvin1709 on 16.11.2015.
 */
public class GameScreen implements Screen
{
    private Game game;
    private Box2DDebugRenderer debuger;
    private GameWorld gameWorld;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Stage stage;
    private Label poslabel;

    public float ppuX;
    public float ppuY;


    public GameScreen(Game game)
    {
        ppuX = (float) Gdx.graphics.getWidth() / 10;
        ppuY = (float) Gdx.graphics.getHeight() / 10;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(10,10);
        camera.setToOrtho(false, 10, 10);
        camera.position.set(5,5,0);
        debuger = new Box2DDebugRenderer();
        gameWorld = new GameWorld();
        Gdx.input.setInputProcessor(new PlayerInputProcessor(gameWorld));

        BitmapFont labelfont= new BitmapFont(Gdx.files.internal("core/assets/defaultfont.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(labelfont, Color.ROYAL);
        poslabel = new Label("",labelStyle);
        poslabel.setPosition(8,Gdx.graphics.getHeight()-10);
        stage = new Stage();
        stage.addActor(poslabel);
    }
    public void cameraUpdate()
    {
      if (camera.position.x+2 < gameWorld.getPlayer().getBody().getPosition().x) camera.position.set(camera.position.x + 0.1f, camera.position.y, 0);
      if (camera.position.x-2 > gameWorld.getPlayer().getBody().getPosition().x) camera.position.set(camera.position.x-0.1f,camera.position.y,0);
    }
    @Override
    public void render(float delta)
    {
        poslabel.setText(1/delta + "");
        cameraUpdate();
        gameWorld.getWorld().step(delta, 5, 5);
        gameWorld.update();
        camera.update();
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        debuger.render(gameWorld.getWorld(),camera.combined);

        batch.begin();
        batch.draw(gameWorld.getPlayer().getSprite(), gameWorld.getPlayer().getBody().getPosition().x*ppuX-(camera.position.x-5)*ppuX - 0.5f*ppuX, gameWorld.getPlayer().getBody().getPosition().y*ppuY-(camera.position.y-5)*ppuY - 0.5f*ppuY, ppuX, ppuY);
        for (GroundBlock block : gameWorld.list){
            batch.draw(block.getSprite(), block.getBody().getPosition().x*ppuX-(camera.position.x-5)*ppuX, block.getBody().getPosition().y*ppuY-(camera.position.y-5)*ppuY, ppuX, 0.8f * ppuY);
        }
        batch.end();
        stage.draw();
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
