package com.mytrex.game.scene;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Menu implements Screen
{
    Stage stage;
    Game game;
    SpriteBatch batch;
    Label poslabel;

    public Menu(Game game)
    {
        this.game = game;
        Create();
    }
    public void Create()
    {
        stage = new Stage();
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
        Image play_button = new Image(new Texture("play_button.png"));
        stage.addActor(play_button);
        play_button.setPosition(150,300);
        BitmapFont labelfont= new BitmapFont(Gdx.files.internal("defaultfont.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(labelfont, Color.BLACK);
        poslabel = new Label("",labelStyle);
        poslabel.setPosition(8,Gdx.graphics.getHeight()-10);
        stage.addActor(poslabel);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta)
    {
        poslabel.setText(String.format("Mouse Position X=%d Y=%d",Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY()));
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
