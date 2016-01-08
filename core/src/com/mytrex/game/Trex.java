package com.mytrex.game;

import com.badlogic.gdx.Game;
import com.mytrex.game.scene.TestMapScene;
import com.mytrex.game.scene.Menu;

public class Trex extends Game {

    @Override
    public void create() {
        setScreen(new Menu(this));
        //setScreen(new TestMapScene());
    }
}
