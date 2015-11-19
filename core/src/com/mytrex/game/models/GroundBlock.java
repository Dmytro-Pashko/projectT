package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Goodvin1709 on 18.11.2015.
 */
public class GroundBlock extends GameObject
{
    private Sprite sprite;
    private Body body;

    public GroundBlock(Body body) {
        this.body = body;
        sprite = new Sprite(new Texture("core/assets/wall.jpg"));
    }

    public Sprite getSprite(){
        return sprite;
    }

    public Body getBody() {
        return body;
    }
}
