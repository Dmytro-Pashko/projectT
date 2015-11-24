package com.mytrex.game.Tools;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Antilamer on 24.11.2015.
 */
public abstract class GameState {
    //protected GameStateManager gsm;
    protected Game game;

    protected SpriteBatch spriteBatch;
    protected OrthographicCamera cam;
    protected OrthographicCamera hudCam;
}
