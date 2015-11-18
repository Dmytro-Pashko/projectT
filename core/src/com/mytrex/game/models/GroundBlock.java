package com.mytrex.game.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Goodvin1709 on 18.11.2015.
 */
public class GroundBlock
{
    private Sprite sprite;
    private Body body;

    public GroundBlock(Body body) {
        this.body = body;
    }
}
