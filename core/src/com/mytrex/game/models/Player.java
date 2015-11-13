package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Goodvin1709 on 10.11.2015.
 */
public class Player
{
    private Sprite sprite;
    private int score;

    public Player()
    {
        score = 0;
        sprite = new Sprite(new Texture("badlogic.jpg"));
    }

    public float Heigth()
    {
        return sprite.getHeight();
    }
    public void setPosition(float x,float y)
    {
        sprite.setPosition(x,y);
    }

    public float Width()
    {
        return sprite.getWidth();
    }

    public float getX()
    {
        return sprite.getX();
    }

    public float getY()
    {
        return  sprite.getY();
    }

    public Sprite getSprite()
    {
        return this.sprite;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public void rotate(float angle)
    {
        sprite.setRotation(angle);

    }
}
