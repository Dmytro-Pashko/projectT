package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Goodvin1709 on 10.11.2015.
 */
public class Player
{
    public Body body;


    public Player(Body body)
    {
        this.body = body;
    }

}
