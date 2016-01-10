package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import static com.mytrex.game.Tools.B2DVars.PPM;

/**
 * Created by Antilamer on 10.01.2016.
 */
public abstract class Box {

    protected SpriteBatch batch;
    protected Body body;
    protected TextureRegion[] waitFrames;
    protected Texture waitSheet, openTexture;
    protected com.badlogic.gdx.graphics.g2d.Animation waitAnimation;
    protected boolean flag = false;   // full/emty

    public void draw(float stateTime, float x, float y) {
        batch.begin();
        if (!flag) batch.draw(waitAnimation.getKeyFrame(stateTime, true), x * 2 - PPM, y * 2 - PPM, 32, 32);
        else
            batch.draw(openTexture, x * 2 - PPM, y * 2 - PPM, 32, 32);
        batch.end();
    }

    public Body getBody() {
        return body;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {

        return flag;
    }
}
