package com.mytrex.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mytrex.game.models.Player;

/**
 * Created by Goodvin1709 on 10.11.2015.
 */
public class World {
    private Player player;

    private int Width; //max world x.
    private int Height; //max world y.


    public World() {
        player = new Player();
    }

    public Player getPlayer() {
        return player;
    }

    //world render.
    public void draw(SpriteBatch batch) {
        player.getSprite().draw(batch);
    }
}
