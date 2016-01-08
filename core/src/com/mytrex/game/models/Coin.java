package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import static com.mytrex.game.Tools.B2DVars.PPM;

public class Coin {
    private SpriteBatch batch;
    private Body body;
    private TextureRegion[] frames;
    private Texture sheet;
    private Animation animation;


    public Coin(Body body) {
        batch = new SpriteBatch();
        this.body = body;
        sheet = new Texture("core/assets/coin_1.png");
        frames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            frames[i] = new TextureRegion(sheet, 16 * i, 0, 16, 16);
        }
        animation = new Animation(0.2f, frames);
    }

    public void draw(float stateTime, float x, float y) {
        batch.begin();
        batch.draw(animation.getKeyFrame(stateTime, true), x * 2 - PPM, y * 2 - PPM, 32, 32);
        batch.end();
    }

    public Body getBody() {
        return body;
    }
}
