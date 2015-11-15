package com.mytrex.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Trex extends Game {
    SpriteBatch batch;
    Stage stage;
    World world;

    @Override
    public void create() {
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        world = new World();

        MouseActor mouseActor = new MouseActor();
        mouseActor.addListener(new MouseListener());
        stage.addActor(mouseActor);

        KeysActor keysActor = new KeysActor();
        keysActor.addListener(new KeysListener());
        stage.addActor(keysActor);
        stage.setKeyboardFocus(keysActor);
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act(Gdx.graphics.getDeltaTime());
    }
}
