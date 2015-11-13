package com.mytrex.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Trex extends ApplicationAdapter {
    SpriteBatch batch;
    World world;

    @Override
    public void create() {
        batch = new SpriteBatch();
        world = new World();

    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
            world.draw(batch);
        batch.end();
    }

    public void update()
    {

        if (world.getPlayer().getX() + world.getPlayer().Width() <= Gdx.graphics.getWidth()) {
            //Право
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                world.getPlayer().setPosition(world.getPlayer().getX() + 10, world.getPlayer().getY());
                world.getPlayer().rotate(0f);
            }
        }

        if (world.getPlayer().getY() + world.getPlayer().Heigth() < Gdx.graphics.getHeight()) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                world.getPlayer().setPosition(world.getPlayer().getX(), world.getPlayer().getY() + 10);
                world.getPlayer().rotate(90.0f);
            }
        }

        if (world.getPlayer().getX() > 0) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                world.getPlayer().setPosition(world.getPlayer().getX() - 10, world.getPlayer().getY());
                world.getPlayer().rotate(180.0f);
            }
        }
        if (world.getPlayer().getY() > 0) {
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                world.getPlayer().setPosition(world.getPlayer().getX(),world.getPlayer().getY() - 10);
                world.getPlayer().rotate(270.0f);
            }
        }


    }
}
