package com.mytrex.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mytrex.game.scene.GameScreen;
import com.mytrex.game.scene.Menu;

public class Trex extends Game {

    @Override
    public void create() {
        //setScreen(new Menu(this));
        setScreen(new GameScreen(this));
    }
}
