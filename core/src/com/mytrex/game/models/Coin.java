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
public class Coin {
    Sprite sprite, emtpyCoin;
    SpriteBatch batch;
    Body body;
    TextureRegion[] walkFrames;
    Texture walkSheet;
    Animation walkAnimation;

    public boolean flag = false;

    public Coin(Body body)
    {
        batch = new SpriteBatch();
        this.body = body;
        walkSheet = new Texture("core/assets/coin_1.png");
        walkFrames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            walkFrames[i] = new TextureRegion(walkSheet, 16 * i, 0, 10, 16);
        }
        walkAnimation = new Animation(0.2f, walkFrames);
    }

    public void draw(float stateTime, float x, float y)
    {
        batch.begin();
        batch.draw(walkAnimation.getKeyFrame(stateTime, true), x * 2 - PPM, y * 2 - PPM, 32, 32);
        batch.end();
    }

    public Body getBody() {
        return body;
    }
}
