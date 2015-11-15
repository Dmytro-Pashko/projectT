package com.mytrex.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mytrex.game.scene.Menu;

public class Trex extends Game {
    SpriteBatch batch;
    World world;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new Menu(this));
    }
}
