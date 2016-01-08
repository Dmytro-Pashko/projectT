package com.mytrex.game.models;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import static com.mytrex.game.Tools.B2DVars.PPM;

public class Flower {

    private SpriteBatch batch;
    private Body body;
    private TextureRegion[] frames;
    private Texture sheet;
    private Animation animation;
    private float startTime;


    public Flower(Body body) {
        startTime = 0;
        batch = new SpriteBatch();
        this.body = body;
        sheet = new Texture("core/assets/flower.png");
        frames = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            frames[i] = new TextureRegion(sheet, 16 * i, 0, 16, 16);
        }
        animation = new Animation(0.2f, frames);
    }

    public void draw(float stateTime, float x, float y)
    {
            batch.begin();

            if (startTime == 0) startTime = stateTime;
            if ((startTime != 0) && (stateTime < startTime + .5)) {
                batch.draw(animation.getKeyFrame(stateTime, true), x * 2 - PPM, ((y + (stateTime - startTime) * 32) * 2 - PPM) - 32, 32, 32);
            } else {
                batch.draw(animation.getKeyFrame(stateTime, true), x * 2 - PPM, y * 2 - PPM, 32, 32);
            }
            batch.end();
    }

    public Body getBody() {
        return body;
    }
}