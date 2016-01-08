package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import static com.mytrex.game.Tools.B2DVars.PPM;

/**
 * Created by Antilamer on 06.01.2016.
 */
public class SecretBox {
    SpriteBatch batch;
    Body body;
    TextureRegion[] waitFrames;
    Texture waitSheet, openTexture;
    Animation waitAnimation;
    private boolean flag = false;   //open/close

    public SecretBox(Body body) {
        batch = new SpriteBatch();
        this.body = body;
        waitSheet = new Texture("core/assets/SecretBox_1.png");
        openTexture = new Texture("core/assets/SecretBox_2.png");

        waitFrames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            waitFrames[i] = new TextureRegion(waitSheet, 16 * i, 0, 16, 16);
        }
        waitAnimation = new Animation(0.2f, waitFrames);
    }

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
