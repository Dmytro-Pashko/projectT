package com.mytrex.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Antilamer on 15.11.2015.
 */
public class KeysActor extends Actor {
    private TextureRegion region;

    public KeysActor(){
        region = new TextureRegion(new Texture("wall.jpg"));
        setSize(42, 32);
        setPosition(0, 0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region, getX(), getY(), getWidth(), getHeight());
    }
}
