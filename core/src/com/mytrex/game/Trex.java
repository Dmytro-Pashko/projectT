package com.mytrex.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Trex extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Vector2 vect;
    Sprite sprite;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        vect = new Vector2(0, 0);
        sprite = new Sprite(img);

    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    public void update()
    {

        if (sprite.getX() + img.getWidth() <= Gdx.graphics.getWidth()) {
            //Право
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                sprite.setPosition(sprite.getX() + 10, sprite.getY());
                sprite.setRotation(0f);
            }

        }

        if (sprite.getY() + img.getHeight() < Gdx.graphics.getHeight()) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                sprite.setPosition(sprite.getX(), sprite.getY() + 10);
                sprite.setRotation(90.0f);
            }
        }

        if (sprite.getX() > 0) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                sprite.setPosition(sprite.getX() - 10, sprite.getY());
                sprite.setRotation(180.0f);
            }
        }
        if (sprite.getY() > 0) {
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                sprite.setPosition(sprite.getX(), sprite.getY() - 10);
                sprite.setRotation(270.0f);
            }
        }


    }
}
