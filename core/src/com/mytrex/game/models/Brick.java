package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import static com.mytrex.game.Tools.B2DVars.PPM;

/**
 * Created by Antilamer on 06.01.2016.
 */
public class Brick {

    Sprite sprite;
    SpriteBatch batch;
    Body body;
    boolean flag = false;

    public Brick(Body body)
    {
        batch = new SpriteBatch();
        this.body = body;
        sprite = new Sprite(new Texture("core/assets/brick_1.png"));
    }

    public void draw(float x, float y)
    {
        batch.begin();
        batch.draw(sprite, x * 2 - PPM, y * 2 - PPM, 32, 32);
        batch.end();
    }

    public void setFlag(){
        flag = true;
    }

    public Body getBody() {
        return body;
    }

}
