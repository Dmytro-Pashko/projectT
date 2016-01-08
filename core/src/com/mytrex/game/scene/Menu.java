package com.mytrex.game.scene;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mytrex.game.Trex;

public class Menu implements Screen, InputProcessor
{
    Stage stage;
    Game game;
    Label poslabel;
    Sprite background;
    float angle = 0f;

    public Menu(Game game)
    {
        this.game = game;
        Create();
        Gdx.input.setInputProcessor(this);
    }
    public void Create()
    {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background = new Sprite(new Texture("core/assets/background.jpg"));
        background.setCenter(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        background.setScale(1.53f, 1.53f);

        Image play_button = new Image(new Texture("core/assets/play_button.png"));
        Image options_button = new Image(new Texture("core/assets/options_button.png"));
        Image scores_button = new Image(new Texture("core/assets/scores_button.png"));
        Image exit_button = new Image(new Texture("core/assets/exit_button.png"));

        stage.addActor(play_button);
        stage.addActor(options_button);
        stage.addActor(scores_button);
        stage.addActor(exit_button);

        play_button.setPosition(150,300);
        options_button.setPosition(150, 180);
        scores_button.setPosition(150, 60);
        exit_button.setPosition(500, 15);

        BitmapFont labelfont= new BitmapFont(Gdx.files.internal("core/assets/defaultfont.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(labelfont, Color.BLACK);
        poslabel = new Label("",labelStyle);
        poslabel.setPosition(8,Gdx.graphics.getHeight()-10);
        stage.addActor(poslabel);

    }

    @Override
    public void show() {
    }
    //toDelete
    @Override
    public void render(float delta)
    {
        if (angle < 360f){
            angle += .2;
        }
        else {
            angle = 0f;
        }
        background.setRotation(angle);
        poslabel.setText(String.format("Mouse Position X=%d Y=%d",Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY()));
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        background.draw(stage.getBatch());
        stage.getBatch().end();
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (screenX > 150 && screenX < 465 && screenY > 116 && screenY < 208) {
            game.setScreen(new TestMapScene());
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}